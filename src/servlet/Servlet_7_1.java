package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆操作
 */
@WebServlet("/Servlet_7_1")
public class Servlet_7_1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        User u = (User)request.getAttribute("name");
        System.out.println(u.userName + "登陆成功");
        response.getWriter().write("登陆成功");
    }
}
