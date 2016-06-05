package com.mygaienko.rt_system.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enda1n on 05.06.2016.
 */
public class MessageContext {

    private static final List<ChatServerSocket> sockets = new ArrayList<>();

    public static void registerServerSocket(ChatServerSocket socket) {
        sockets.add(socket);
    }

    public static void resendMessage(String msg) throws IOException {
        for (ChatServerSocket socket : sockets) {
            socket.sendMessage(msg);
        }

    }
}
