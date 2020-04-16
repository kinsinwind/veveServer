package com.kinsin.service;

import com.kinsin.dao.MsgDao;
import com.kinsin.dao.RequestFriendDao;
import com.kinsin.domain.RequestFriend;
import com.kinsin.utils.Util;

import java.util.List;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/4/3.
 * PS: Not easy to write code, please indicate.
 */
public class RequestFriendService implements RequestFriendDao {
    RequestFriendDao requestFriendDao=Util.sqlSession.getMapper(RequestFriendDao.class);
    @Override
    public List<RequestFriend> getRequestByUid2(RequestFriend requestFriend) {
        return requestFriendDao.getRequestByUid2(requestFriend);
    }

    @Override
    public boolean addRequest(RequestFriend requestFriend) {
        try{
            requestFriendDao.addRequest(requestFriend);
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateIsWork(RequestFriend requestFriend) {
        try{
            requestFriendDao.updateIsWork(requestFriend);
            Util.sqlSession.commit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public int getRequestFriendNumber(RequestFriend requestFriend) {
        return requestFriendDao.getRequestFriendNumber(requestFriend);
    }

    @Override
    public RequestFriend getRequestByUid1and2(RequestFriend requestFriend) {
        return requestFriendDao.getRequestByUid1and2(requestFriend);
    }
}