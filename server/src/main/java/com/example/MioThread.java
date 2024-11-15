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
            String nome = in.readLine();
                if(nome.equals(" ")){
                    out.writeBytes("Metti un username," + "\n");
                }else{
                    l.aggiuntaContanti(nome);
                    out.writeBytes();
                    
                }
            l.visualizzaContatti();
            do{
                /* Controllo */ 
                /*Menu Principale */
                String menuP = in.readLine();
                System.out.println(menuP);
                switch (menuP) {
                    case "PRIVATO":
                    //Si entra nel ramo contatto singolo
                    System.out.println("Privato");
                         out.writeBytes("PRIV" + "\n");
                        break;
                
                    case "PUBBLICO":
                    //si entra nel ramo gruppo
                    out.writeBytes("PUBBL" + "\n");
                        break;
                    case "DISCONETTI":
                    //si disconnette il client 
                    out.writeBytes("EXIT" + "\n");
                    break;
                } 

                String scelta = in.readLine();
                /*MENU PUBLIC */
                switch (scelta) {
                    case "LISTA_PUBBL":
                       out.writeBytes("LISTA_GRUPPI:" + "\n");
                        break;
                
                    case "SCELTA_PUBBLIC":
                        out.writeBytes("SCELTA_GRUPPO" + "\n");
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
