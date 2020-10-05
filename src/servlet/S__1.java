package servlet;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class S__1 {
    Connection conn = null;
    PreparedStatement ps = null;
    public S__1(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            FileInputStream fis = new FileInputStream("D:/study/language/JAVA/Web/src/servlet/Mysqlproperties");
            Properties p = new Properties();
            p.load(fis);
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int insert(String tableName, Map<String, String> map){
        if(map == null || map.size() == 0){
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        try {
            ps = conn.prepareStatement("INSERT INTO ?(" + sb.toString() +")" + "vlaue(" + sb.toString() + ")");
            ps.setString(1, tableName);
            Iterator<String> ite = map.keySet().iterator();
            for(int i=0; i<map.size() && ite.hasNext(); ++i){
                String key = ite.next();
                ps.setString(2+i, key);
                ps.setString(2+i+map.size(), (String)map.get(key));
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int delete(String tableName, int primaryKey){
        try {
            ps = conn.prepareStatement("DELETE FROM ? WHERE id = ?");
            ps.setString(1, tableName);
            ps.setInt(2, primaryKey);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int update(String tableName, int primaryKey, Map<String, String> map){
        if(map == null || map.size() == 0){
            return 0;
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<map.size(); ++i){
            sb.append(", ? = ?");
        }
        try {
            ps = conn.prepareStatement("UPADTE ? SET ? = ?" + sb.toString() + "WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public ResultSet select(String table){
        ResultSet rs = null;
        return rs;
    }
}
