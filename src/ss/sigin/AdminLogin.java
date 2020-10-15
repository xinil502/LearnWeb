package ss.sigin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin_login_servlet")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");
        if(AdminDaoImpl.signIn(uname, upwd)){
            Cookie cookie = new Cookie("admin", "true");
            cookie.setMaxAge(360);
            response.addCookie(cookie);
            request.getRequestDispatcher("/admin/user_list_servlet").forward(request, response);
            System.out.println("登陆成功");
        }else{
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            System.out.println("登陆失败");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
