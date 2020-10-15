package ss.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter({"/admin/*","/list.jsp","/insert.jsp","/update.jsp"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对request消息的拦截
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies) {
            if (c.getName().equals("admin")) {
                if(c.getValue().equals("true")){
                    System.out.println("-已登录的用户-");
                    filterChain.doFilter(servletRequest, servletResponse);//放行
                    return; //
                } else {
                    request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
                    return; //
                }
            }
        }
        request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
