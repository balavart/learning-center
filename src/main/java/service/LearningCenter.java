package service;

import dto.Student;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

public class LearningCenter {

    private final StudentService studentService;

    public LearningCenter(StudentService studentService) {
        this.studentService = studentService;
    }

    public String getReport(LocalDate startDate, int param) {
        return param == 0 ? getSimpleReport(studentService, startDate) : getDefaultReport(studentService, startDate);
    }

    public LocalDate getLastLearnDate(Student student) {
        int courseDuration = student.getCourseDuration();
        int learningDays = courseDuration / 8;
        int lastHours = courseDuration % 8;

        int weekends = courseDuration / 40;
        if (lastHours != 0) {
            learningDays++;
        }
        int daysWithWeekends = learningDays + weekends * 2;
        if (weekends == 0) {
            daysWithWeekends = ifDaysWithWeekends(student, daysWithWeekends);
        }
        LocalDate localDate = student.getStartDate().plusDays(daysWithWeekends - 1);
        localDate = ifWeekend(localDate);

        return localDate;
    }

    private String getDefaultReport(StudentService studentService, LocalDate startDate) {
        StringBuilder sb = new StringBuilder().append("Full information: \n\n");
        studentService.getStudents().stream().map(student -> printStudentDefault(student, startDate)).forEach(sb::append);

        return sb.toString();
    }

    private String printStudentDefault(Student student, LocalDate startDate) {
        LocalDate lastLearnDate = getLastLearnDate(student);
        String printCourses = student.getCourses()
                .stream()
                .map(course -> "\t" + course.toString() + ": " + course.getDurationHours() + " HOURS")
                .reduce((s, s2) -> s + "\n" + s2).orElseGet(() -> "");
        String firstOutput = "Student: " +
                student.getName() + "\n" +
                "Working time (from 10 to 18)" + "\n" +
                "Program name: " +
                student.getCurriculum() + "\n" +
                "Program duration: " +
                student.getCourseDuration() + "\n" +
                "His courses: " + "\n" +
                printCourses + "\n" +
                "Start date: " +
                student.getStartDate() + "\n" +
                "End date: " +
                lastLearnDate + "\n";

        return getOutputResult(lastLearnDate, firstOutput, student.getCourseDuration() % 8, startDate) + "\n";
    }

    private String getSimpleReport(StudentService studentService, LocalDate startDate) {
        StringBuilder sb = new StringBuilder().append(String.format("Short (Generating report date - %s) : \n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, EEEE, HH:mm", Locale.US))));
        studentService.getStudents().stream().map(student -> printStudentSimple(student, startDate)).forEach(sb::append);

        return sb.toString();
    }

    private String printStudentSimple(Student student, LocalDate startDate) {
        LocalDate endLearnDate = getLastLearnDate(student);
        String resultOutput = String.format("%s (%s) - ", student.getName(), student.getCurriculum());
        int remainHours = student.getCourseDuration() % 8;

        return getOutputResult(endLearnDate, resultOutput, remainHours, startDate);
    }

    private String getOutputResult(LocalDate endLearnDate, String outputResult, int remainHours, LocalDate startDate) {
        if (startDate.isBefore(endLearnDate)) {
            outputResult += String.format("Training is not finished. %s are left until the end.\n",
                    getRemain(endLearnDate, remainHours, startDate));
        } else {
            outputResult += String.format("Training completed. %s have passed since the end.\n",
                    getPast(endLearnDate, remainHours, startDate));
        }

        return outputResult;
    }

    private String getRemain(LocalDate endLearnDate, int remainHours, LocalDate startDate) {
        LocalDate now = ifWeekend(startDate);
        StringBuilder sb = new StringBuilder();
        long until = now.until(endLearnDate, DAYS);
        int weekendsDays = (int) until / 5 * 2;
        long remainDays = until - (long) weekendsDays;
        if (until != 0) {
            sb.append(remainDays).append(" DAYS ");
        }
        if (remainHours != 0) {
            sb.append(remainHours).append(" HOURS");
        }

        return sb.toString();
    }

    private String getPast(LocalDate endLearnDate, int remainHours, LocalDate startDate) {
        DayOfWeek d = startDate.getDayOfWeek();
        LocalDate countdown = d.getValue() > 5 ? d == DayOfWeek.SATURDAY ?
                startDate.minusDays(1) : startDate.minusDays(2) : startDate;
        String result = "";
        long until = endLearnDate.until(countdown, DAYS);
        int weekendsDays = (int) until / 5 * 2;
        long remainDays = until - (long) weekendsDays;
        if (until != 0) {
            result += remainDays + " DAYS ";
        }
        if (remainHours != 0) {
            result += 8 - remainHours + " HOURS";
        }

        return result;
    }

    private int ifDaysWithWeekends(Student student, int daysWithWeekends) {
        LocalDate startDate = student.getStartDate();
        for (int i = 0; i < startDate.until(startDate.plusDays(daysWithWeekends - 1), DAYS); i++) {
            LocalDate localDate = startDate.plusDays(i);
            if (localDate != ifWeekend(localDate)) {
                daysWithWeekends++;
            }
        }

        return daysWithWeekends;
    }

    public LocalDate ifWeekend(LocalDate localDate) {
        DayOfWeek day = localDate.getDayOfWeek();

        return day.getValue() > 5 ? day == DayOfWeek.SATURDAY ? localDate.plusDays(2) : localDate.plusDays(1) : localDate;
    }
}