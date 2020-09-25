package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求转发&共享数据
 */
@WebServlet("/Servlet_6")
public class Servlet_6 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_6被访问了");
        request.setAttribute("msg", "这是我想说的话");        //存储共享数据

        request.getRequestDispatcher("/Servlet_6_1").forward(request,response);        //转发到Servlet_6_1

    }
}
