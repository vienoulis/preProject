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
        if (request.getParameter("add") != null) {
            UserService.getInstance().addUser(request.getParameter("name"),
                    request.getParameter("age"),
                    request.getParameter("passport"));
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (request.getParameter("delete") != null) {
            UserService.getInstance().delete(Integer.parseInt(request.getParameter("userId")));
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (request.getParameter("update") != null) {
            response.setHeader("userId", request.getParameter("userId"));
            getServletContext().getRequestDispatcher("/WEB-INF/UpdateJSP.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        if (request.getParameter("doUpdate") != null) {
            UserService.getInstance().update(Long.parseLong(request.getParameter("userId")),
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
