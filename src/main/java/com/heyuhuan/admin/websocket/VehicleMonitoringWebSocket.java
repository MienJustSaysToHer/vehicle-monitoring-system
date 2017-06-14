package com.heyuhuan.admin.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 车辆监控 WebSocket 类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@ServerEndpoint(value = "/vehicle-monitoring")
public class VehicleMonitoringWebSocket {

    private static CopyOnWriteArraySet<VehicleMonitoringWebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    public static CopyOnWriteArraySet<VehicleMonitoringWebSocket> getWebSocketSet() {
        return webSocketSet;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("有连接加入");
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("有连接关闭");
    }

    @OnError
    public void onError(Session session, Throwable e) {
        System.out.println("发生错误");
        e.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        System.out.println("来自客户端的消息：" + message);

        sendMessage(message);
    }

    public static void sendMessage(String message) throws IOException {
        for (VehicleMonitoringWebSocket vehicleMonitoringWebSocket : webSocketSet) {
            vehicleMonitoringWebSocket.session.getBasicRemote().sendText(message);
        }
        //this.session.getAsyncRemote().sendText(message);
    }

}