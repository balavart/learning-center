package dto;

import lombok.Getter;

@Getter
public enum Course {
    JAVA(12),
    JDBC(24),
    SPRING(16),
    TEST_DESIGN(10),
    PAGE_OBJECT(16),
    SELENIUM(16);

    int durationHours;

    Course(int durationHours) {
        this.durationHours = durationHours;
    }
}