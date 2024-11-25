package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 3000;
    private static List<GestioneConnessione> connessioni = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("Server avviato...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                GestioneConnessione gc = new GestioneConnessione(clientSocket);
                connessioni.add(gc);
                gc.start();
            }
        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }

    public static void broadcast(String messaggio, GestioneConnessione mittente) {
        synchronized (connessioni) {
            for (GestioneConnessione gc : connessioni) {
                if (gc != mittente) {
                    gc.inviaMessaggio(messaggio);
                }
            }
        }
    }

    public static void rimuoviConnessione(GestioneConnessione gc) {
        connessioni.remove(gc);
    }
}