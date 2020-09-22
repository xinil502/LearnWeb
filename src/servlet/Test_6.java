package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接收请求的常用方法。
 */
@WebServlet(name = "servlet/Test_6", value = "/servlet/Test_6")
public class Test_6 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String url = req.getRequestURL().toString();  //获取完整的URL,返回StringBuffer类型
        String uri = req.getRequestURI();             //获取请求的资源名称部分
        String queryString = req.getQueryString();    //获取请求行的参数部分
        String method = req.getMethod();              //获取客户端的请求方式
        String protocol = req.getProtocol();          //获取HTTP版本号
        String webApp = req.getContextPath();         //获取当前项目的站点名
        System.out.println("获取完整的URL:" + url);
        System.out.println("获取请求的资源名称部分:" + uri);
        System.out.println("获取请求行的参数部分:" + queryString);
        System.out.println("获取客户端的请求方式:" + method);
        System.out.println("获取HTTP版本号:" + protocol);
        System.out.println("获取项目的站点名：" + webApp);
        resp.getWriter().write("<p align=\"center\">接收请求的常用方法</p>" +
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
                "            <th>获取项目的站点名：</th>\n" +
                "            <th>" + webApp + "</th>\n" +
                "        </tr>\n" +
                "    </table>");
    }
}
