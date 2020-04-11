package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartPageServlet", value = "/start")
public class StartPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authName = request.getParameter("authName");
        String authPassword = request.getParameter("authPassword");
        request.getSession().setAttribute("authName", authName);
        request.getSession().setAttribute("authPassword", authPassword);

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("authName", null);
        request.getSession().setAttribute("authPassword", null);
        getServletContext().getRequestDispatcher("/WEB-INF/start.jsp").forward(request, response);
    }
}
