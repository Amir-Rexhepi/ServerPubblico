package com.example;

import java.util.ArrayList;


public class Lista {
    ArrayList<String> contatto;
    ArrayList<String> gruppo;

     public Lista(){
       contatto = new ArrayList<>();
       gruppo = new  ArrayList<>();
     }

     public void setUsername(ArrayList<String> contatto){
        this.contatto = contatto;
     }
     public ArrayList<String> getUsername(){
        return contatto;
     }
     public void setGruppo(ArrayList<String> gruppo){
        this.gruppo = gruppo;
     }
     public ArrayList<String> getGruppo(){
        return gruppo;
     }
//inizio gestione contatti
     public void aggiuntaContanti(String a) {
        if(contatto.contains(a)){
            System.out.println("username già esistente");
        }else{
            System.out.println("nuovo username aggiunto");
            contatto.add(a);
        }
     }
     public void visualizzaContatti(){
        for(int i = 0; i<contatti.size(); i++ ){
            System.out.println(contatto.get(i));
        }
     }
//fine gestione contatti
//inizo gestione Gruppi
public void aggiuntaGruppo(String a) {
    if(gruppo.contains(a)){
        System.out.println("gruppo già esistente");
    }else{
        System.out.println("nuovo gruppo creato");
        gruppo.add(a);
    }
 }
 public String visualizzaGruppi() {
   StringBuilder risultato = new StringBuilder();
   for (int i = 0; i < gruppo.size(); i++) {
       risultato.append(gruppo.get(i).toString());
       if (i < gruppo.size() - 1) {
           risultato.append("\n");
       }
   }
   return risultato.toString();
}
//fine Gestione Gruppi
}
