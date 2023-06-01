package com.world.pcclient.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.world.pcclient.client.chatListener.ChatListener;
import com.world.pcclient.Panels;
import com.world.pcclient.Stats;

public class Client {
    public Socket clientSocket;
    private BufferedWriter out;
    private Scanner in;
    private static int clientIDCounter = 0;
    public final int ID = clientIDCounter++;
    public final ChatListener listener;
    public MessageSender sender = new MessageSender(this);
    private Panels panels;

    /**
     * Try to bind new connection with the server.
     * It also sends initialization messages to the server,
     * 
     * @param panels
     * @param stats
     */
    public Client(Panels panels, Stats stats) {
        this.panels = panels;
        int port = 25555;

        listener = new ChatListener(stats, panels);

        while (true) {
            try {
                this.clientSocket = new Socket(String.valueOf(InetAddress.getLocalHost().getHostAddress()), port);
                this.in = new Scanner(clientSocket.getInputStream());
                break;
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        panels.mainWindow.setTitleCondition(new StringBuilder("connected"));
        startListener(in, panels);
        sendIntroductionInfo();
    }

    private void startListener(final Scanner in, final Panels panels) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("Client");
                while (true) {
                    String s = "";
                    try {
                        s = in.nextLine();
                    } catch (Exception e) {
                        System.out.println("Connection interupted " + e);
                        System.exit(0);
                        break;
                    }

                    if (s != null) {
                        listener.listen(s);
                    }
                }
            }
        });
        thread.start();
    }

    private void sendIntroductionInfo() {
        sendMessage("server name clientName_" + ID);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendMessage("server findTheMatch");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendMessage("ready");
    }

    public void sendMessage(String message) {
        panels.listenerPanel.addOutcomingMessage(message);
        try {
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
