package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * 获取请求参数
 */
@WebServlet(name = "servlet/Test_7", value = "/servlet/Test_7")
public class Test_7 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");             //获取指定名称的参数值(重点)
        String[] userNames = req.getParameterValues("userNames");   //获取指定名称的所有参数值。
        System.out.println("userName的参数值：" + userName);
        System.out.println("userNames的所有参数值：" + Arrays.toString(userNames));
    }
}
