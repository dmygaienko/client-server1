package com.mygaienko.rt_system.application;

import com.mygaienko.rt_system.controller.Listener;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by enda1n on 05.06.2016.
 */
@ClientEndpoint
public class ChatClientSocket {
    CountDownLatch latch = new CountDownLatch(1);
    private Session session;

    private Listener listener;

    public ChatClientSocket(Listener listener) {
        this.listener = listener;
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to server");
        this.session = session;
        latch.countDown();
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("Message received from server:" + message);
        listener.onMessage(message);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void sendMessage(String str) {
        try {
            session.getBasicRemote().sendText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
