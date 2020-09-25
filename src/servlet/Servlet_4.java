package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 获取请求主体
 */
@WebServlet("/Servlet_4")
public class Servlet_4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //获取字符流
        BufferedReader br = request.getReader();
        //读取数据
        String s = null;
        while((s = br.readLine()) != null){
            System.out.println(s);
        }
    }
}