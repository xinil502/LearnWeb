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
@WebServlet("/Servlet_6_1")
public class Servlet_6_1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet_6_1被访问了。");

        Object obj = request.getAttribute("msg"); //获取共享数据
        System.out.println((String)obj);
    }
}
