package com.ahrsoft.movil.chatsockets;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TextView sendtext;
    private EditText cajatexto;
    private  Button btnsend;
    private String textoenviado;
    private TextView conexiontxt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendtext = (TextView) findViewById(R.id.txtEnvio);
        cajatexto = (EditText) findViewById(R.id.edittextSend);
        btnsend = (Button) findViewById(R.id.btnSend);
        conexiontxt = (TextView) findViewById(R.id.txtConexion);




        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

    }
    public void onClick (View view){
        textoenviado = cajatexto.getText().toString();
        ejecutaCliente();

    }

    private void ejecutaCliente() {

        String ip = "192.168.43.87";
        int puerto = 7;

        conexiontxt.setText("Conectado a " + ip +" " + puerto);

        try {
            Socket sk = new Socket(ip, puerto);
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(
                    new OutputStreamWriter(sk.getOutputStream()), true);
            log("enviando... " + textoenviado);
            salida.println(textoenviado);
            log("recibiendo " +textoenviado + entrada.readLine());
            sk.close();
        } catch (Exception e) {
            log("error: " + e.toString());
        }
    }



    private void log(String string) {
        sendtext.append(string + "\n");
    }


}
