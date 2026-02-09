/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
public class Main {
    
    static int siguienteMenu=0;
    static int contador=1;
    static String jugador1;
    static String jugador2;
    static String jugadorIniciado;
    static ArrayList<Player> listaUsuarios = new ArrayList<>();
    boolean UsuarioExistente;
    
    public static void main(String[] args) {
        listaUsuarios.add(new Player("car","1"));
        listaUsuarios.add(new Player("chri","1"));
        Login();
        
    }
    
    //login , PRINCIPAL  =================================================================
    static void Login(){
        Scanner n = new Scanner(System.in);
        int elegir;
        
        do{
            contador=1;
            System.out.println("===INICIA SESION===");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Crear jugador");
            System.out.println("3. Salir");
            System.out.println("\nSeleccione una opcion: ");
            elegir = n.nextInt();
            
                switch(elegir){
                    case 1: IniciarSesion();
                    break;

                    case 2: CrearJugador();
                    break;
                    
                    case 3: SalirJuego();
                    break;
                }
            }while(siguienteMenu==0);
    }
    
    //1 , Login , INICIAR SESION
    public static void IniciarSesion(){
        Scanner n = new Scanner(System.in);
        String contra = null;
        String user = null;
        boolean UsuarioEncontrado=false;
        
        while(UsuarioEncontrado==false){
            
            if(!listaUsuarios.isEmpty()){
                
                System.out.println("Ingrese su usuario: ");
                user = n.nextLine();

                System.out.println("Ingrese su contraseña: ");
                contra = n.nextLine();
            }else{
                
                System.out.println("No hay ningun usuario registrado.");
                System.out.println("");
                Login();
            }
            
            for(int x=0 ; x<listaUsuarios.size() ; x++){
                if(listaUsuarios.get(x).getNombre().equals(user) && listaUsuarios.get(x).getContra().equals(contra)){
                    System.out.println("Ingresado con exito");
                     System.out.println(listaUsuarios.size());
                    if(contador==1){
                        jugador1=user;
                        contador++;
                    }
                    
                    UsuarioEncontrado=true;
                    Menu();
                }
            }
            
            if(!UsuarioEncontrado){
                System.out.println("Usuario o contraseña incorrecta");
            }
        }
    }
    
    //2 , Login , CREAR JUGADOR
    public static void CrearJugador(){
        Scanner n = new Scanner(System.in);
        String contra;
        String user;
        
        boolean UsuarioExistente=true;
         
            System.out.println("Ingrese su usuario: ");
            user = n.nextLine();

            System.out.println("Ingrese su contraseña: ");
            contra = n.nextLine();
            
        while(UsuarioExistente){
            UsuarioExistente=false;
            
            for(int x=0 ; x<listaUsuarios.size(); x++){
                if(listaUsuarios.get(x).getNombre().equals(user)){
                        
                    System.out.println("El usuario ya existe.");
                    System.out.println("Ingrese un usuario nuevo: ");
                    user = n.nextLine();
                    UsuarioExistente=true;
                    break;
                }
            }
        }
        
        Player Pl = new Player(user,contra);
        listaUsuarios.add(Pl);
        System.out.println("Se creo con exito el usuario!");
        System.out.println("");
    }
    //3, login , Salir Juego
    public static void SalirJuego(){
        System.exit(0);
    }
    
    //MENU DE INICIO ==================================================================================
    static void Menu(){
        
            int elegir;
            Scanner n = new Scanner(System.in);
        do{
        System.out.println("");
        System.out.println("===MENU DE INICIO===");
        System.out.println("1. Jugar");
        System.out.println("2. Configuracion");
        System.out.println("3. Reportes");
        System.out.println("4. Mi perfil");
        System.out.println("5. Salir");
        System.out.println("JUGADOR 1: "+jugador1);
        System.out.println("\nSeleccione una opcion: ");
        elegir = n.nextInt();
        
                switch(elegir){
                    case 1: 
                    MenuJugar();
                    break;
                        
                    case 2: 
                    MenuConfiguracion();
                    break;
                    
                    case 3: 
                    MenuReportes();
                    break;
                    
                    case 4: 
                    MenuMiPerfil();
                    break;
                    
                    case 5: 
                    Login();
                    break;
                }
                
            }while(siguienteMenu==0);
    }
    
