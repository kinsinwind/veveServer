package com.kinsin.service;

import com.kinsin.dao.UserDao;
import com.kinsin.domain.User;
import com.kinsin.utils.Util;

import java.util.List;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/3/29.
 * PS: Not easy to write code, please indicate.
 */
public class UserService implements UserDao {
    UserDao userDao = Util.sqlSession.getMapper(UserDao.class);
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public boolean addUser(User user) {
        try {
            userDao.addUser(user);
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public User getUserByAccountAndPassword(User user) {
        return userDao.getUserByAccountAndPassword(user);
    }

    @Override
    public User getUserByEmailAndPassword(User user) {
        return userDao.getUserByEmailAndPassword(user);
    }

    @Override
    public User getUserByAccount(User user) {
        return userDao.getUserByAccount(user);
    }

    @Override
    public User getUserByEmail(User user) {
        return userDao.getUserByEmail(user);
    }

    @Override
    public List<User> getFriendList(User user) {
        return userDao.getFriendList(user);
    }

    @Override
    public void updateStatus(User user) {
        userDao.updateStatus(user);
        Util.sqlSession.commit();
    }

    @Override
    public boolean updateNickName(User user) {
        try{
            userDao.updateNickName(user);
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateHeadIcon(User user) {
        try{
            userDao.updateHeadIcon(user);
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
