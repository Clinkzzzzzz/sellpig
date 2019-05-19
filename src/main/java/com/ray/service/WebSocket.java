package com.ray.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void opOpen(Session session){
        this.session = session;
        webSockets.add(this);
        log.info("【webSocket消息】有新的链接，总数：{}",webSockets.size());
    }

    @OnClose
    public void opClose(){
        webSockets.remove(this);
        log.info("【webSocket消息】链接断开，总数：{}",webSockets.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【webSocket消息】收到客户端发来的消息:{}",message);
    }

    public void sendMessage(String message){
        for(WebSocket webSocket:webSockets){
            log.info("【webSocket消息】广播消息，message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
