package com.kinsin.dao;

import com.kinsin.domain.Friend;
import com.kinsin.domain.Msg;

import java.util.List;

public interface MsgDao {
    void addMsg(Msg msg);

    List<Msg> getMsgs(Msg msg);

    int getNoReadNumber(Msg msg);

    Msg getLastContentAndTime(Msg msg);

    void setIsRead(Msg msg);
}
