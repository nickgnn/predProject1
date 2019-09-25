import exception.DBException;
import service.UserService;

public class Main {
    public static void main(String[] args) throws DBException {
        UserService userService = new UserService();
        userService.createTable();

        System.out.println("hello");
    }
}
