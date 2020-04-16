package com.kinsin.domain;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/3/30.
 * PS: Not easy to write code, please indicate.
 */
public class Friend {
    private String uid;
    private String friend_id;
    private String remark;

    public Friend() {
    }

    public Friend(String uid, String friend_id) {
        this.uid = uid;
        this.friend_id = friend_id;
    }

    public Friend(String uid, String friend_id, String remark) {
        this.uid = uid;
        this.friend_id = friend_id;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "uid='" + uid + '\'' +
                ", friend_id='" + friend_id + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
