/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rogelio
 */
public class Juego {
    
        static String[][] PosicionTablero1 = new String[8][8];
        static String[][] PosicionTablero2 = new String[8][8];
        
        static String[][] Tablero1 = new String[8][8];
        static String[][] Tablero2 = new String[8][8];
        
        
        static int cantidadBarco;
        static int Dificultad;
        
        
    public Juego(){
        
    }
    
    public static void main(String[] args){
        ArrayList<String> ListaDeBarcos = new ArrayList<>();
        Scanner n = new Scanner(System.in);
        String TipoBarco;
        int fila;
        int columna;
        switch(Dificultad){
            case 1:
                cantidadBarco=5;
                break;
            case 2:
                cantidadBarco=4;
                break;
            case 3:
                cantidadBarco=2;
                break;
            case 4:
                cantidadBarco=1;
                break;
        }
        
        
        for(int x=1 ; x<=cantidadBarco ; x++){
            System.out.println("Que tipo de barco desea usar?");
            System.out.println("Opciones: PA, AZ, SM, DT");
            TipoBarco = n.nextLine();
            
            for(int i=0 ; i<ListaDeBarcos.size() ; i++){
                if((ListaDeBarcos.get(i).equals(TipoBarco) && Dificultad!=1) || (Dificultad==1 && TipoBarco.equals("DT") && ListaDeBarcos.get(i).equals(TipoBarco))){
                    System.out.println("No es posible colocar ese tipo de barco.");
                    System.out.println("Elija de nuevo el tipo de barco");
                    System.out.println("Opciones: PA, AZ, SM, DT");
                    TipoBarco = n.nextLine();
                    i=0;
                }
            }
            
            System.out.println("Elija el indice de la fila: ");
            fila = n.nextInt();
            System.out.println("Elija el indice de la columna: ");
            columna = n.nextInt();
            
        }
    }
    public static void registrarResultado(String ganador, String perdedor, int dificultad) {
        String registro;
        int indice1;
        registro = ganador + " hundió todos los barcos de " + perdedor + " en modo " + dificultad;
        indice1 = MenusDelJuego.indice1; 
        MenusDelJuego.listaUsuarios.get(indice1).SetLog(registro);
        
    }
    public static void Tablero(){
        for(int x=0 ; x<PosicionTablero1.length ; x++){
            for(int y=0 ; y<PosicionTablero1[x].length ; y++){
                if(PosicionTablero1[x][y]==null){
                    System.out.print("~ ");
                }else{
                System.out.print(PosicionTablero1[x][y]);
                }
            }
            System.out.println();
        }
        
    }
    public static void ColocarBarco(int Jugador, int x1, int x2, int y1, int y2, String barco){
        if(Jugador==1){
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    PosicionTablero1[x1][y1+i]=barco;
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    PosicionTablero1[x1+i][y1]=barco;
                }
            }
        }else{
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    PosicionTablero2[x1][y1+i]=barco;
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    PosicionTablero2[x1+i][y1]=barco;
                }
            }
        }
    }
    
    public static boolean RevisarPosicion(int Tablero, int x1, int x2, int y1, int y2, int barco){
        boolean Posicion=true;
        
        if(Tablero==1){
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    if(PosicionTablero1[x1][y1+i]!=null){
                        Posicion=false;
                    }
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    if(PosicionTablero1[x1+i][y1]!=null){
                        Posicion=false;
                    }
                }
            }
        }else{
            if(x1==x2){
                for(int i=0 ; i<=y2-y1 ; i++){
                    if(PosicionTablero2[x1][y1+i]!=null){
                        Posicion=false;
                    }
                }
            }else{
                for(int i=0 ; i<=x2-x1 ; i++){
                    if(PosicionTablero2[x1+i][y1]!=null){
                        Posicion=false;
                    }
                }
            }
        }
        return Posicion;
    }
}

