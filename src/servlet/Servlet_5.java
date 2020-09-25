package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;


/**
 * 通用方法：获取请求参数
 */
@WebServlet("/Servlet_5")
public class Servlet_5 extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userName = request.getParameter("username");             //获取指定名称的参数值
        String[] userNames = request.getParameterValues("userName");   //获取指定名称的所有参数值。(一键多值)
        System.out.println("userName的参数值：" + userName);
        System.out.println("userNames的所有参数值：" + Arrays.toString(userNames));

        //获取所有的参数名
        Enumeration<String> e = request.getParameterNames();
        while(e.hasMoreElements()){
            String key = e.nextElement();
            System.out.println(key + " = " + request.getParameter(key));
        }

        //获取所有键值对参数的Map集合
        Map<String, String[]> map = request.getParameterMap();
        for(Iterator<String> ite = map.keySet().iterator(); ite.hasNext();){
            String key = ite.next();
            System.out.println(key + " = " + Arrays.toString(map.get(key)));
        }
    }
}
