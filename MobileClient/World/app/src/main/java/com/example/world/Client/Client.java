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
    public static volatile String ip = "192.168.0.106";
    public static volatile int port = 25555;
    public static volatile boolean flow = false; // controls the network connection
    public static volatile String name = "";
    public static final DataLibrary clientsData = new DataLibrary("clientsData");
    public static volatile boolean disconnected = true;
    public static volatile int condition; // 0 -> connected, 1 -> reconnected, 2 -> first start
    public static final int idle = 10;
    public static final int playing = 11;
    public static final int first_start = 12;
    public static volatile int actualGameId = -1;

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
    /*    // TODO Auto-generated method stub
        final String[] message = value.split(" ");
        String typeAction;
        typeAction = message[0];
        switch(typeAction) {
            case "get":// getter
                get(message);
                break;
            case "start_the_game":
                MainActivity.actual_activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((MenuActivity)MainActivity.actual_activity).goToLastActivity = true;
                        GameActivity.disconnected = false;
                        Intent menuIntent = new Intent(MainActivity.actual_activity, GameActivity.class);
                        MainActivity.actual_activity.startActivity(menuIntent);
                    }
                });
                break;
            case "startGameTimer": // startGameTimer durationOfRound rounds
                timer = new Timer(Integer.parseInt(message[1]), Integer.parseInt(message[2]));
                break;
            case "new_round":
                MainActivity.actual_activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (MainActivity.actual_activity instanceof GameActivity)
                            ((TextView)MainActivity.actual_activity.findViewById(R.id.current_round)).setText("Round: " + message[1]);
                    }
                });
                timer.makeNewRound = true;
                break;
            case "size_of_map":
                GameActivity.sizeOfMap = Integer.parseInt(message[1]);
                GameActivity.map = new Place[GameActivity.sizeOfMap][GameActivity.sizeOfMap];
                for (int i = 0; i < GameActivity.sizeOfMap; i++) {
                    for (int j = 0; j < GameActivity.sizeOfMap; j++) {
                        GameActivity.map[i][j] = new Place(i, j);
                    }
                }
                break;
            default:
                Log.d(TAG, "decomposeTheString: Some wierd default!!!!");
                Log.d(TAG, "decomposeTheString: " + value);
                break;
        }*/

    }
   /* public final static Place[][] pl = GameActivity.map;

    private static void get(final String[] message) {
        switch (message[1]){
            case "number_of_players_to_wait":
                DecomposeMessage.number_of_players_to_wait(message);
                break;
            case "set_id":
                DecomposeMessage.set_id(message);
                break;
            case "set_wood":
                DecomposeMessage.set_wood(message);
                break;
            case "set_stone":
                DecomposeMessage.set_stone(message);
                break;
            case "set_food":
                DecomposeMessage.set_food(message);
                break;
            case "set_gold":
                DecomposeMessage.set_gold(message);
                break;
            case "set_force":
                DecomposeMessage.set_force(message);
                break;
            case "not_enough_resources":
                MainActivity.actual_activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.actual_activity, "Not enough resources", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "addUnit":
                DecomposeMessage.addUnit(message);
                break;
            case "removeUnit":
                DecomposeMessage.remove_unit(message);
                break;
            case "addVillage":
                DecomposeMessage.addVillage(message);
                break;
            case "removeVillage":
                DecomposeMessage.remove_village(message);
                break;
            case "addVision": // get addVision  3 3 map.Meadow 3 4 map.Meadow 3 5 map.Forest
                DecomposeMessage.addVision(message);
                break;
            case "removeVision"://get removeVision
                DecomposeMessage.removeVision(message);
                break;
            case "setResoult":
                DecomposeMessage.setResoult(message);
                break;
        }
    }*/
}
