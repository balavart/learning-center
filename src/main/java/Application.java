import service.IOService;
import service.LearningCenter;
import service.StudentService;

public class Application {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        LearningCenter learningCenter = new LearningCenter(studentService);
        IOService ioService = new IOService(learningCenter);

        ioService.printReport();
    }
}