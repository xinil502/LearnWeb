package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当有请求进来的时候,会找到当前电脑的8080端口，来访问主机的服务器。
 * 找到服务器后，会通过请求行当中的路径，访问到具体的应用，
 * 在应用中找到与之相对应的路径（注解）
 * 此时就会找到那个被访问的Servlet。
 * 如果该Servlet是被第一次访问，服务器就会创建一个对应的Servlet。
 * 此时request对象，response对象就会被生成，来处理请求和响应。
 */

/**
 * 实现Servlet实现流程
 * 1．创建普通Java类
 * 2．实现Servlet的规范，继承HttpServlet类
 * 3．重写service方法，用来处理请求
 * 4．设置注解，指定访问路径
 */
@WebServlet(name = "servlet/Test_1", value = {"/servlet/Test_1", "/servlet/Test1", "/servlet/Test01", "/servlet/Test_01"})//value设置访问url
public class Test_1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //打印内容到控制台
        System.out.println("Hello Servlet.");
        //通过流输出数据到浏览器
        resp.getWriter().write("Hello Servlet.");
    }
}