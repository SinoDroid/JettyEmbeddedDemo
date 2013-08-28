package zoid.demo.jetty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExtensionServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5508595093199294081L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("ExtensionServlet");
        resp.getWriter().write("ExtensionServlet");
        resp.getWriter().close();
    }
}
