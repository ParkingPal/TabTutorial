package com.example.tabtutorial;

import android.app.ListActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tabtutorial.ui.main.BLActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class BuffetActivity extends ListActivity {
    Socket s = null;
    DataOutputStream dos = null;
    DataInputStream dis = null;

    ArrayList<String> pizzaStringList = new ArrayList<>();
    ArrayList<String> pizzaIdList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffet);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);

        ListView list = findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;
                BuffetActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            sendMessage(pizzaIdList.get(index), pizzaStringList.get(index));
                            pizzaStringList.remove(index);
                            pizzaIdList.remove(index);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            System.out.println("ERROR: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        adapter = new ArrayAdapter<>(this, R.layout.listitems, pizzaStringList);
        setListAdapter(adapter);

        try {
            connectToServer("74.130.99.83", 3395);
            listen();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            disconnectFromServer();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        Intent toSwitchTo = new Intent(this, MainActivity.class);
        startActivity(toSwitchTo);
    }

    private void connectToServer(String ipString, int ServerPort) throws IOException {
        InetAddress ip = InetAddress.getByName(ipString);
        s = new Socket(ip, ServerPort);
        dos = new DataOutputStream(s.getOutputStream());
        dis = new DataInputStream((s.getInputStream()));
        dos.writeUTF("BL");

        String msg = dis.readUTF();
        String[] arrMsg = msg.split("#");
        for (String pizza : arrMsg) {
            final String[] arrPizza = pizza.split("&");
            BuffetActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    addItem(arrPizza[0], arrPizza[1]);
                }
            });
        }
    }

    private void disconnectFromServer() throws IOException{
        dos.writeUTF("logout");
        dos.close();
        s.close();
    }

    private void sendMessage(String id, String pizzaString) throws IOException {
        String msg = "InOven#CD#" + id + "#" + pizzaString;
        dos.writeUTF(msg);
    }

    private void addItem(String id, String item) {
        pizzaIdList.add(id);
        pizzaStringList.add(item);
        adapter.notifyDataSetChanged();
    }

    private void listen(){
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    while (true) {
                        // read the message sent to this client
                        String msg = dis.readUTF();
                        final String[] arrMsg = msg.split(":", 2);
                        BuffetActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                addItem(arrMsg[0], arrMsg[1]);
                            }
                        });

                        System.out.println(msg);
                    }

                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        readMessage.start();
    }
}
