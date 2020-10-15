package ss.dao;

import ss.domain.User;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * 用户操作的DAo
 */
public interface UserDao {
    /**
     * 处理 DQL语句
     * 并将返回的结果集转为 User列表。
     * @param ps
     * @return
     */
    public List<User> toList(PreparedStatement ps);

    /**
     * 查询 user表中的所有数据。
     * @return
     */
    public List<User> findAll();

    /**
     * 将 User JavaBean的对象加入到 user表中。
     * @param user
     * @return
     */
    public boolean insertUser(User user);

    /**
     * 根据 id删除数据库中的记录
     * @param id
     * @return
     */
    public boolean delete(int id);

    /**
     * 根据 User类对象的 id属性，修改数据库中的指定记录
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 根据 id获取指定的 user对象
     * @param id
     * @return
     */
    public User getUser(int id);

    /**
     * 根据 uname字段，查询user表中的记录
     * @param uname
     * @return
     */
    public List<User> findUName(String uname);

    /**
     * 根据 uclass字段， 查询 user表中的记录
     * @param ucalss
     * @return
     */
    public List<User> findUClass(String ucalss);

    /**
     * 根据 uname， uclass字段， 查询 user表中的记录
     * @param uname
     * @param uclass
     * @return
     */
    public List<User> findUNameUClass(String uname, String uclass);
}
