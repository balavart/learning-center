package service;


import dto.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class LearningCenterTest {

    private final StudentService studentService = new StudentService();
    private final LearningCenter learningCenter = new LearningCenter(studentService);

    private String getMessage(Student student) {
        return String.format("NAME - %s, TIME - %s, START DATE - %s",
                student.getName(), student.getCourseDuration(), student.getStartDate());
    }

    @Test
    void getLastLearningDateS1() {
        Student student = studentService.getStudents().stream().filter(s -> s.getName().equals("Ivanov Ivan"))
                .findFirst().orElse(null);
        LocalDate toCheck = LocalDate.of(2021, 11, 26);
        Assertions.assertEquals(toCheck, learningCenter.getLastLearnDate(student), getMessage(student));
    }

    @Test
    void getLastLearningDateS2() {
        Student student = studentService.getStudents().stream().filter(s -> s.getName().equals("Sidorov Ivan"))
                .findFirst().orElse(null);
        LocalDate toCheck = LocalDate.of(2021, 11, 22);
        Assertions.assertEquals(toCheck, learningCenter.getLastLearnDate(student), getMessage(student));
    }
}