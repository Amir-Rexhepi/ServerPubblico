package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3000);
        Lista l = new Lista();

        do {
            Socket s = ss.accept();
            System.out.println("Un Client si è appena connesso al server");
            MioThread t = new MioThread(s, l);
            t.start();
        } while (true);
        
    }
}