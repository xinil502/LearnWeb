package ss.web.servlet;

import com.sun.deploy.net.HttpResponse;
import ss.domain.User;
import ss.service.UserService;
import ss.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

@WebServlet("/admin/user_servlet")
public class UserServlet extends HttpServlet {
    UserService service = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Class c = this.getClass();
        String method = request.getParameter("method");
        try {
            Object obj = c.newInstance();
            Method m = c.getMethod(""+method, HttpServletRequest.class, HttpServletResponse.class);
            m.invoke(obj, request, response);
        } catch (Exception e) {
            System.out.println("无法获取增删改方法！");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    public void insert(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        User user = new User();

        try {
            if(request.getParameter("uid").equals("")|| request.getParameter("uname").equals("") ){
                request.getRequestDispatcher("/insert_false.jsp").forward(request,response);
                return;
            }

            user.setUid(Integer.valueOf(request.getParameter("uid")));  //private int uid;
            user.setUname(request.getParameter("uname"));               //private String uname;
            user.setUage(Integer.valueOf(request.getParameter("uage")));//private int uage;
            user.setUgender(request.getParameter("ugender"));           //private String ugender;
            user.setUadress(request.getParameter("uadress"));           //private String uadress;
            user.setUclass(request.getParameter("uclass"));             //private String uclass;
            boolean insert = service.insert(user);

            if(insert){
                request.getRequestDispatcher("/admin/user_list_servlet?method=findAll").forward(request, response);
            }else{
                request.getRequestDispatcher("/insert_false.jsp").forward(request,response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            try {
                request.getRequestDispatcher("/insert_false.jsp").forward(request,response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");

        String id = request.getParameter("id");

        service.delete(id);

        try {
            request.getRequestDispatcher("/admin/user_list_servlet?method=findAll").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        User user = new User();
        if(request.getParameter("uid").equals("") || request.getParameter("uname").equals("")){
            try {
                request.getRequestDispatcher("/update_false.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setUid(Integer.valueOf(request.getParameter("uid")));
            user.setUname(request.getParameter("uname"));
            user.setUage(Integer.valueOf(request.getParameter("uage")));
            user.setUgender(request.getParameter("ugender"));
            user.setUadress(request.getParameter("uadress"));
            user.setUclass(request.getParameter("uclass"));
        }

        boolean update = service.update(user);
        try {
            if(update){
                System.out.println("修改成功");
                request.getRequestDispatcher("/admin/user_list_servlet?method=findAll").forward(request, response);
            }else{
                request.getRequestDispatcher("/update_false.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUser(HttpServletRequest request, HttpServletResponse response){
        try {
            String id = request.getParameter("id");
            User u = service.getUser(id);
            System.out.println(u.getUname() + "已经查到，准备修改。");
            request.setAttribute("user", u);
            request.getRequestDispatcher("/update.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
