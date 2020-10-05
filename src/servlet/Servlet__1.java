package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/servlet__1")
public class Servlet__1 extends HttpServlet {
    S__1 sql = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        sql = new S__1();
        try {
            Class servlet__1 = this.getClass();
            Method m = servlet__1.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(method);
            int re = (int)m.invoke(servlet__1.newInstance(), request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.getWriter().write("insert Ok");
        Enumeration<String> names = request.getParameterNames();
        Map<String, String> map = new HashMap<>();
        String table = null;
        while(names.hasMoreElements()){
            String name = names.nextElement();
            if(name == table){
                table = request.getParameter(name);
            }
            map.put(name, request.getParameter(name));
        }
        int re = sql.insert(table, map);
        if(re == -1){
            //添加失败
        }else if(re == 0){
            //未接受到数据
        }else if(re == 1) {
            //添加成功。
        }
    }

    void delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.getWriter().write("delete Ok");
        int re =  sql.delete(request.getParameter("table"), Integer.valueOf(request.getParameter("primaryKey")));
        if(re == -1){
            //删除失败
        }else{
            //删除re条数据。
        }
    }

    void update(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        response.getWriter().write("update Ok");

    }

    void select(HttpServletRequest request, HttpServletResponse response)  throws Exception{
        response.getWriter().write("select Ok");
    }
}
