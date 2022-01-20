package service;

import dto.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class LearningCenterTest {

    private final StudentService studentService = new StudentService();
    private final LearningCenter learningCenter = new LearningCenter(studentService);

    @Test
    public void ifWeekend() {
        LocalDate afterWeekend = learningCenter.ifWeekend(LocalDate.of(2021, 12, 4));

        DayOfWeek expectedDay = DayOfWeek.MONDAY;
        DayOfWeek actualDay = afterWeekend.getDayOfWeek();

        assertEquals(expectedDay, actualDay);
    }

    @ParameterizedTest
    @MethodSource("studentDates")
    public void getLastLearningDate(LocalDate expectedDate, String studentName) {
        Student student = studentService.getStudents().stream().filter(s -> s.getName().equals(studentName))
                .findFirst().orElse(null);
        Assertions.assertEquals(expectedDate, learningCenter.getLastLearnDate(student), getMessage(student));
    }

    private String getMessage(Student student) {
        return String.format("NAME - %s, TIME - %s, START DATE - %s",
                student.getName(), student.getCourseDuration(), student.getStartDate());
    }

    private static Stream<Arguments> studentDates() {
        return Stream.of(
                arguments(LocalDate.of(2021, 12, 27), "Ivanov Ivan"),
                arguments(LocalDate.of(2021, 12, 21), "Sidorov Ivan")
        );
    }
}