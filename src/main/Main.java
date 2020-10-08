  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;

/**
 *
 * @author logra
 */
public class Main {
    
    public static class Filosofo{
        int tiempo_comida;
        int tiempo_hambre;
        boolean comiendo = false;
    }
    
    public static Filosofo[] iniciar(Filosofo lista[]){
        Random rand = new Random();
        for(int i = 0; i < 8; i++){
            lista[i] = new Filosofo();
            lista[i].tiempo_comida =  rand.nextInt(100);
            lista[i].tiempo_hambre =  rand.nextInt(100);
            if( (i + 1) % 3 == 0 ){
                lista[i].comiendo = true;
            }
        }
        return lista;
    }
    
    public static void escanear(Filosofo tiempos[], Filosofo cena[], int i){
        if(i == 7){
            if(tiempos[i].tiempo_comida <= 0 && tiempos[0].tiempo_hambre > 0){
                tiempos[i].tiempo_comida = cena[i].tiempo_comida;
                tiempos[i].comiendo = false;
                tiempos[0].comiendo = true;
                tiempos[0].tiempo_hambre = cena[0].tiempo_hambre;
            }
        }else{
            if(tiempos[i].tiempo_comida <= 0 && tiempos[i + 1].tiempo_hambre > 0){
                tiempos[i].tiempo_comida = cena[i].tiempo_comida;
                tiempos[i].comiendo = false;
                tiempos[i + 1].comiendo = true;
                tiempos[i + 1].tiempo_hambre = cena[i].tiempo_hambre;
            }
        }
    }
    
    public static void imprimir(Filosofo lista[]){
        for(int i = 0; i < 8; i++){
            if(lista[i].tiempo_hambre <= 0 && lista[i].comiendo == false){
                System.out.println("El filosofo " + (i + 1) + " se murio de hambre.");
            }else{
                if(lista[i].comiendo == false){
                    System.out.println("El filosofo " + (i + 1) + " esta pensando.");
                }
                else{
                    System.out.println("El filosofo " + (i + 1) + " esta comiendo.");
                }
            }
        }
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        Random rand = new Random();
        Filosofo cena[] = new Filosofo[8];
        cena = iniciar(cena);
        Filosofo tiempos[] = cena;
        int tiempo_cena = 9 + rand.nextInt(91);
        
        while(tiempo_cena > 0){
            tiempo_cena--;
            for(int i = 0; i < 8; i++){
                if(tiempos[i].comiendo == true){
                    tiempos[i].tiempo_comida--;
                    escanear(tiempos, cena, i);
                    
                }
                else{
                    tiempos[i].tiempo_hambre--;
                }
            }
            if(tiempo_cena % 9 == 0){
                imprimir(tiempos);
                System.out.print("\n");
            }
        }
        
        
        
        
    }
}
