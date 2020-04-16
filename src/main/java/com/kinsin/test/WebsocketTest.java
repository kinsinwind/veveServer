package com.kinsin.test;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Author by kinsin, Email kinsinlin@foxmail.com, Date on 2020/3/27.
 * PS: Not easy to write code, please indicate.
 */
@ServerEndpoint("/websockets")
public class WebsocketTest {
    private Session session;
    private static CopyOnWriteArraySet<WebsocketTest> webSocketSet = new CopyOnWriteArraySet<WebsocketTest>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("有新连接"+webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        System.out.println("连接关闭");
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        System.out.println("接收到的消息：" + msg);
        //群发消息
        for (WebsocketTest item : webSocketSet) {
            try {
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
}
