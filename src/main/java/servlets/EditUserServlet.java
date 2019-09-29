package servlets;

import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));

        System.out.println("id is: " + id);
        System.out.println("name is: " + name);
        System.out.println("age is: " + age);

        req.getRequestDispatcher("/users").forward(req, resp);

        if (name.equals("null")) {
            try {
                service.updateUser(new User(), age);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }
    }
}
