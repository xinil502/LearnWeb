package ss.service;

import ss.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {

    /**
     * 查询所有用户的数据。
     * @return
     */
    public List<User> findAll();

    /**
     * 增加一条用户的数据
     * @param user
     * @return
     */
    public boolean insert(User user);

    /**
     * 删除 id对应的记录
     * @param id
     * @return
     */
    public boolean delete(String id);

    /**
     * 更新 User记录，根据 id去替换
     * @param user
     * @return
     */
    public boolean update(User user);

    /**
     * 获取 User记录
     * @param id
     * @return
     */
    public User getUser(String id);

    /**
     * 获取指定班级的所有记录条数。
     * @param uClass
     * @return
     */
    public List<User> findUClass(String uClass);

    /**
     * 获取指定姓名的所有记录条数
     * @param uName
     * @return
     */
    public List<User> findUName(String uName);

    /**
     * 获取指定班级和指定姓名的所有记录条数。
     * @param uName
     * @param uClass
     * @return
     */
    public List<User> findUNameUClass(String uName, String uClass);

}
