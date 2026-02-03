/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    
    static int siguienteMenu=0;
    
    static ArrayList<Player> listaUsuarios = new ArrayList<>();
    boolean UsuarioExistente;
    
    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        int menu=0;
        int elegir=0;
        
        
        while(menu==0){
            do{
                Login();
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
            
            do{
                Menu();
                elegir = n.nextInt();
                switch(elegir){
                    case 1: Jugar();
                    break;
                        
                    case 2: Configuracion();
                    break;
                    
                    case 3: Reportes();
                    break;
                    
                    case 4: MiPerfil();
                    break;
                    
                    case 5: Salir();
                    break;
                }
                
            }while(siguienteMenu==1);
            
            do{
                MenuConfiguracion();
                elegir = n.nextInt();
                switch(elegir){
                    
                    case 1: Dificultad();
                    break;
                        
                    case 2: ModoDeJuego();
                    break;
                    
                    case 3: Regresar();
                    break;
                }
                
            }while(siguienteMenu==12);
            
        }
    }
    
    //login , PRINCIPAL  =================================================================
    static void Login(){
            System.out.println("===INICIA SESION===");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Crear jugador");
            System.out.println("3. Salir");
            System.out.println("\nSeleccione una opcion: ");
            
    }
    //1 , Login , INICIAR SESION
    public static void IniciarSesion(){
        Scanner n = new Scanner(System.in);
        String contra;
        String user;
        boolean UsuarioEncontrado=false;
        
        while(UsuarioEncontrado==false){
        System.out.println("Ingrese su usuario: ");
        user = n.nextLine();
        
        System.out.println("Ingrese su contraseña: ");
        contra = n.nextLine();
        
            for(int x=0 ; x<listaUsuarios.size() ; x++){
                if(listaUsuarios.get(x).getNombre().equals(user) && listaUsuarios.get(x).getContra().equals(contra)){
                    System.out.println("Ingresado con exito");
                    UsuarioEncontrado=true;
                    siguienteMenu=1;
                    break;
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
    }
    //3, login , Salir Juego
    public static void SalirJuego(){
        System.exit(0);
    }
    
    //MENU DE INICIO ==================================================================================
    static void Menu(){
        System.out.println("===MENU DE INICIO==");
        System.out.println("1. Jugar");
        System.out.println("2. Configuracion");
        System.out.println("3. Reportes");
        System.out.println("4. Mi Perfil");
        System.out.println("5. Salir");
        System.out.println("\nSeleccione una opcion: ");
    }
    
    // 1, MENU, JUGAR
    public static void Jugar(){
        
    }
    
    public static void Configuracion(){
        siguienteMenu=12;
        
    }
    
    public static void Reportes(){
        
    }
    
    public static void MiPerfil(){
        
    }
    
    public static void Salir(){
        siguienteMenu=0;
        System.out.println(siguienteMenu);
    }
    
    //MENU DE LOS MENUS ================================================
    static void MenuConfiguracion(){
        System.out.println("===MENU DE CONFIGURACION===");
        System.out.println("1. Dificultad");
        System.out.println("2. Modo de juego");
        System.out.println("3. Regresar al Menu principal");
        System.out.println("\nSeleccione una opcion: ");
    }
    
    public static void Dificultad(){
        System.out.println("Elija la dificultad: ");
        
    }
    public static void ModoDeJuego(){
        System.out.println("Elija el modo de juego: ");
    }
    public static void Regresar(){
        siguienteMenu=1;
    }
}
