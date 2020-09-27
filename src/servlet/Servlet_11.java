package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * 获取：
 * 通过request对象获取：
 *   request.getServletContext();
 * 通过HttpServlet来获取
 *   this.getServletContext();
 *
 * 使用：
 *      获取MIME类型：
 *          String getMimeType(String file);
 *      域对象，共享数据
 *          setAtteribute(String name, Object value);
 *          getAtteribute(String name);
 *          removeAtteribute(String name);
 *      获取文件的真实路径(服务器)路径
 *
 */
@WebServlet("/servlet_11")
public class Servlet_11 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //通过request对象获取
        ServletContext context1 = request.getServletContext();
        //通过HttpServlet来获取
        ServletContext context2 = this.getServletContext();

        System.out.println("context1 = " + context1);
        System.out.println("context2 = " + context2);
        System.out.println(context1 == context2); //true

        //通过文件名，获取MIME类型：
        String mimeType = context1.getMimeType("a.jpg");
        System.out.println(mimeType);

        //获取文件的真实路径。
        String realPath_Mysql = context1.getRealPath("/WEB-INF/classes/servlet/Mysql.properties");
        Properties p = new Properties();
        FileReader fr = new FileReader(new File(realPath_Mysql));
        p.load(fr);
        fr.close();
        System.out.println("url = " + p.getProperty("url"));
        System.out.println("user = " + p.getProperty("user"));
        System.out.println("password = " + p.getProperty("password"));

        Scanner scanner = new Scanner(System.in);
    }
}
