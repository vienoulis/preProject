    package servlet;

    import model.User;
    import service.UserService;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.List;


    @WebServlet(name = "UsersServlet", value = "/")
    public class UsersServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            UserService.getInstance().addUser(request.getParameter("name"),
                    request.getParameter("age"),
                    request.getParameter("passport"));
            List<String> strings =  UserService.getInstance().getAllUsersToGSON();
            request.setAttribute("usersList",strings);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.setStatus(HttpServletResponse.SC_OK);
        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<String> strings =  UserService.getInstance().getAllUsersToGSON();
            request.setAttribute("usersList", strings);
            for (String s: strings){
                System.out.println(s);
            }
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
