package servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 设置编解码字符集
 * 字符输出流。
 * 字节输出流。
 */
@WebServlet("/servlet_9")
public class Servlet_9 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止乱码：
//      response.setHeader("content-type", "text/html;charset=utf-8");//方法一
        //方法二
        response.setContentType("text/html;charset=utf-8");

        //字符输出流
        PrintWriter pw = response.getWriter();
        pw.println("大声说中文。");
        response.getWriter().write("123456");

        //字节输出流
        ServletOutputStream sos = response.getOutputStream();
        sos.print("字节输出流");
        sos.write("666".getBytes("utf-8"));

    }
}
