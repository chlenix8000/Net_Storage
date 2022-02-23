package com.example.net_storage;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;

public class ClientController {
//    Socket socket;
//    DataInputStream in;
//    DataOutputStream out;
//    String filename;

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
    public void initialize() throws InterruptedException {
        setAuthorized(false);
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    pipeline.addLast(new ClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 45001).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        eventLoopGroup.shutdownGracefully();

//        try {
//            socket = new Socket("localhost", 45001);
//            in = new DataInputStream(socket.getInputStream());
//            out = new DataOutputStream(socket.getOutputStream());
//            setAuthorized(true);
//        }
//        catch (IOException e) {
//            lblLs.setVisible(true);
//            lblLs.setText("*Не удалось подключиться к серверу");
//            e.printStackTrace();
//        }
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