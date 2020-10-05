package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter 使用
 */
@WebFilter(value = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class Servlet_13 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对request消息的拦截
        System.out.println("过滤器filter_1正在过滤请求");

        filterChain.doFilter(servletRequest, servletResponse);//放行

        //对response对象的增强。
        System.out.println("过滤器filter_1正在过滤响应");
    }

    @Override
    public void destroy() {

    }
}
