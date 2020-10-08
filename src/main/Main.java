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
        int tiempo_comida; // tiempo que se tarda en comer
        int tiempo_hambre; // tiempo que dura en tener hambre sin morir
        boolean comiendo = false;  //  turno para comer
    }
    
    public static Filosofo[] iniciar(Filosofo lista[]){
        Random rand = new Random();
        for(int i = 0; i < 8; i++){
            lista[i] = new Filosofo(); // inicializamos cada filosofo
            lista[i].tiempo_comida =  rand.nextInt(100); // asignamos un numero aleatorio para el tiempo que tarda en comer
            lista[i].tiempo_hambre =  rand.nextInt(100); // asignamos un numero aleatorio para el tiempo que tiene para comer y no morir
            if( (i + 1) % 3 == 0 ){  // cada 2 filosofos
                lista[i].comiendo = true; //asignamos un turno
            }
        }
        return lista;
    }
    
    public static void escanear(Filosofo tiempos[], Filosofo cena[], int i){
        if(i == 7){ // si es el ultimo, se lo pasamos al inicio de la "mesa" (arreglo)
            if(tiempos[i].tiempo_comida <= 0 && tiempos[0].tiempo_hambre > 0){ //si termino de comer y el siguiente aun tiene hambre
                tiempos[i].tiempo_comida = cena[i].tiempo_comida; // reiniciamos el tiempo de comida
                tiempos[i].comiendo = false; // le quitamos el turno
                tiempos[0].comiendo = true; // se pasa el turno al siguiente
                tiempos[0].tiempo_hambre = cena[0].tiempo_hambre;  // reiniciamos el tiempo de hambre del filosofo
            }
        }else{
            if(tiempos[i].tiempo_comida <= 0 && tiempos[i + 1].tiempo_hambre > 0){ //si termino de comer y el siguiente aun tiene hambre
                tiempos[i].tiempo_comida = cena[i].tiempo_comida;  // reiniciamos el tiempo de comida
                tiempos[i].comiendo = false;  // le quitamos el turno
                tiempos[i + 1].comiendo = true;  // se pasa el turno al siguiente
                tiempos[i + 1].tiempo_hambre = cena[i].tiempo_hambre;  // reiniciamos el tiempo de hambre del filosofo
            }
        }
    }
    
    public static void imprimir(Filosofo lista[]){
        for(int i = 0; i < 8; i++){
            if(lista[i].tiempo_hambre <= 0 && lista[i].comiendo == false){ // si ya no tiene hambre y no esta comiendo
                System.out.println("El filosofo " + (i + 1) + " se murio de hambre.");  // significa que murio
            }else{
                if(lista[i].comiendo == false){ // si no esta comiendo
                    System.out.println("El filosofo " + (i + 1) + " esta pensando."); // esta esperando y pensando
                }
                else{ // si si esta comiendo
                    System.out.println("El filosofo " + (i + 1) + " esta comiendo.");  // esta comiendo
                }
            }
        }
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        Random rand = new Random(); // generar un numero aleatorio
        Filosofo cena[] = new Filosofo[8]; // se pone la mesa de los filosofos
        cena = iniciar(cena);  // iniciamos la cena
        Filosofo tiempos[] = cena; // hacemos una copia de los tiempos de los filosofos
        int tiempo_cena = 9 + rand.nextInt(91); // generamos un tiempo aleatorio en el que se pasaran los turnos
        
        while(tiempo_cena > 0){  // mientras no se haya acabado el tiempo de cena
            tiempo_cena--; // le quitamos un minuto
            for(int i = 0; i < 8; i++){  // recorremos la mesa
                if(tiempos[i].comiendo == true){  // si esta comiendo
                    tiempos[i].tiempo_comida--;  // le quitamos un minuto al tiempo de comida
                    escanear(tiempos, cena, i);  // y checamos si hay muertos, pensadores o comensales
                    
                }
                else{ // si no esta comiendo
                    tiempos[i].tiempo_hambre--; // le quitamos un minuto al tiempo de espera
                }
            }
            if(tiempo_cena % 9 == 0){  // cada 9 minutos
                imprimir(tiempos);  // imprimimos el estado de la mesa
                System.out.print("\n");
            }
        }
        
    }
}
