package com.example.anroid_networking.Lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anroid_networking.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AndroidSocketCLIENT extends AppCompatActivity implements View.OnClickListener {
    private Socket socket;
    private static final int SERVERPORT = 5000;
    private static final String SERVER_IP = "10.0.2.2";
    EditText edt;
    Button btnSend;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_socket_c_l_i_e_n_t);
        edt = (EditText) findViewById(R.id.edtText);
        btnSend = (Button) findViewById(R.id.btnActive);

        btnSend.setOnClickListener(this);

        new Thread(new ClientThread()).start();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnActive:
                str = edt.getText().toString();
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter
                            (new
                                    OutputStreamWriter(socket.getOutputStream())), true);
                    out.println(str);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    class ClientThread implements Runnable {
        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}