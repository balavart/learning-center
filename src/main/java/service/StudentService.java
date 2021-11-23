package service;

import dto.Course;
import dto.Student;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.*;

import static dto.Course.*;

@ToString
@Getter
public class StudentService {

    private final List<Student> students;

    public StudentService() {
        Student first = createStudent("Ivanov Ivan", "Java Developer",
                LocalDate.of(2021, 11, 18),
                new HashSet<>(Arrays.asList(JAVA, JDBC, SPRING)));

        Student second = createStudent("Sidorov Ivan", "AQE",
                LocalDate.of(2021, 11, 14),
                new HashSet<>(Arrays.asList(TEST_DESIGN, PAGE_OBJECT, SELENIUM)));

        students = new ArrayList<>(Arrays.asList(first, second));
    }

    private Student createStudent(String name, String curriculum, LocalDate startDate, Set<Course> studentCourses) {
        return new Student().setName(name)
                .setCurriculum(curriculum)
                .setStartDate(startDate)
                .setCourses(studentCourses)
                .setCourseDuration(getCommonHours(studentCourses));
    }

    private int getCommonHours(Set<Course> studentCourses) {
        return studentCourses.stream().reduce(0,
                (integer, course) -> integer + course.getDurationHours(),
                Integer::sum);
    }
}