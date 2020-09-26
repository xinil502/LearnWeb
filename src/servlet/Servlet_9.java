package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置编解码字符集
 * 字符输出流。
 */
@WebServlet("/servlet_9")
public class Servlet_9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //方法一
//        response.setHeader("content-type", "text/html;charset=utf-8");
        //方法二
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("大声说中文。");
    }
}
