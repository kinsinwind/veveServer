package com.kinsin.service;

import com.kinsin.dao.FriendDao;
import com.kinsin.dao.UserDao;
import com.kinsin.domain.Friend;
import com.kinsin.utils.Util;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/3/30.
 * PS: Not easy to write code, please indicate.
 */
public class FriendService implements FriendDao {
    FriendDao friendDao = Util.sqlSession.getMapper(FriendDao.class);
    @Override
    public boolean addFriend(Friend friend) {
        String account1 = friend.getUid();
        String account2 = friend.getFriend_id();
        try {
            friendDao.addFriend(new Friend(account1,account2));
            friendDao.addFriend(new Friend(account2,account1));
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Friend getFriendByUidAndFid(Friend friend) {
        return friendDao.getFriendByUidAndFid(friend);
    }
}
