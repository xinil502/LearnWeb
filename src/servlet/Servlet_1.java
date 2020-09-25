package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Servlet_1")
public class Servlet_1 implements Servlet {
    /**
     * 在Servlet被创建时执行，只会执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("服务器启动");
    }

    /**
     * 获取ServletConfig对象
     * ServletConfig：Servlet的配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     *
     * 提供服务的方法
     * 每一次Servlet被访问时，都会执行。
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Hello Servlet.");
    }

    /**
     * 获取Servlet的信息：
     *      版本，作者等等 (不常用)
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁方法
     * 在Servlet被销毁时执行一次。
     */
    @Override
    public void destroy() {
    }
}
