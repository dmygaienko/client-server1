package com.mygaienko.rt_system.controller;

import com.mygaienko.rt_system.application.ChatClientSocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by enda1n on 23.05.2016.
 */
public class ChatController implements Initializable, Listener {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @FXML
    TextArea msgTextArea;

    @FXML
    TextArea chatTextArea;

    @FXML
    TextField nicknameTextField;

    private ChatClientSocket socket;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            initClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initClient() throws URISyntaxException, IOException, DeploymentException, InterruptedException {
        String dest = "ws://localhost:8080/chat";
        socket = new ChatClientSocket(this);
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(socket, new URI(dest));

        socket.getLatch().await();
    }

    @FXML
    private void sendMsg(ActionEvent event) {
        socket.sendMessage(nicknameTextField.getText() + ":" + msgTextArea.getText());
        msgTextArea.clear();
        logger.info("sendMsg");
    }

    @FXML
    private void start(ActionEvent event) {
        logger.info("process is started");
    }

    @Override
    public void onMessage(String msg) {
        String text = chatTextArea.getText();
        chatTextArea.setText(text + (text.isEmpty() ? "" : "\n") + msg);
    }
}
