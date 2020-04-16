package com.kinsin.sockets;

import com.kinsin.domain.Friend;
import com.kinsin.domain.Msg;
import com.kinsin.domain.RequestFriend;
import com.kinsin.domain.User;
import com.kinsin.service.FriendService;
import com.kinsin.service.MsgService;
import com.kinsin.service.RequestFriendService;
import com.kinsin.service.UserService;
import com.kinsin.utils.CurrentTime;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/3/27.
 * PS: Not easy to write code, please indicate.
 */
@ServerEndpoint("/friendSocket/{names}")
public class FriendSocket {
    private Session session;
    private String names;
    private static Map<String, FriendSocket> websocketServerMap = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("names") String names, Session session) {
        this.session = session;
        this.names = names;
        websocketServerMap.put(names, this);
        System.out.println("有新连接(好友请求)" + names);
    }

    @OnClose
    public void onClose() {
        websocketServerMap.remove(names);  //从map中删除
        System.out.println("连接关闭(好友请求)");
        for (String val : websocketServerMap.keySet()
        ) {
            System.out.println("------------------" + val);
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        System.out.println("接收到的消息(好友请求)：" + msg);
        //使用json消息
        JSONObject jsonObject = new JSONObject(msg);
        UserService userService=new UserService();
        String header= jsonObject.getString("header");//头消息，判断是请求还是回应
        String uid1 = jsonObject.getString("uid1");//好友请求发送人
        String uid2 = jsonObject.getString("uid2");//好友请求接收人

        if("心跳".equals(uid2)){//心跳机制
            if (websocketServerMap.get(uid1) != null) {
                try {
                    websocketServerMap.get(uid1).sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        if("request".equals(header)){//接收请求
            RequestFriend requestFriend=new RequestFriend();
            requestFriend.setUid1(uid1);
            requestFriend.setUid2(uid2);
            requestFriend.setIswork(1);//设置状态为工作中（未失效）
            requestFriend.setNick_name(userService.getUserByAccount(new User(uid1)).getNick_name());//设置请求发起人的昵称
            requestFriend.setSend_time(CurrentTime.getTimes());
            //发送给指定用户
            try {
                //持久化
                RequestFriendService requestFriendService=new RequestFriendService();
                requestFriendService.addRequest(requestFriend);
                //发送给请求人是否成功，只要检测到是自己发送的就可以判断是否成功
                if (websocketServerMap.get(uid1) != null) {
                    websocketServerMap.get(uid1).sendMessage(msg);
                }
                if(websocketServerMap.get(uid2)!=null){
                    websocketServerMap.get(uid2).sendMessage(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {//接收回应，respond
            //此处完成两个操作
            //第一：推送消息给用户，给请求发送者发消息：你的好友请求已通过或被拒绝，给接收者：该请求是否处理了，处理状态
            //第一个操作暂时没有实现，因为需要开启一个新的模块（收件箱模块，用来接收各类消息）
            //第二：将消息更新到数据库
            try{
                Friend friend=new Friend();
                //得到是否同意
                String isAcc = jsonObject.optString("isAcc");
                if("YES".equals(isAcc)){
                    //同意了好友请求则将其添加到好友表中
                    FriendService friendService=new FriendService();
                    friendService.addFriend(new Friend(uid1,uid2));
                }else {
                    //等待开启一个新的模块（收件箱模块，用来接收各类消息）
                }
                //持久化
                RequestFriendService requestFriendService=new RequestFriendService();
                RequestFriend requestFriend=new RequestFriend();
                requestFriend.setUid1(uid1);
                requestFriend.setUid2(uid2);
                requestFriend.setIswork(0);
                requestFriendService.updateIsWork(requestFriend);//只要回应了，无论是同意还是拒绝都需要将其标记为失效状态

                //给用户返回了头消息为respond的，即通知UI刷新好友列表
                if (websocketServerMap.get(uid1) != null) {
                    websocketServerMap.get(uid1).sendMessage(msg);
                }
                if(websocketServerMap.get(uid2)!=null){
                    websocketServerMap.get(uid2).sendMessage(msg);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }



    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误(好友请求)");
        System.out.println(error.getMessage());
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
