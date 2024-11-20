package com.example;

import java.util.ArrayList;


public class Lista {
    ArrayList<String> contatto;

     public Lista(){
       contatto = new ArrayList<>();
     }
     public void setUsername(ArrayList<String> contatto){
        this.contatto = contatto;
     }
     public ArrayList<String> getUsername(){
        return contatto;
     }
//inizio gestione contatti
     public boolean aggiuntaContanti(String a) {
        if(contatto.contains(a)){
            return false;
        }else{
            contatto.add(a);
            return true;
        }
     }
     public void visualizzaContatti(){
        for(int i = 0; i<contatto.size(); i++ ){
            System.out.println(contatto.get(i));
        }
     }

}
