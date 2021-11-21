package com.wiki.spring.websocket;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;


/**
 * <h1>websocket 类</h1>
 */
@Component
@ServerEndpoint("/ws/{token}")
@Slf4j
public class WebSocketServer {
    /**
     * 每个客户端一个token
     */
    private String token = "";
    //   存放所有客户端的Session 和 Token
    private static HashMap<String, Session> map = new HashMap<>();

    /**
     * 连接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        map.put(token, session);
        this.token = token;
        log.info("有新连接：token：{}，session id：{}，当前连接数：{}", token, session.getId(), map.size());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        map.remove(this.token);
        log.info("连接关闭，token：{}，session id：{}！当前连接数：{}", this.token, session.getId(), map.size());
    }

    /**
     * 收到消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到消息：{}，内容：{}", token, message);
    }

    /**
     * 连接错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误", error);
    }

    /**
     * 群发消息
     */

    public void sendInfo(String s, String message) {
//        log.error("开始推送：{}，内容：{},s {}", token, message,s);
        log.error(" message：{} ",   message  );
        log.error(" s：{} ",   s  );
        log.error("map.keySet()-------------->{}",map.keySet());
        for (String token : map.keySet()) {
            log.error("11111111111111111111111");
            Session session = map.get(token);
            log.error("session：{} ", session);
            log.error("session.getBasicRemote()：{} ", session.getBasicRemote());
            try {
                // 发送信息
                session.getBasicRemote().sendText(s);
            } catch (IOException e) {
                log.error("推送消息失败：{}，内容：{}", token, s);
            }
            log.info("推送消息：{}，内容：{}", token, s);
        }
    }

}
