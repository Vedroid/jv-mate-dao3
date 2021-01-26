package ua.vedroid.dao3.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.vedroid.dao3.exceptions.AuthenticationException;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.security.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("ua.vedroid.dao3");
    private static final String LOGIN_JSP = "/WEB-INF/views/login.jsp";
    private static final String DRIVER_ID = "driver_id";
    private AuthenticationService service =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");

        try {
            Driver driver = service.login(login, pwd);
            HttpSession session = req.getSession();
            session.setAttribute(DRIVER_ID, driver.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
