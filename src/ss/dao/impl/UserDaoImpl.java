package ss.dao.impl;

import ss.dao.UserDao;
import ss.domain.JDBCUtils;
import ss.domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public List<User> toList(PreparedStatement ps) throws SQLException {
        ResultSet resultSet = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User u = new User();
            u.setId(resultSet.getInt(1));
            u.setUid(resultSet.getInt(2));
            u.setUname(resultSet.getString(3));
            u.setUage(resultSet.getInt(4));
            u.setUgender(resultSet.getString(5));
            u.setUadress(resultSet.getString(6));
            u.setUclass(resultSet.getString(7));
            list.add(u);
        }
        resultSet.close();
        return list;
    }

    @Override
    public synchronized List<User> findAll() {
        //jdbc查询数据库
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user ORDER BY uid");

            List<User> list = toList(ps);

            ps.close();
            JDBCUtils.close(connection);
            return list;
        } catch (SQLException e) {
            JDBCUtils.close(connection);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized boolean insertUser(User user) {
        Connection connection = JDBCUtils.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user(uid, uname, uage, ugender, uadress, uclass) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setInt(1, user.getUid());
            ps.setString(2, user.getUname());
            ps.setInt(3, user.getUage());
            ps.setString(4, user.getUgender());
            ps.setString(5, user.getUadress());
            ps.setString(6, user.getUclass());
            int n = ps.executeUpdate();
            System.out.println("成功插入" + n + "条数据");
            ps.close();
            JDBCUtils.close(connection);
            return 1 == n;
        } catch (SQLException e) {
            JDBCUtils.close(connection);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean delete(int id) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, id);
            int re = ps.executeUpdate();

            ps.close();
            JDBCUtils.close(conn);

            return 1 == re;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtils.close(conn);
            return false;
        }
    }

    @Override
    public synchronized boolean update(User user) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE user SET uid=?, uname=?, uage=?, ugender=?, uadress=?, uclass=? WHERE id = ?");
            ps.setInt(1, user.getUid());
            ps.setString(2, user.getUname());
            ps.setInt(3, user.getUage());
            ps.setString(4, user.getUgender());
            ps.setString(5, user.getUadress());
            ps.setString(6, user.getUclass());
            ps.setInt(7, user.getId());

            int re = ps.executeUpdate();
            ps.close();
            JDBCUtils.close(conn);
            return 1 == re;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtils.close(conn);
            return false;
        }
    }

    @Override
    public synchronized User getUser(int id) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;
        System.out.print("准备查询————");
        try {
            ps = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setInt(1, id);
            List<User> list = toList(ps);

            ps.close();
            JDBCUtils.close(conn);
            return list.get(0);
        } catch (Exception e) {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtils.close(conn);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUName(String uname) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM user WHERE uname = ?");
            ps.setString(1,uname);

            List<User> users = toList(ps);

            ps.close();
            JDBCUtils.close(conn);
            return users;
        } catch (SQLException e) {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            JDBCUtils.close(conn);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUClass(String ucalss) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM user WHERE uclass = ?");
            ps.setString(1, ucalss);

            List<User> users = toList(ps);

            ps.close();
            JDBCUtils.close(conn);
            return users;
        } catch (SQLException e) {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtils.close(conn);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findUNameUClass(String uname, String uclass) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM user WHERE uname = ? AND uclass = ?");
            ps.setString(1, uname);
            ps.setString(2, uclass);

            List<User> users = toList(ps);

            ps.close();
            JDBCUtils.close(conn);
            return users;
        } catch (SQLException e) {

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtils.close(conn);
            e.printStackTrace();
            return null;
        }
    }
}