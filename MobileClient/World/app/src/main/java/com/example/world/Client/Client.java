package com.example.world.Client;

import android.util.Log;

import com.example.world.AbstractActivity;
import com.example.world.DataSafe.DataLibrary;
import com.example.world.MainActivity;
import com.example.world.MenuScreen.MenuActivity;
import com.example.world.Screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static final String TAG = "Client";
    public static volatile Socket clientSocket;
    public static final String ip = "192.168.0.106";
    public static volatile int port = 25555;
    public static volatile boolean flow = false; // controls the network connection
    public static String name = "";
    public static final DataLibrary clientsData = new DataLibrary("clientsData");
    public static volatile boolean disconnected = true;
    public static volatile int condition; // 0 -> connected, 1 -> reconnected, 2 -> first start
    public static final int idle = 10;
    public static final int playing = 11;
    public static final int first_start = 12;
    public static volatile int actualGameId = -1;
    public static volatile Stats stats;
    public static volatile Fragments fragments;

    public Client(){
        condition = clientsData.LoadDataInteager(AbstractActivity.actualActivity, "clientsCondition");
        name = clientsData.LoadDataString(MainActivity.actualActivity, "clientsName");
        if(condition < 10)condition = first_start;
        if(condition == playing) actualGameId = clientsData.LoadDataInteager(AbstractActivity.actualActivity, "gameId");
    }

    public static void setName(String text) {
        if(!name.equals(text)){
            name = text;
            clientsData.saveDataString(MainActivity.actualActivity, name, "clientsName");
            clientsData.saveDataInteager(MainActivity.actualActivity, idle, "clientsCondition");
        }
        write("name " + name);
    }

    // This method sets the connection to server
    public static void connect() {
        Log.d(TAG, "run: CREATE CLIENT --- LETS CONNECT");
        boolean flow = true; // to gets info about flow
        try {
            try{
                clientSocket = new Socket(ip, port);
            }catch (UnknownHostException e){
                flow = false;
            } catch (IOException i) {
                MainActivity.actualActivity.runOnUiThread(
                        () -> Screen.info("Network exception...", 0));
                flow = false;
            }
            if(clientSocket == null) flow = false;

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            startListener(in);
        } catch (IOException e) {
            flow = false;
        } catch (Exception e){
            flow = false;
        }
        Client.flow = flow;
        sendMessage = new SendMessage();

        if(flow){
            clientsData.saveDataInteager(AbstractActivity.actualActivity, port, "port");
            clientsData.saveDataString(AbstractActivity.actualActivity, ip, "serverIp");

            if(AbstractActivity.actualActivity instanceof MenuActivity)
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        AbstractActivity.actualActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MenuActivity)AbstractActivity.actualActivity).showMenuFragment();
                            }
                        });
                    }
                }).start();
        }
    }

    // app stores last ip, which was used
    // this method is called when connect to last ip button is clicked
    // it will try to connect to server with last ip
    public static void connectToLastIp() {
        ip = Client.clientsData.LoadDataString(AbstractActivity.actualActivity, "serverIp");
        port = Client.clientsData.LoadDataInteager(AbstractActivity.actualActivity, "port");
        new Thread(() -> connect()).start();
    }

    // sets listener of messages from server
    private static void startListener(final BufferedReader in) {
        // TODO Auto-generated method stub
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true) {
                    String string = null;
                    try {
                        string = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e){
                        Log.d(TAG, "run: error " + e);
                        return;
                    }
                    if(string != null) {
                        makeThreadWorker(string);
                    }
                }
            }
            private void makeThreadWorker(final String string) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: NEW MESSAGE : ------->   " + string);
                        decomposeTheString(string);
                    }
                }).start();
            }
        });
        thread.start();
    }

    public static SendMessage sendMessage;
    public static void write(String message){
        try{
            sendMessage.write(message);
        }catch (Exception e){
            Log.d(TAG, "write: ERROR " + e);
            return;
        }
        Log.d(TAG, "write: text was written  ->  " + message);
    }

    //private static Timer timer;
    public synchronized static void decomposeTheString(String value) {
        Log.d(TAG, "decomposeTheString: TRY HELLO WORLD");

    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setFragments(Fragments fragment) {
        this.fragments = fragments;
    }
}
