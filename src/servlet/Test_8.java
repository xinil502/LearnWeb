package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求乱码问题
 *      乱码原因：
 *          由于在解析过程中默认使用的编码方式为ISO-8859-1(此编码不支持中文)，所以编译时会出现乱码。
 *                                   GET请求                      POST请求
 *      Tomcat 7 及以前，            会乱码                         会乱码
 *      Tomcat 8 及以上，           不会乱码                         会乱码。
 *
 *      无论什么版本的服务器，POST请求都会乱码。(request.setCharacterEncoding("UTF-8");)
 *      注：
 *          1.request.setCharacterEncoding("UTF-8") 只针对 POST 请求的乱码有效
 *          2.new String(request.getParameter("uname").getBytes("ISO-8859-1"), "UTF-8")针对任何请求方式。
 */
@WebServlet(name = "servlet/Test_8", value = "/servlet/Test_8")
public class Test_8 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  //设置请求编码的格式

        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        System.out.println("姓名：" + uname);
        System.out.println("密码：" + upwd);

        response.setContentType("text/html;charset=utf-8");  //设置输出编码格式
        response.getWriter().write("<p align=\"center\">啊这，登陆成功！</p><p align=\"center\">对，装作登录成功的界面。</p>");
    }
}
