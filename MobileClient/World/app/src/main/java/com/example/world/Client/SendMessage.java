package com.example.world.Client;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendMessage {
    private static final String TAG = "SendMessage";
    private volatile BufferedWriter out;
    public SendMessage(){
        try {
            out = new BufferedWriter(new OutputStreamWriter(Client.clientSocket.getOutputStream()));
        }catch (Exception e){
            Log.d(TAG, "SendMessage: " + e);
        }
    }

    protected void write(final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: lets write");
                    out.write(message + "\r\n");
                    Log.d(TAG, "run: lets flush");
                    out.flush();
                    Log.d(TAG, "run: everything is alsom!!!!!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }catch(Exception e){
                    Log.d(TAG, "doInBackground: " + e);
                }
            }
        }).start();
    }
}
