package service;

import dto.Course;
import dto.Student;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static dto.Course.*;

@ToString
@Getter
public class StudentService {

    private final List<Student> students;

    public StudentService() {
        Student first = createStudent("Ivanov Ivan", "Java Developer",
                LocalDate.of(2021, 12, 18),
                new LinkedList<>(Arrays.asList(JAVA, JDBC, SPRING)));

        Student second = createStudent("Sidorov Ivan", "AQE",
                LocalDate.of(2021, 12, 14),
                new LinkedList<>(Arrays.asList(TEST_DESIGN, PAGE_OBJECT, SELENIUM)));

        students = new ArrayList<>(Arrays.asList(first, second));
    }

    private Student createStudent(String name, String curriculum, LocalDate startDate, List<Course> studentCourses) {
        Student newStudent = new Student();
        return newStudent.setName(name)
                .setCurriculum(curriculum)
                .setStartDate(startDate)
                .setCourses(studentCourses)
                .setCourseDuration(newStudent.getTotalHours());
    }
}