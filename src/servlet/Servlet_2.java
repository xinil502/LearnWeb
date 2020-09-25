package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request获取请求行信息。
 */
@WebServlet("/Servlet_2/Test_request")
public class Servlet_2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String url = request.getRequestURL().toString();  //获取完整的URL,返回StringBuffer类型
        String uri = request.getRequestURI();             //获取请求的资源名称部分
        String queryString = request.getQueryString();    //获取请求行的参数部分
        String method = request.getMethod();              //获取客户端的请求方式
        String protocol = request.getProtocol();          //获取HTTP版本号
        String webApp = request.getContextPath();         //获取当前项目的站点名
        System.out.println("获取完整的URL:" + url);
        System.out.println("获取请求的资源名称部分:" + uri);
        System.out.println("获取请求行的参数部分:" + queryString);
        System.out.println("获取客户端的请求方式:" + method);
        System.out.println("获取HTTP版本号:" + protocol);
        System.out.println("获取项目的站点名：" + webApp);
        response.getWriter().write("<p align=\"center\">接收请求的常用方法</p>" +
                "<table border=\"10px\" width=\"50%\" height=\"200px\" align=\"center\">\n" +
                "        <tr align=\"center\">\n" +
                "            <th>获取完整的URL:</th>\n" +
                "            <th>" + url+ "</th>\n" +
                "        </tr>\n" +"        <tr align=\"center\">\n" +
                "            <th>获取请求的资源名称部分:</th>\n" +
                "            <th>" + uri+ "</th>\n" +
                "        </tr>\n" +"        <tr align=\"center\">\n" +
                "            <th>获取请求行的参数部分:</th>\n" +
                "            <th>" + queryString+ "</th>\n" +
                "        </tr>\n" +"        <tr align=\"center\">\n" +
                "            <th>获取客户端的请求方式:</th>\n" +
                "            <th>" + method+ "</th>\n" +
                "        </tr>\n" +"        <tr align=\"center\">\n" +
                "            <th>获取HTTP版本号:</th>\n" +
                "            <th>" + protocol+ "</th>\n" +
                "        </tr>\n" +"        <tr align=\"center\">\n" +
                "            <th>获取项目的虚拟路径：</th>\n" +
                "            <th>" + webApp + "</th>\n" +
                "        </tr>\n" +
                "    </table>");
    }
}
