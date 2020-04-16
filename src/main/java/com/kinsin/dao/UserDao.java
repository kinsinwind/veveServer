package com.kinsin.dao;

import com.kinsin.domain.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> getAllUser();

    /**
     * 注册
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 账号密码
     * @param user
     * @return
     */
    User getUserByAccountAndPassword(User user);

    /**
     * 邮箱密码
     * @param user
     * @return
     */
    User getUserByEmailAndPassword(User user);

    /**
     * 账号
     * @param user
     * @return
     */
    User getUserByAccount(User user);
    /**
     * 邮箱
     * @param user
     * @return
     */
    User getUserByEmail(User user);

    /**
     * 获得好友列表
     * @param user
     * @return
     */
    List<User> getFriendList(User user);

    /**
     * 修改登录状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 修改昵称
     * @param user
     */
    boolean updateNickName(User user);

    /**
     * 修改头像
     * @param user
     */
    boolean updateHeadIcon(User user);

}