    //MENU DE LOS MENUS ===========================================================================================================================================
    public static void MenuJugar(){
        String user;
        boolean UsuarioExiste=false;
        Scanner n = new Scanner(System.in);
        
        while(!UsuarioExiste){
            System.out.println("");
            System.out.println("===MENU JUGAR===");
            System.out.println("JUGADOR 1: "+jugador1);
            System.out.println("Escriba el usuario del jugador 2: ");
            user = n.nextLine();
            
            if(user.equals("EXIT")){
                Menu();
            }
            if(user.equals(jugador1)){
                System.out.println("Este jugador ya esta en uso.");
            }
                
            for(int x=0 ; x<listaUsuarios.size() ; x++){
                if(listaUsuarios.get(x).getNombre().equals(user)){
                    jugador2=user;
                    System.out.println("");
                    System.out.println("Se encontro el jugador 2");
                    System.out.println("JUGADOR 2: "+jugador2);
                    UsuarioExiste=true;
                    Jugar();
                    break;
                    
                }
            }
                if(!UsuarioExiste){
                    System.out.println("No se encontro el jugador 2");
                }
        }
    }
    
    public static void Jugar(){
        System.out.println("En progreso");
        Juego P = new Juego();
        P.Tablero();
    }

    
    //MENU DE LOS MENUS ================================================
    static void MenuConfiguracion(){
        int elegir;
        Scanner n = new Scanner(System.in);
        System.out.println("");
        System.out.println("===MENU DE CONFIGURACION===");
        System.out.println("1. Dificultad");
        System.out.println("2. Modo de juego");
        System.out.println("3. Regresar al menu principal");
        System.out.println("JUGADOR 1: "+jugador1);
        System.out.println("\nSeleccione una opcion: ");
        
        do{
                elegir = n.nextInt();
                switch(elegir){
                    
                    case 1: Dificultad();
                    break;
                        
                    case 2: ModoDeJuego();
                    break;
                    
                    case 3: Menu();
                    break;
                }
                
            }while(siguienteMenu==0);
            
    }
    
    public static void Dificultad(){
        System.out.println("Elija la dificultad: ");
        
    }
    public static void ModoDeJuego(){
        System.out.println("Elija el modo de juego: ");
    }
    
    
    //MENU DE LOS MENUS 2 ==================================================
    static void MenuReportes(){
        int elegir;
        Scanner n = new Scanner(System.in);
        System.out.println("");
        System.out.println("===MENU DE Reportes===");
        System.out.println("1. Ultimos 10 juegos");
        System.out.println("2. Ranking de jugadores");
        System.out.println("3. Regresar al menu principal");
        System.out.println("JUGADOR 1: "+jugador1);
        System.out.println("\nSeleccione una opcion: ");
        do{
                elegir = n.nextInt();
                switch(elegir){
                    
                    case 1: UltimosJuegos();
                    break;
                        
                    case 2: Ranking();
                    break;
                    
                    case 3: Menu();
                    break;
                }
                
            }while(siguienteMenu==0);
    }
    public static void UltimosJuegos(){
        
    }
    
    public static void Ranking(){
        
    }
    
    //MENU DE LOS MENUS 3 ==================================================
    static void MenuMiPerfil(){
        int elegir;
        Scanner n = new Scanner(System.in);
        System.out.println("");
        System.out.println("===MENU DE Reportes===");
        System.out.println("1. Ver mis datos");
        System.out.println("2. Modificar mis datos");
        System.out.println("3. Eleminar cuenta");
        System.out.println("4. Regresar al menu principal");
        System.out.println("JUGADOR 1: "+jugador1);
        System.out.println("\nSeleccione una opcion: ");
        do{
                elegir = n.nextInt();
                switch(elegir){
                    
                    case 1: VerDatos();
                    break;
                        
                    case 2: ModificarDatos();
                    break;
                    
                    case 3: EliminarCuenta();
                    break;
                    
                    case 4: Menu();
                    break;
                }
                
            }while(siguienteMenu==0);
    }
    public static void VerDatos(){
        
    }
    
    public static void ModificarDatos(){
        
        System.out.println("Desea cambiar su usuario o su contrase;a? (u o c)");
        
    }
    
    public static void EliminarCuenta(){
        Scanner n = new Scanner(System.in);
        String decidir;
        int indice=0;
        
        for(int x=0 ; x<listaUsuarios.size() ; x++){
            if(listaUsuarios.get(x).getNombre().equals(jugador1)){
                x=indice;   
               System.out.println(indice);    
            }
        }
        
        System.out.println("Desea eliminar la cuenta logeada (si o no)?");
        System.out.println("cuenta logeada: "+jugador1);
        decidir=n.nextLine();
        
        if(decidir.equalsIgnoreCase("si")){
            System.out.println("Esta segur@ de eliminar su cuenta (si o no)?");
            System.out.println("cuenta logeada: "+jugador1);
            decidir=n.nextLine();
            
            if(decidir.equalsIgnoreCase("si")){
                listaUsuarios.remove(indice);
                System.out.println("Se elemino la cuenta.");
                Login();
                System.out.println("");
            }else{
                MenuMiPerfil();
            }
        }else{
            MenuMiPerfil();
        }
            
    }
    
    
    
}
