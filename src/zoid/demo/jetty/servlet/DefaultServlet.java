package zoid.demo.jetty.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -9117471110902305984L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DefaultServlet");
        resp.getWriter().write("DefaultServlet");
        resp.getWriter().close();
    }
}
