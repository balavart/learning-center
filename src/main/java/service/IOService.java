package service;

import org.apache.commons.lang.NumberUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class IOService {

    private final LearningCenter learningCenter;

    public IOService(LearningCenter learningCenter) {
        this.learningCenter = learningCenter;
    }

    public void printReport() {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Type \"0\" or nothing for short report or type any other character(s) for full report: ");
            String outputValue = in.nextLine().trim();
            int param = outputValue.equals("0") || outputValue.isEmpty() ? 0 : 1;
            System.out.println(learningCenter.getReport(param));
        }
    }

    public static LocalDate getStartDate() {
        int year = getYear();
        int month = getMonth();
        while (true) {
            System.out.println("Type day of month: ");
            String outputValue = new Scanner(System.in).nextLine().trim();
            if (!NumberUtils.isNumber(outputValue)
                    || outputValue.isEmpty()
                    || Integer.parseInt(outputValue) < 1
                    || Integer.parseInt(outputValue) > 31) {
                System.err.println("Incorrect day format!");
            } else {
                int dayOfMonth = Integer.parseInt(outputValue);
                try {
                    return LocalDate.of(year, month, dayOfMonth);
                } catch (DateTimeException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    private static Integer getYear() {
        while (true) {
            System.out.println("Type year in format 20xx: ");
            String outputValue = new Scanner(System.in).nextLine().trim();
            if (!NumberUtils.isNumber(outputValue)
                    || outputValue.isEmpty()
                    || Integer.parseInt(outputValue) < 2000) {
                System.err.println("Incorrect year format!");
            } else {
                return Integer.valueOf(outputValue);
            }
        }
    }

    private static Integer getMonth() {
        while (true) {
            System.out.println("Type month in format 1-12: ");
            String outputValue = new Scanner(System.in).nextLine().trim();
            if (!NumberUtils.isNumber(outputValue)
                    || outputValue.isEmpty()
                    || Integer.parseInt(outputValue) < 1
                    || Integer.parseInt(outputValue) > 12) {
                System.err.println("Incorrect month format!");
            } else {
                return Integer.valueOf(outputValue);
            }
        }
    }
}