package zoid.demo.jetty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PathServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 7840452696366393112L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("PathServlet");
        resp.getWriter().write("PathServlet");
        resp.getWriter().close();
    }
}
