package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 获取请求头数据
 */
@WebServlet("/Servlet_3")
public class Servlet_3 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");  //设置输出编码格式

        Enumeration<String> headerNames = request.getHeaderNames(); //获取所有请求头名称

        while(headerNames.hasMoreElements()){ //输出所有请求头数据
            String name = headerNames.nextElement();
            System.out.println(name + " = " + request.getHeader(name));
            response.getWriter().write("<br>" + name + " = " + request.getHeader(name));
        }

        //判断agent的浏览器版本
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            System.out.println("这是谷歌");
        }else if(agent.contains("Firefox")){
            System.out.println("这是火狐");
        }

        /**
         *获取referer
         * 当前页面访问：null
         * 其他页面访问：显示他的url
         */
        String referer = request.getHeader("referer");
        System.out.println(referer);
        if(referer != null){
            if(referer.contains("/LearnWeb")){
                System.out.println("正常访问");
            }else{
                System.out.println("想访问吗，自己写个页面吧。");
            }
        }

    }
}
