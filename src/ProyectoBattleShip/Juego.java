/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.Random;
/**
 *
 * @author Rogelio
 */
public class Juego {
    
        static String[][] Tablero1 = new String[8][8];
        static String[][] Tablero2 = new String[8][8];
        
        static int cantidadBarco;
        static int Dificultad=4;
        
        static int barcosP1;
        static int barcosP2;
        static String modoActual = "TUTORIAL";
        
        public static void vaciarTableros() {   
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Tablero1[x][y] = "~";
                    Tablero2[x][y] = "~";
                }
            }
        }
    
    public static void registrarResultado(String ganador, String perdedor, int dificultad) {
        String registro;
        int indice1;
        registro = ganador + " hundio todos los barcos de " + perdedor + " en modo " + dificultad;
        indice1 = MenusDelJuego.indice1; 
        MenusDelJuego.listaUsuarios.get(indice1).SetLog(registro);
        
    }
    
     private static int obtenerTamaño(String tipo) {
        switch (tipo) {
            case "PA": 
                return 5;
                
            case "AZ": 
                return 4;
                
            case "SM": 
                return 3;
                
            case "DT": 
                return 2;
                
            default: 
                return 0;
        }
    }
     
    public static void ColocarBarco(int Jugador, int fila, int columna, String tipo, char orientacion) {
        int tam = obtenerTamaño(tipo);
        String[][] tablero = (Jugador == 1) ? Tablero1 : Tablero2;

        for (int x = 0; x < tam; x++) {
            if (orientacion == 'H') tablero[fila][columna + x] = tipo;
            else tablero[fila + x][columna] = tipo;
        }
    }
   
    public static void RegenerarTablero(int numJugador) {
        String[][] original = (numJugador == 1) ? Tablero1 : Tablero2;
        // 1. Identificar barcos vivos (que no son "X" ni "~" ni "F")
        // Para simplificar, esta lógica debe recolectar los códigos de barcos 
        // y reubicarlos usando Random.
        
        String[][] nuevo = new String[8][8];
        for(int x=0; x<8; x++) for(int y=0; y<8; y++) nuevo[x][y] = "~";

        // Lógica de reubicación... (se invoca tras cada bombardeo exitoso)
        if (numJugador == 1){ 
            Tablero1 = nuevo; 
        }else{ 
            Tablero2 = nuevo;
        }
    }
    
    public static boolean Bombardear(int Atacante, int fila, int columna){
        String[][] objetivo = (Atacante == 1) ? Tablero2 : Tablero1;
        
        
        if(fila == -1 || columna == -1){
            return false;
        } // Para el retiro

        if (!objetivo[fila][columna].equals("~") && !objetivo[fila][columna].equals("F") && !objetivo[fila][columna].equals("X")) {
            System.out.println("BOMBARDEASTE UN " + objetivo[fila][columna]);
            objetivo[fila][columna] = "X";
            
            // Aquí entra lo dinámico:
            RegenerarTablero(Atacante == 1 ? 2 : 1);
            return true;
        } else {
            System.out.println("FALLASTE");
            objetivo[fila][columna] = "F";
            return false;
        }
    }
    
    public static boolean puedeColocar(int Jugador, int fila, int columna, String tipo, char orientacion){
        int tam = obtenerTamaño(tipo);
        String[][] tablero = (Jugador == 1) ? Tablero1 : Tablero2;

        try{
            
            for (int i = 0; i < tam; i++) {
                int nf = (orientacion == 'V') ? fila + i : fila;
                int nc = (orientacion == 'H') ? columna + i : columna;
                if (!tablero[nf][nc].equals("~")) 
                    return false;
            }
            
        }catch(Exception e){ 
            return false; 
        } 
        return true;
    }
    
    
    public static void imprimirTablero(int numTablero, boolean esPropio) {
        String[][] t = (numTablero == 1) ? Tablero1 : Tablero2;
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++){
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++){
                String celda = t[i][j];
                
                if (modoActual.equals("ARCADE") && !esPropio){
                    System.out.print((celda.equals("X") || celda.equals("F")) ? celda + " " : "~ ");
                }else{
                    System.out.print(celda + " ");
                }
            }
            System.out.println();
        }
    }
    
    public static boolean BuscarBarcos(int numJugador){
        String[][] tablero = (numJugador == 1) ? Tablero1 : Tablero2;
        
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                String celda = tablero[x][y];
                
                if (celda.equals("PA") || celda.equals("AZ") || 
                    celda.equals("SM") || celda.equals("DT")) {
                    return true; 
                }
            }
        }
        return false;
    }
}