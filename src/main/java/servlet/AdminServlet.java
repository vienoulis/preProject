    package servlet;

    import com.google.gson.Gson;
    import model.User;
    import service.UserService;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.List;


    @WebServlet(name = "AdminServlet", value = "/admin")
    public class AdminServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            UserService.getInstance().addUser(request.getParameter("name"),
                    request.getParameter("age"),
                    request.getParameter("passport"),
                    request.getParameter("password"),
                    request.getParameter("role"));
            request.setAttribute("test", UserService.getInstance().getAllUsers());
            response.setStatus(HttpServletResponse.SC_OK);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute("test", UserService.getInstance().getAllUsers());
            response.setStatus(HttpServletResponse.SC_OK);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
    }
