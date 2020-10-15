package ss.dao;

import ss.domain.User;

import java.util.List;

/**
 * 用户操作的DAo
 */
public interface UserDao {

    public List<User> findAll();

    public boolean insertUser(User user);

    public boolean delete(int id);

    public boolean update(User user);

    public User getUser(int id);

    public List<User> findUName(String uname);

    public List<User> findUClass(String ucalss);

    public List<User> findUNameUClass(String uname, String uclass);
}
