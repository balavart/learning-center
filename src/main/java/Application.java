import service.IOService;
import service.LearningCenter;
import service.StudentService;

public class Application {

    public static void main(String[] args) {
        new IOService(new LearningCenter(new StudentService())).printReport();
    }
}