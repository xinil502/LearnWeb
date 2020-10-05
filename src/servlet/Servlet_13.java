package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Filter 使用
 */
@WebFilter("/*")
public class Servlet_13 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器filter_1正在执行中。。。");
        filterChain.doFilter(servletRequest, servletResponse);//放行
    }

    @Override
    public void destroy() {

    }
}
