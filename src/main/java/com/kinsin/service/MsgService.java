package com.kinsin.service;

import com.kinsin.dao.MsgDao;
import com.kinsin.domain.Msg;
import com.kinsin.utils.Util;

import java.util.List;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/4/1.
 * PS: Not easy to write code, please indicate.
 */
public class MsgService implements MsgDao {
    MsgDao msgDao= Util.sqlSession.getMapper(MsgDao.class);
    @Override
    public void addMsg(Msg msg) {
        msgDao.addMsg(msg);
        Util.sqlSession.commit();
    }

    @Override
    public List<Msg> getMsgs(Msg msg) {
        return msgDao.getMsgs(msg);
    }

    @Override
    public int getNoReadNumber(Msg msg) {
        return msgDao.getNoReadNumber(msg);
    }

    @Override
    public Msg getLastContentAndTime(Msg msg) {
        return msgDao.getLastContentAndTime(msg);
    }

    @Override
    public void setIsRead(Msg msg) {
        msgDao.setIsRead(msg);
        Util.sqlSession.commit();
    }
}
