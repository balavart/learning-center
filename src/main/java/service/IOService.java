package service;

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
}