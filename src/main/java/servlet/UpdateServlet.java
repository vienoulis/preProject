package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("userId", request.getParameter("userId"));
        request.setAttribute("usersList", UserService.getInstance().getAllUsers());
        getServletContext().getRequestDispatcher("/WEB-INF/UpdateJSP.jsp").forward(request, response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService.getInstance().update(Long.parseLong(request.getParameter("userId")),
                request.getParameter("nameToUpdate"),
                request.getParameter("ageToUpdate"),
                request.getParameter("passportToUpdate"));
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
