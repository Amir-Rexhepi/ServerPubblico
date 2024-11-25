package com.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GestioneConnessione extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String username;

    public GestioneConnessione(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Gestione username
            out.write("Inserisci il tuo username: ");
            out.newLine();
            out.flush();
            username = in.readLine();

            while (username == null || username.trim().isEmpty()) {
                out.write("Username non valido, riprova: ");
                out.newLine();
                out.flush();
                username = in.readLine();
            }
            System.out.println(username + " si è connesso.");
            out.write("Benvenuto, " + username + "! Digita '/GLOBAL'  o '/QUIT':");
            out.newLine();
            out.flush();

            String messaggio;
            while ((messaggio = in.readLine()) != null) {
                if (messaggio.equalsIgnoreCase("/QUIT")) {
                    out.write("Disconnessione...");
                    out.newLine();
                    out.flush();
                    break;
                } else if (messaggio.equalsIgnoreCase("/GLOBAL")) {
                    out.write("Digita il messaggio da inviare a tutti:");
                    do{ 
                    out.newLine();
                    out.flush();
                    String broadcastMessage = in.readLine();
                    ChatServer.broadcast(username + ": " + broadcastMessage, this);
                    if (messaggio.equals("/QUIT")) {
                        break;
                    }
                   }while (true); 
                } else {
                    out.write("Comando non valido. Digita 'PUBBLICO' o 'DISCONETTI'.");
                    out.newLine();
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Errore con il client: " + e.getMessage());
        } finally {
            try {
                ChatServer.rimuoviConnessione(this);
                socket.close();
                System.out.println(username + " si è disconnesso.");
            } catch (IOException e) {
                System.err.println("Errore durante la chiusura della connessione: " + e.getMessage());
            }
        }
    }

    public void inviaMessaggio(String messaggio) {
        try {
            out.write(messaggio);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println("Errore nell'invio del messaggio a " + username + ": " + e.getMessage());
        }
    }
}