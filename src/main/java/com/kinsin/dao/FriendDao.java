package com.kinsin.dao;

import com.kinsin.domain.Friend;

public interface FriendDao {
    /**
     * 添加好友
     * @param friend
     * @return
     */
    boolean addFriend(Friend friend);

    /**
     * 根据uid和fid寻找好友
     * @param friend
     * @return
     */
    Friend getFriendByUidAndFid(Friend friend);
}
