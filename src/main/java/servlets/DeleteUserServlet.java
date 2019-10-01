package servlets;

import exception.DBException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserService();
        String name = req.getParameter("name");

        req.getRequestDispatcher("/users").forward(req, resp);

        try {
            service.deleteUser(name);
        } catch (DBException e) {
            e.printStackTrace();
        }
        System.out.println(name + " IS DELETED!");
    }
}
