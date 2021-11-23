package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Student {
    private String name;
    private String curriculum;
    private LocalDate startDate;
    private int courseDuration;
    private Set<Course> courses;
}