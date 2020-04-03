package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UsersServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String add = request.getParameter("add");
        String delete = request.getParameter("delete");
        String update = request.getParameter("update");
        String doUpdate = request.getParameter("doUpdate");
        String count = request.getParameter("userId");
        if (add != null) {
            new UserService().addUser(request.getParameter("name"),
                    request.getParameter("age"),
                    request.getParameter("passport"));
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (delete != null) {
            new UserService().delete(Integer.parseInt(request.getParameter("userId")));
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (update != null) {
            response.setHeader("userId", request.getParameter("userId"));
            getServletContext().getRequestDispatcher("/WEB-INF/UpdateJSP.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (doUpdate != null) {
            new UserService().update(Long.parseLong(request.getParameter("userId")),
                    request.getParameter("nameToUpdate"),
                    request.getParameter("ageToUpdate"),
                    request.getParameter("passportToUpdate"));
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
