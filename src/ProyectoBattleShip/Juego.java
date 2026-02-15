/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import static ProyectoBattleShip.MenusDelJuego.listaUsuarios;
import java.util.Random;

public class Juego {
    static String[][] Tablero1 = new String[8][8];
    static String[][] Tablero2 = new String[8][8];
    
    static String jugador1 = MenusDelJuego.jugador1;
    static String jugador2 = MenusDelJuego.jugador2;
    
    static int cantidadBarco;
    static int Dificultad=4;
    static int barcosP1;
    static int barcosP2;
    
    static String modoActual = "TUTORIAL";
    
    public static String[] tipos = {"PA", "AZ", "SM", "DT"};

    public static void vaciarTableros(){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                Tablero1[x][y] = "~";
                Tablero2[x][y] = "~";
            }
        }
    }

    public static void registrarResultado(String ganador, String perdedor, int dificultad){
        String registro;
        int indice;
        
        registro = ganador + " hundio todos los barcos de " + perdedor + " en modo " + dificultad;
        indice = MenusDelJuego.indice1;
        
        MenusDelJuego.listaUsuarios.get(indice).SaveLog(registro);
    }

    private static int obtenerTamaño(String tipo){
        switch(tipo){
            
            case "PA": 
                return 5;
            case "AZ": 
                return 4;
            case "SM": 
                return 3;
            case "DT": 
                return 2;
                
            default: return 0;
        }
    }

    public static void ColocarBarco(int Jugador, int fila, int columna, String tipo, char rotacion){
        int TamañoBarco = obtenerTamaño(tipo);
        String[][] tablero = (Jugador==1) ? Tablero1 : Tablero2;
        
        for(int x=0; x<TamañoBarco; x++){
            if(rotacion=='H'){ 
                tablero[fila][columna+x] = tipo; 
            }else{
                tablero[fila+x][columna] = tipo; 
            }
        }
    }

    public static void RegenerarTablero(int numJugador){
        String[][] TableroActual = (numJugador==1) ? Tablero1 : Tablero2;
        String[][] NuevoTablero = new String[8][8];
        
        Random rand = new Random();
        
        
        String celda = "";
        
        int PartesVivas = 0;
        int fila = 0;
        int columna = 0;
        char direccion = ' ';
        
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                celda = TableroActual[x][y];
                NuevoTablero[x][y] = (celda.equals(" X") || celda.equals(" F")) ? celda : "~";
            }
        }
        
        for(String tipo : tipos){
            PartesVivas = 0;
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(TableroActual[i][j].equals(tipo)){ PartesVivas++; }
                }
            }
            
            while(PartesVivas>0){
                fila = rand.nextInt(8);
                columna = rand.nextInt(8);
                direccion = rand.nextBoolean() ? 'H' : 'V';
                
                if(PuedeReubicar(NuevoTablero, fila, columna, PartesVivas, direccion)){
                    for(int x=0; x<PartesVivas; x++){
                        if(direccion=='H'){ NuevoTablero[fila][columna+x] = tipo; }
                        else{ NuevoTablero[fila+x][columna] = tipo; }
                    }
                    PartesVivas = 0;
                }
            }
        }
        
        if(numJugador==1){ 
            Tablero1 = NuevoTablero;
        }else{ 
            Tablero2 = NuevoTablero; 
        }
    }

    public static boolean Bombardear(int atacante, int fila, int columna){
        int oponente = (atacante==1) ? 2 : 1;
        String[][] DireccionBomba = (atacante==1) ? Tablero2 : Tablero1;
        String celda = DireccionBomba[fila][columna];

        if(!celda.equals("~") && !celda.equals(" X") && !celda.equals(" F")){
            DireccionBomba[fila][columna] = " X";  // SIN ESPACIOS
            RegenerarTablero(oponente);
            System.out.println("LE DISTE");
            return true;

        }else{
            DireccionBomba[fila][columna] = " F";  // SIN ESPACIOS
            System.out.println("FALLASTE");
            return false;
        }
    }

    private static boolean PuedeReubicar(String[][] tablero, int fila, int columna, int tamaño, char direccion){
        int NextFila = 0;
        int NextColumna = 0;
        for(int i=0; i<tamaño; i++){
            NextFila = (direccion=='V') ? fila+i : fila;
            NextColumna = (direccion=='H') ? columna+i : columna;
            if(NextFila<0 || NextFila>=8 || NextColumna<0 || NextColumna>=8 || !tablero[NextFila][NextColumna].equals("~")){ 
                return false; 
            }
        }
        return true;
    }

    public static void LimpiarRastros(int numOponente){
        String[][] tablero = (numOponente==1) ? Tablero1 : Tablero2;
        
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(tablero[x][y].equals(" F")){ tablero[x][y] = "~"; }
            }
        }
    }

    public static int ContarBarcosRestantes(int numOponente){
        String[][] tablero = (numOponente==1) ? Tablero1 : Tablero2;
        int vivos = 0;
        boolean encontrado;
        
        for(String z : tipos){
            encontrado = false;
            
            for(int x=0; x<8; x++){
                for(int y=0; y<8; y++){
                    if(tablero[x][y].equals(z)){ 
                        encontrado = true; 
                        break; 
                    }
                }
                if(encontrado){ 
                    break; 
                }
            }
            if(encontrado){ 
                vivos++; 
            }
        }
        return vivos;
    }
    
    public static boolean barcoYaExiste(int jugador, String tipo){
        String[][] tablero = (jugador==1) ? Tablero1 : Tablero2;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(tablero[x][y].equals(tipo)){ 
                    return true; 
                }
            }
        }
        return false;
    }
    
    public static boolean procesarColocacion(int jugador, int f, int c, String tipo, char rot){
        String[][] tab=(jugador==1)?Tablero1:Tablero2;

        int piezas=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(tab[i][j].equals(tipo)){
                    piezas++;
                }
            }
        }

        if(piezas>0){
            if(!(Dificultad==1 && tipo.equals("DT") && piezas<4)){
                return false; 
            }
        }

        if(puedeColocar(jugador, f, c, tipo, rot)){
            ColocarBarco(jugador, f, c, tipo, rot);
            return true;
        }
        return false;
    }
    
    public static boolean puedeColocar(int jugador, int fila, int columna, String tipo, char rotacion){
        int TamañoBarco = obtenerTamaño(tipo);
        int NextFila = 0, NextColumna = 0, espaciosOcupados = 0;
        String[][] tablero = (jugador==1) ? Tablero1 : Tablero2;

        for(int r=0; r<8; r++){
            for(int k=0; k<8; k++){
                if(tablero[r][k].equals(tipo)){ 
                    espaciosOcupados++; 
                }
            }
        }

        if(espaciosOcupados > 0){
            if(Dificultad == 1 && tipo.equals("DT") && espaciosOcupados < 4){
                
            }else{
                return false; 
            }
        }

        for(int i=0; i<TamañoBarco; i++){
            NextFila = (rotacion=='V') ? fila+i : fila;
            NextColumna = (rotacion=='H') ? columna+i : columna;
            if(NextFila<0 || NextFila>=8 || NextColumna<0 || NextColumna>=8 || !tablero[NextFila][NextColumna].equals("~")){ 
                return false; 
            }
        }
        return true;
    }

    public static boolean BuscarBarcos(int numJugador){
        String[][] tablero = (numJugador==1) ? Tablero1 : Tablero2;
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                if(tablero[x][y].equals("PA") || tablero[x][y].equals("DT") || tablero[x][y].equals("AZ") || tablero[x][y].equals("SM")){ return true; }
            }
        }
        return false;
    }
    
    public static void RegistrarJuegos(String ganador, String perdedor, boolean fueRetiro){
        String frase="";
        String modoTexto="";

        switch(Dificultad){
            case 1: modoTexto="EASY"; 
                break;
            case 2: modoTexto="NORMAL"; 
                break;
            case 3: modoTexto="EXPERT"; 
                break;
            case 4: modoTexto="GENIUS"; 
                break;
        }

        if(fueRetiro){
            frase=perdedor+" se retiro del juego dejando como ganador a "+ganador+".";
        }else{
            frase=ganador+" hundio todos los barcos de "+perdedor+" en modo "+modoTexto+".";
        }

        // Guardamos en el objeto Player de la lista global
        int indiceGanador=Player.obtenerIndice(ganador);
        int indicePerdedor=Player.obtenerIndice(perdedor);

        if(indiceGanador!=-1){
            listaUsuarios.get(indiceGanador).SaveLog(frase);
        }
        if(indicePerdedor!=-1){
            listaUsuarios.get(indicePerdedor).SaveLog(frase);
        }
    }
        
    //=========================METODOS DE IMPRIMIR=====================================================

    public static void imprimirTableroSolo(int numJugador){
        String[][] tab = (numJugador==1) ? Tablero1 : Tablero2;
        System.out.println("\n   0  1  2  3  4  5  6  7");
        
        for(int x=0; x<8; x++){
            System.out.print(x + " ");
            
            for(int y=0; y<8; y++){
                if(tab[x][y].equals("~")){ 
                    System.out.print(" ~ "); 
                }else{ 
                    System.out.print(tab[x][y] + " "); 
                }
            }
            System.out.println();
        }
    }

    public static void imprimirPantallaDeJuego(int turno){
        String[][] TableroP1 = (turno==1) ? Tablero1 : Tablero2;
        String[][] TableroP2 = (turno==1) ? Tablero2 : Tablero1;
        int x = 0;
        int y = 0;
        String CeldaP2 = "";
        String CeldaP1 = "";

        System.out.println("\n      TU TABLERO                TABLERO ENEMIGO");
        System.out.println("   0  1  2  3  4  5  6  7        0  1  2  3  4  5  6  7");

        for(x=0; x<8; x++){
            System.out.print(x + " ");

            for(y=0; y<8; y++){
                CeldaP1 = TableroP1[x][y];
                if(CeldaP1.equals("~")){ 
                    System.out.print(" ~ "); 
                }else{ 
                    System.out.print(CeldaP1 + " "); 
                }
            }

            System.out.print("     " + x + " ");

            for(y=0; y<8; y++){
                CeldaP2 = TableroP2[x][y];

                // X y F siempre se muestran
                if(CeldaP2.equals("X") || CeldaP2.equals("F")){
                    System.out.print(" " + CeldaP2 + " ");
                }
                // En ARCADE ocultar barcos enemigos
                else if(modoActual.equals("ARCADE")){
                    System.out.print(" ~ ");
                }
                // En TUTORIAL mostrar todo
                else{
                    System.out.print(CeldaP2.equals("~") ? " ~ " : CeldaP2 + " ");
                }
            }
            System.out.println();
        }
    }

}