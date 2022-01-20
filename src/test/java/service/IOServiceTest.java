package service;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IOServiceTest {

    @Test
    void getReportDate() {
        Exception exception = assertThrows(DateTimeException.class, () -> LocalDate.of(2021, 2, 29));

        String expectedMessage = "is not a leap year";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}