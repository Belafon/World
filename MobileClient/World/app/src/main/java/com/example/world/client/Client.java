package com.example.world.client;

import android.util.Log;

import com.example.world.AbstractActivity;
import com.example.world.dataSafe.DataLibrary;
import com.example.world.MainActivity;
import com.example.world.game.Game;
import com.example.world.game.client.MessageSender;
import com.example.world.game.client.chatListener.ChatListener;
import com.example.world.gameActivity.GameActivity;
import com.example.world.menuScreen.MenuActivity;
import com.example.world.Screen;
import com.example.world.game.Panels;
import com.example.world.game.Stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String TAG = "Client";

    public static Socket clientSocket;
    public static String ip = "192.168.0.106";
    public static volatile int port = 25555;
    public static volatile boolean flow = false; // controls the network connection

    public static String name = "";
    public static volatile boolean disconnected = true;

    public static final DataLibrary clientsData = new DataLibrary("clientsData");
    public static volatile int condition; // 0 -> connected, 1 -> reconnected, 2 -> first start
    
    public static final int idle = 10;
    public static final int playing = 11;
    public static final int first_start = 12;
    public static volatile int actualGameId = -1;

    public static Stats stats = Game.stats;
    public static Panels fragments;

    public static final MessageSender sender = new MessageSender();
    public static final ChatListener chatListener = new ChatListener();

    public Client() {
        condition = clientsData.LoadDataInteager(AbstractActivity.getActualActivity(), "clientsCondition");
        name = clientsData.LoadDataString(MainActivity.getActualActivity(), "clientsName");
        if (condition < 10)
            condition = first_start;
        if (condition == playing)
            actualGameId = clientsData.LoadDataInteager(AbstractActivity.getActualActivity(), "gameId");
    }

    public static void setName(String text) {
        if (!name.equals(text)) {
            name = text;
            clientsData.saveDataString(MainActivity.getActualActivity(), name, "clientsName");
            clientsData.saveDataInteager(MainActivity.getActualActivity(), idle, "clientsCondition");
        }
        sendMessage("name " + name);
    }

    // This method sets the connection to server
    public static void connect() {
        Log.d(TAG, "run: CREATE CLIENT --- LETS CONNECT");
        boolean flow = true; // to gets info about flow
        try {
            try {
                clientSocket = new Socket(ip, port);
            } catch (UnknownHostException e) {
                flow = false;
            } catch (IOException i) {
                MainActivity.getActualActivity().runOnUiThread(
                        () -> Screen.info("Network exception...", 0));
                flow = false;
            }
            if (clientSocket == null)
                flow = false;

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            startListener(in);
        } catch (IOException e) {
            flow = false;
        } catch (Exception e) {
            flow = false;
        }

        Client.flow = flow;

        if (flow) {
            sendMessage = new SendMessage();

            clientsData.saveDataInteager(AbstractActivity.getActualActivity(), port, "port");
            clientsData.saveDataString(AbstractActivity.getActualActivity(), ip, "serverIp");
            disconnected = false;

            if (AbstractActivity.getActualActivity() instanceof MenuActivity)
                new Thread(() -> {
                    AbstractActivity.getActualActivity().runOnUiThread(
                            () -> ((MenuActivity) AbstractActivity.getActualActivity()).showMenuFragment());
                }).start();
        }
    }

    // app stores last ip, which was used
    // this method is called when connect to last ip button is clicked
    // it will try to connect to server with last ip
    public static void connectToLastIp() {
        ip = Client.clientsData.LoadDataString(AbstractActivity.getActualActivity(), "serverIp");
        port = Client.clientsData.LoadDataInteager(AbstractActivity.getActualActivity(), "port");
        new Thread(() -> connect()).start();
    }

    // sets listener of messages from server
    private static void startListener(final BufferedReader in) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("ClientMessageListener");
                while (true) {
                    String string = null;
                    try {
                        string = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (string != null) {
                        makeThreadWorker(string);
                    }
                }
            }

            private void makeThreadWorker(final String string) {
          //      new Thread(() -> {
                    //Log.d(TAG, "run: NEW MESSAGE : ------->   " + string);
                    decomposeTheString(string);
      //          }).start();
            }
        });
        thread.start();
    }

    private static SendMessage sendMessage;

    public static void sendMessage(String message) {
        try {
            sendMessage.write(message);
        } catch (Exception e) {
            Log.d(TAG, "write: ERROR " + e);
            return;
        }
        Log.d(TAG, "write: text was written  ->  " + message);
    }

    // private static Timer timer;
    public synchronized static void decomposeTheString(String value) {
        chatListener.listen(value);
    }

    public static void setFragments(Panels newFragments) {
        fragments = newFragments;
    }
}
