package servlet;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class User {
    int userId;
    String userName;
    String userPassword;
    private boolean signIn;

    public User(String uname, String upassword) {
        this.userName = uname;
        this.userPassword = upassword;
        userId = -1;
        signIn = false;
    }

    public boolean signIn() throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties p = new Properties();
            p.load(new FileInputStream("D:\\study\\language\\JAVA\\Web\\src\\servlet\\Mysql.properties"));
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            conn = DriverManager.getConnection(url, user, password);
            ps = conn.prepareStatement("SELECT uid, uname, upassword FROM user");
            rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("uname").equals(userName)){
                    break;
                }
            }
            if(rs.getString("upassword").equals(userPassword)){
                //登陆成功
                signIn = true;
                return true;
            }else{
                //登陆失败
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
