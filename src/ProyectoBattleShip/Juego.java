/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;

/**
 *
 * @author Rogelio
 */
public class Juego {
    
        static String[][] Tablero1 = new String[8][8];
        static String[][] Tablero2 = new String[8][8];
        
        static int Dificultad;
        
    public Juego(){
        
    }
    
    public static void main(String[] args){
        Tablero();
    }
    
    public static void Tablero(){
        for(int x=0 ; x<Tablero1.length ; x++){
            for(int y=0 ; y<Tablero1[x].length ; y++){
                if(Tablero1[x][y]==null){
                    System.out.print("~ ");
                }else{
                System.out.print(Tablero1[x][y]);
                }
            }
            System.out.println();
        }
        
    }
    public static void ColocarBarco(int Jugador, int x1, int x2, int y1, int y2, String barco){
        if(Jugador==1){
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    Tablero1[x1][y1+i]=barco;
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    Tablero1[x1+i][y1]=barco;
                }
            }
        }else{
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    Tablero2[x1][y1+i]=barco;
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    Tablero2[x1+i][y1]=barco;
                }
            }
        }
    }
    
    public static boolean RevisarPosicion(int Tablero, int x1, int x2, int y1, int y2, int barco){
        boolean Posicion=true;
        
        if(Tablero==1){
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    if(Tablero1[x1][y1+i]!=null){
                        Posicion=false;
                    }
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    if(Tablero1[x1+i][y1]!=null){
                        Posicion=false;
                    }
                }
            }
        }else{
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    if(Tablero2[x1][y1+i]!=null){
                        Posicion=false;
                    }
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    if(Tablero2[x1+i][y1]!=null){
                        Posicion=false;
                    }
                }
            }
        }
        return Posicion;
    }
}

