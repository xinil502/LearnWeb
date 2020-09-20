package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlet/Test_4", value = "/servlet/Test_4")
public class Test_4 extends HttpServlet {
    /**
     * service底层需要进一步判断是Get还是Post请求，就需要把doPost和doGet代码都重写一遍。
     *
     * 将执行都指向doGet方法，可以省略一些代码。
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //代码
        System.out.println("你现在看到的是Get请求的Request。");
    }
}
