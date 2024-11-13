package com.example;

import java.util.ArrayList;


public class Lista {
    ArrayList<String> contatti;
    ArrayList<String> gruppo;

     public Lista(){
        ArrayList<String> contatti = new ArrayList<String>();
     }

     public void aggiuntaContanti(String a) {
        if(contatti.contains(a)){
            System.out.println("variabile già esistente");

        }else{
            System.out.println("nuova variabile aggiunta");
        }
     }
     public void visualizzaContatti(){
        for(int i = 0; i<contatti.size(); i++ ){
            System.out.println("");
        }
     }

}
