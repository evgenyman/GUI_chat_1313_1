package com.example.gui_chat_1313_1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    DataOutputStream out;

    @FXML
    Button myConnectButton;

    @FXML
    Button mySendButton;

    @FXML
    TextArea myTextArea;

    @FXML
    TextField myTextField;

    @FXML
    public void mySend(){
        String text = myTextField.getText();
        myTextArea.appendText(text+"\n");
        myTextField.clear();
        myTextField.requestFocus();
        try {
            out.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void myConnect(){
        try {
            Socket socket = new Socket("127.0.0.1",8178);
            System.out.println("Успешно подключились к серверу");
            out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        String response = null;
                        try {
                            response = in.readUTF();
                            myTextArea.appendText(response+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
            mySendButton.setDisable(false);
            myConnectButton.setDisable(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}