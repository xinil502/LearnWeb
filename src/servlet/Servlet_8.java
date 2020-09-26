package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 */
@WebServlet("/servlet_8")
public class Servlet_8 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //访问servlet_8会重定向到百度

        /**
         * response.setStatus(302);
         * response.setHeader("location","/LearnWeb/servlet_8_1");
         */

        response.sendRedirect("http://www.baidu.com");

    }
}
