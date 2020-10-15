package ss.service.impl;

import ss.dao.UserDao;
import ss.dao.impl.UserDaoImpl;
import ss.domain.User;
import ss.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        /**
         * 调用DAO完成查询
         */
        return dao.findAll();
    }

    @Override
    public boolean insert(User user) {
        return dao.insertUser(user);
    }

    @Override
    public boolean delete(String id) {
        int iId = Integer.valueOf(id);
        return dao.delete(iId);
    }

    @Override
    public boolean update(User user) {
        return dao.update(user);
    }

    @Override
    public User getUser(String id) {
        return dao.getUser(Integer.valueOf(id));
    }

    @Override
    public List<User> findUName(String uName) {
        return dao.findUName(uName);
    }

    @Override
    public List<User> findUClass(String uClass) {
        return dao.findUClass(uClass);
    }

    @Override
    public List<User> findUNameUClass(String uName, String uClass) {
        return dao.findUNameUClass(uName, uClass);
    }

}
