package ss.domain;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {  //JDBC工具类
    private static DataSource ds = null;

    static{

        Properties properties = new Properties();
        InputStream is = JDBCUtils.class.getResourceAsStream("druid.properties");
        try {
            properties.load(is);  //加载配置文件
            ds = DruidDataSourceFactory.createDataSource(properties); //获取连接池对象
            System.out.println("成功获取连接池");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){  //获取连接
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection){  //归还连接。
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){ //获取连接池
        return ds;
    }
}
