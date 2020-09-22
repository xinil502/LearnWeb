package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet的生命周期
 *
 */
@WebServlet(name = "servlet/Test_5", value = "/servlet/Test_5")
public class Test_5 extends HttpServlet {
    /**
     * 初始化方法（一个生命周期中只执行一次）
     * 系统方法，由服务器自动调用
     * 当请求到达Servlet容器时，该容器会判断Servlet对象是否存在。
     * 如果不存在，会创建实例并初始化。
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException{
        System.out.println("Servlet创建了。。。");
    }

    /**
     * 就绪/服务方法（处理请求数据——核心）
     * 系统方法，服务器自动调用
     * 当有请求到达Servlet时，就会调用该方法。
     * 该方法可以被多次调用。
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Servlet调用了。。。");
    }

    /**
     * 销毁方法（只会执行一次）
     * 系统方法，服务器自动调用
     * 当服务器关闭，或应用程序停止时，调用该方法。
     */
    public void destroy(){
        System.out.println("Servlet销毁了。。。");
    }
}
