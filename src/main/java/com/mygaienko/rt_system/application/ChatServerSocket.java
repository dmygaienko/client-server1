package com.mygaienko.rt_system.application;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by enda1n on 05.06.2016.
 */
@ServerEndpoint("/chat")
public class ChatServerSocket {

    private Session session;

    public ChatServerSocket() {
        MessageContext.registerServerSocket(this);
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("WebSocket opened: " + this.session.getId());
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException, EncodeException {
        System.out.println("Message received: " + msg);
        MessageContext.resendMessage(msg);
    }

    public void sendMessage(String msg) throws IOException {
        session.getBasicRemote().sendText(msg);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

}
