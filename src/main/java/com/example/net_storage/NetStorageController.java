package com.example.net_storage;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetStorageController {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    String filename;

    @FXML
    Button logBtn;
    @FXML
    TextField log;
    @FXML
    PasswordField pass;
    @FXML
    Label lblLs;
    @FXML
    Label lblU;
    @FXML
    Label lblP;
    @FXML
    Label lblCu;
    @FXML
    Label lblCd;
    @FXML
    Button dwlBtn;
    @FXML
    Button delBtn;
    @FXML
    Button selBtn;
    @FXML
    Button uplBtn;


    @FXML
    public void initialize() {
        setAuthorized(false);
        try {
            socket = new Socket("localhost", 45001);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(true);
        }
        catch (IOException e) {
            lblLs.setVisible(true);
            lblLs.setText("*Не удалось подключиться к серверу");
            e.printStackTrace();
        }
    }


    public void setAuthorized(boolean b) {
        if (b) {
            lblU.setVisible(false);
            lblP.setVisible(false);
            pass.setVisible(false);
            logBtn.setVisible(false);
            log.setVisible(false);
            lblCd.setVisible(true);
            lblCu.setVisible(true);
            dwlBtn.setVisible(true);
            delBtn.setVisible(true);
            selBtn.setVisible(true);
            uplBtn.setVisible(true);
        }
        else {
            lblLs.setText("Введите логин и пароль");
            lblU.setVisible(true);
            lblP.setVisible(true);
            pass.setVisible(true);
            logBtn.setVisible(true);
            log.setVisible(true);
            lblCd.setVisible(false);
            lblCu.setVisible(false);
            dwlBtn.setVisible(false);
            delBtn.setVisible(false);
            selBtn.setVisible(false);
            uplBtn.setVisible(false);
        }
    }
}