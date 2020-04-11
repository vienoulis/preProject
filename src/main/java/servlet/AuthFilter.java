package servlet;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/admin", "/admin/update","/admin/delete"})
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if (nonNull(session) &&
                !nonNull(session.getAttribute("authName")) &&
                !nonNull(session.getAttribute("authPassword"))) {
            session.setAttribute("authName", request.getParameter("authName"));
            session.setAttribute("authPassword", request.getParameter("authPassword"));
        }

        String authName = (String) session.getAttribute("authName");
        String authPassword = (String) session.getAttribute("authPassword");
        if (UserService.getInstance().authUser(authName, authPassword)) {
            if (UserService.getInstance().isAdmin(authName)) {
                chain.doFilter(request, response);
            } else {
                req.getServletContext().getRequestDispatcher("/user").forward(request, response);
            }
        } else {
            req.getServletContext().getRequestDispatcher("/start").forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
