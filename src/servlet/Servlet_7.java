package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 登陆操作
 */
@WebServlet("/Servlet_7")
public class Servlet_7 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        User u1 = new User(uname, upwd);
        boolean signIn = false;
        try {
            signIn = u1.signIn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(signIn){
            request.setAttribute("name", u1);
            request.getRequestDispatcher("/Servlet_7_1").forward(request, response);
            System.out.println("登陆成功");
        }else{
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            System.out.println("登陆失败");
        }
    }
}
