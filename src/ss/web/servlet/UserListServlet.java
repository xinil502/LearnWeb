package ss.web.servlet;

import com.mysql.cj.Session;
import ss.domain.User;
import ss.service.UserService;
import ss.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/admin/user_list_servlet")
public class UserListServlet extends HttpServlet {
    UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methood = request.getParameter("method");
        HttpSession session = request.getSession();
        Long online = (Long) this.getServletContext().getAttribute("online");
        request.setAttribute("online", online);
        Class c = this.getClass();
        try {
            Object obj = c.newInstance();
            Method method = c.getMethod(methood, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(obj, request, response);
        } catch (Exception e) {
            System.out.println("无法获取list查找方法！");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void select(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String uname = request.getParameter("uname");
        String uclass = request.getParameter("uclass");


        if (uname.equals("")) {
            if (uclass.equals("")) {
                findAll(request, response);
            } else {
                findUClass(request, response);
            }
        } else {
            if (uclass.equals("")) {
                findUName(request, response);
            } else {
                findUNameUClass(request, response);
            }
        }

    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) {
        //1.用UserService完成查询。

        List<User> users = service.findAll();
        //2.讲list存入request域。
        request.setAttribute("users", users);
        //转发到jsp页面.
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findUClass (HttpServletRequest request, HttpServletResponse response){
        String uClass = request.getParameter("uclass");
        List<User> list = service.findUClass(uClass);

        request.setAttribute("users", list);
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void findUName (HttpServletRequest request, HttpServletResponse response){
        String uName = request.getParameter("uname");
        List<User> list = service.findUName(uName);

        request.setAttribute("users", list);
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void findUNameUClass (HttpServletRequest request, HttpServletResponse response){
        String uClass = request.getParameter("uclass");
        String uName = request.getParameter("uname");
        List<User> list = service.findUNameUClass(uName, uClass);

        request.setAttribute("users", list);
        try {
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
