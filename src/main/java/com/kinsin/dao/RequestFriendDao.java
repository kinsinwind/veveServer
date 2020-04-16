package com.kinsin.dao;

import com.kinsin.domain.RequestFriend;

import java.util.List;

public interface RequestFriendDao {
    /**
     * 根据uid2获得请求记录
     * @param requestFriend
     * @return
     */
    List<RequestFriend> getRequestByUid2(RequestFriend requestFriend);

    /**
     * 增加请求
     * @param requestFriend
     * @return
     */
    boolean addRequest(RequestFriend requestFriend);

    /**
     * 更新工作状态
     * @param requestFriend
     * @return
     */
    boolean updateIsWork(RequestFriend requestFriend);

    /**
     * 获得所有未处理好友请求
     * @param requestFriend
     * @return
     */
    int getRequestFriendNumber(RequestFriend requestFriend);

    /**
     * 查询是否有正在工作的请求
     * @param requestFriend
     * @return
     */
    RequestFriend getRequestByUid1and2(RequestFriend requestFriend);
}
