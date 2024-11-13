package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;
    Lista l;

    public MioThread(Socket s, Lista l){
        this.s =  s;
        this.l = l;
    }

    public void run(){
       try{
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            do{
                /* Controllo */ 
                String nome = in.readLine();
                l.aggiuntaContanti(nome);
                out.writeBytes("MEUNU");
                /*Menu Principale */
                String menuP = in.readLine();
                switch (menuP) {
                    case "PRIVATO":
                    //Si entra nel ramo contatto singolo
                         out.writeBytes("PRIV");
                        break;
                
                    case "PUBBLICO":
                    //si entra nel ramo gruppo
                    out.writeBytes("PUBBL");
                        break;
                    case "DISCONETTI":
                    //si disconnette il client 
                    out.writeBytes("EXIT");
                    break;
                } 

                String scelta = in.readLine();
                /*MENU PUBLIC */
                switch (scelta) {
                    case "LISTA_PUBBL":
                       out.writeBytes("LISTA_GRUPPI");
                        break;
                
                    case "SCELTA_PUBBLIC":
                        out.writeBytes("SCELTA_GRUPPO");
                        break;
                    case "MENUCHAT":
                        out.writeBytes("MENU" + "\n");
                       break;
                }
                /*MENU SE È STATO SCELTO ALTRO */
                String sceltaG = in.readLine();
                switch (sceltaG) {

                    case "INDIETRO":
                         
                        break;
                
                    case "SCRIVI_GURPPO":
                     /*gestisco con un array */
                        if(scelta.equals("CIAO")){
                            /*entra nel gruppo e puo messaggiare */

                        }else{
                            out.writeBytes("NON_CONNESSO");
                        }
                            
                        break;
                       }
                String scletaF = in.readLine();
                   switch (scletaF) {
                    case "MESSAGGIO":
                        
                        break;
                   
                    case "NON_VALIDO":
                        break;
                   }

            }while(true);
       }catch(Exception e){
          System.out.println("errore");
       }
    }
}
