package com.kinsin.sockets;

import com.kinsin.domain.Msg;
import com.kinsin.service.MsgService;
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
@ServerEndpoint("/websocket/{names}")
public class WebsocketServer {
    private Session session;
    private String names;
    private static Map<String, WebsocketServer> websocketServerMap = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("names") String names, Session session) {
        this.session = session;
        this.names = names;
        websocketServerMap.put(names, this);
        System.out.println("有新连接(聊天)" + names);
    }

    @OnClose
    public void onClose() {
        websocketServerMap.remove(names);  //从map中删除
        System.out.println("连接关闭(聊天)");
        for (String val : websocketServerMap.keySet()
        ) {
            System.out.println("------------------" + val);
        }
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        System.out.println("接收到的消息(聊天)：" + msg);

        //使用json消息
        JSONObject jsonObject = new JSONObject(msg);
        String uid1 = jsonObject.getString("uid1");
        String uid2 = jsonObject.getString("uid2");
        String content = jsonObject.getString("content");

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

        Msg msgs = new Msg();
        msgs.setUid1(uid1);
        msgs.setUid2(uid2);
        msgs.setContent(content);
        msgs.setIsRead(0);
        msgs.setSend_time(CurrentTime.getTimes());
        //持久化
        MsgService msgService = new MsgService();
        msgService.addMsg(msgs);
        //发送给指定用户
        try {
            if (websocketServerMap.get(uid1) != null) {
                websocketServerMap.get(uid1).sendMessage(msg);
            }
            if(websocketServerMap.get(uid2)!=null){
                websocketServerMap.get(uid2).sendMessage(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //使用字符串消息
//        String header = msg.split("#@#@#@#")[0];
//        String content = msg.split("#@#@#@#")[1];
//
//        String uid1 = header.split(",")[0];
//        String uid2 = header.split(",")[1];
//
//        if("all".equals(uid2)){//群发消息
//            content = uid1+"#@#@#@#"+content;
//            for (WebsocketServer val:websocketServerMap.values()
//            ) {
//                try {
//                    val.sendMessage(content);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }else {//一对一
//            try {
////                content = uid1+"#@#@#@#"+content;
//                System.out.println("准备发送");
//                if(websocketServerMap.get(uid1)!=null){
//                    websocketServerMap.get(uid1).sendMessage(msg);
//                }
//                if(websocketServerMap.get(uid2)!=null){
//                    websocketServerMap.get(uid2).sendMessage(msg);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }


    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误(聊天)");
        System.out.println(error.toString());
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
