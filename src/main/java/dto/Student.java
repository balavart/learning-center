package dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
public class Student {
    private String name;
    private String curriculum;
    private LocalDate startDate;
    private Integer courseDuration;
    private List<Course> courses;


    public Integer getTotalHours() {
        return courses.stream().reduce(0,
                (integer, course) -> integer + course.getDurationHours(),
                Integer::sum);
    }
}