package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class MioThread extends Thread {
    Socket s;
    Lista l;

    public MioThread(Socket s, Lista l) {
        this.s = s;
        this.l = l;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            boolean connessione = true;
            //possibile errore
            out.writeBytes("Inserisci un username prima di chattare" + "\n");
            String nome = in.readLine();
             do {
                

               if (nome.isEmpty()) {
                    out.writeBytes("Metti un username valido" + "\n");
                }
                else {
                 if( l.aggiuntaContanti(nome))
                 {
                    out.writeBytes("MENU" + "\n");
                    break;
                 }else{
                    out.writeBytes("Username gia esistente" + "\n");
                 }
                 
                }
            } while (true);
            /* Controllo */
            /* Menu Principale */
            do {
                String menuP = in.readLine();
                switch (menuP) {
                    case "PRIVATO":
                        // Si entra nel ramo contatto singolo
                        System.out.println("Privato");
                        out.writeBytes("PRIV" + "\n");
                        break;

                    case "PUBBLICO":
                        // si entra nel ramo gruppo
                        out.writeBytes("PUBBL" + "\n");
                        do{
                        String scelta = in.readLine();
                        /* MENU PUBLIC */
                        switch (scelta) {
                            case "LISTA_PUBBL":

                                if (l.contatto.isEmpty()) {
                                    out.writeBytes("VUOTO" + "\n");
                                } else {
                                    for (String i : l.contatto) {
                                        out.writeBytes(i + "\n");
                                    }
                                    out.writeBytes("VUOTO" + "\n");
                                }

                                break;

                            case "SCRIVI_PUBBLIC":
                                out.writeBytes("Digita un messaggio da mandare a tutti gli altri utenti" + "\n");
                                String[] messaggio = new String[100];
                                while (true) {
                                    String leggi = in.readLine();
                                    if(leggi.equals("@")){
                                        break;
                                    }else{
                                    for(int i = 0; i<messaggio.length; i++){
                                            messaggio[i] = leggi;
                                        out.writeBytes("Ok" + "\n");
                                       }
                                    }
                                }
                                String messaggi = String.join(" ", messaggio);
                                out.writeBytes( messaggi + "\n");
                                out.writeBytes(nome + messaggi + "\n");;
                                break;
                            case "MENUCHAT":
                                out.writeBytes("MENU" + "\n");
                                break;
                        }
                    }while (true);
                    case "DISCONETTI":
                        // si disconnette il client
                        s.close();
                        out.writeBytes(nome +" si è disconesso" + "\n");
                        break;
                }
            } while (connessione);
        } catch (Exception e) {
            System.out.println("client disconesso");
        }
    }
    
}
