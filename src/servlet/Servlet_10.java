package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/servlet_10")
public class Servlet_10 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 40;
        //通过一个对象，在内存中画图(验证码的图片对象)。
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//宽，高，图片类型

        //美化，优化图片。
        Graphics g = image.getGraphics();  //获得画笔对象
        //2.1填充背景颜色
        g.setColor(Color.PINK);  //设置画笔颜色
        g.fillRect(0,0,width,height);  //填充矩形
        //2.2画一个边框。
        g.setColor(Color.BLUE);  //设置画笔颜色
        g.drawRect(0,0,width-1,height-1);  //填充矩形
        //2.3写验证码
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        g.setColor(Color.BLUE);  //设置画笔颜色
        for(int i=1; i<5; ++i) {
            g.drawString("" + s.charAt(ran.nextInt(s.length())), 20*i, 20);
        }
        //2.4画干扰线
        g.setColor(Color.GREEN);
        for(int i=0; i<5; ++i) {
            g.drawLine(ran.nextInt(width), ran.nextInt(width), ran.nextInt(height), ran.nextInt(height));
        }

        //将图片输出到页面上展示
        ImageIO.write(image, "jpg",response.getOutputStream()); //图片对象，后缀名，任意流
    }
}
