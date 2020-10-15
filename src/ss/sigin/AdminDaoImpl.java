package ss.sigin;

import ss.domain.JDBCUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDaoImpl {

    public static boolean signIn(String userName, String userPassword){
        PreparedStatement ps = null;
        Connection conn = JDBCUtils.getConnection();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT aid, auser, apassword FROM admin");
            rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("auser").equals(userName)){
                    if(rs.getString("apassword").equals(userPassword)){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
