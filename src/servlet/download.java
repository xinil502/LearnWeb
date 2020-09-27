package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 文件下载案例：
 *      需求：
 *          1.页面显示下载超链接
 *          2.点击超链接后弹出下载提示框
 *          3.完成图片文件下载。
 *      分析：
 *          1.超链接指向的资源如果能被浏览器解析，则在浏览器中展示，不能解析则下载，不满足需求。
 *          2.任何资源都必须弹出下载提示框。
 *          3.使用响应头设置资源的打开方式：
 *              content-disposition:attachment;filename=xxx
 *      步骤：
 *          1.定义页面，编辑超链接href属性，指向Servlet，传递资源名称参数：filename。
 *          2.定义Servlet：
 *              1.获取文件名称。
 *              2.加载文件进内存。（通过真实路径获取）
 *              3.指定response的响应头,以附件/弹窗的形式：content-disposition:attachment;filename=xxx
 *              4.使用response的输出流中。
 */
@WebServlet("/downloadServlet")
public class download extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数：文件名称
        String filename = request.getParameter("filename");

        //2.使用字节输入流加载文件进内存。
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/WEB-INF/file/" + filename);
        FileInputStream fis = new FileInputStream(path);

        //3.设置response的响应头
        response.setContentType(context.getMimeType(filename)); //设置响应的类型
        response.setHeader("content-disposition","attachment;filename=" + filename);


        //使用工具类，编码对应的文件名。

        //4.将输入流的数据写出到输出流中。（文件对拷）
        ServletOutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while((len = fis.read(buff)) != -1){
            out.write(buff);
        }
        fis.close();
    }
}
