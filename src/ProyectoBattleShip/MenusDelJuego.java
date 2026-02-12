/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Timer;
public class MenusDelJuego {
    
    static int siguienteMenu=0;
    static int contador=1;
    static String jugador1;
    static String jugador2;
    static int indice1;  
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
                     
                    if(contador==1){
                        jugador1=user;
                        indice1=x;
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
                }
                
            }while(siguienteMenu==0);
            
    }
    
    public static void Dificultad(){
        Scanner n = new Scanner(System.in);
        int opcion;
        int dificultad = 0;
        boolean opcionCorrecta=false;
        
        while(!opcionCorrecta){
            System.out.println("1. EASY (5 barcos)");
            System.out.println("2. NORMAL (4 barcos)");
            System.out.println("3. EXPERT (2 barcos)");
            System.out.println("4. GENIUS (1 barco)");
            System.out.println("Elija la dificultad: ");
            opcion = n.nextInt();
            
        switch(opcion){
            case 1: dificultad = 5;
            break;
            
            case 2: dificultad = 4;
            break;
            
            case 3: dificultad = 2;
            break;
            
            case 4: dificultad = 1;
            break;
        }
            if(opcion<=4 && opcion>0){
                Juego.Dificultad = opcion;
                System.out.println("Dificultad actualizada");
                /*Juego.registrarResultado("", "", dificultad);*/
                opcionCorrecta=true;
            }else{
                System.out.println("Elija una opcion correcta.");
                opcionCorrecta=false;
            }
            
        }
        MenuConfiguracion();
    }
    
    public static void ModoDeJuego() {
        Scanner n = new Scanner(System.in);
        
        int opcion;
        String modoJuego;
        
        System.out.println("\n===SELECCIONAR MODO DE JUEGO===");
        System.out.println("1. ARCADE (Barcos enemigos ocultos)");
        System.out.println("2. TUTORIAL (Barcos enemigos visibles)");
        System.out.println("3. Regresar");
        System.out.print("Seleccione una opcion: ");

        opcion = n.nextInt();

        switch(opcion) {
            case 1: modoJuego = "ARCADE";
                System.out.println("Modo cambiado a: ARCADE");
                break;
                
            case 2: modoJuego = "TUTORIAL";
                System.out.println("Modo cambiado a: TUTORIAL");
                break;
                
            case 3: MenuConfiguracion();
                break;
        }
        MenuConfiguracion();
    }
    
    
    //MENU DE LOS MENUS 2 ==================================================
    static void MenuReportes(){
        int elegir;
        Scanner n = new Scanner(System.in);
        
        System.out.println("\n===MENU DE Reportes===");
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
                }
                
            }while(siguienteMenu==0);
    }
    public static void UltimosJuegos() {
        Scanner n = new Scanner(System.in);
        System.out.println("\n===ULTIMOS 10 JUEGOS===");
        ArrayList<String> historial = listaUsuarios.get(indice1).getLog();

        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay juegos registrados aun");
        }else{
            int contadorMostrados = 1;
            
            for (int i = historial.size() - 1; i >= 0; i--) {
                System.out.println(contadorMostrados + "- " + historial.get(i));

                if (contadorMostrados == 10) {
                    break;
                }
                contadorMostrados++;
            }
        }

        System.out.println("\nPresione Enter para regresar al menu de reportes");
        n.nextLine(); 
        MenuReportes();
    }
    
    public static void Ranking(){
        
    }
    
    //MENU DE LOS MENUS 3 ==================================================
    static void MenuMiPerfil(){
        int elegir;
        Scanner n = new Scanner(System.in);
        System.out.println("");
        System.out.println("===MENU DE MI PERFIL===");
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
                }
                
            }while(siguienteMenu==0);
    }
    public static void VerDatos(){
        Scanner n = new Scanner(System.in);
        Player p = listaUsuarios.get(indice1);
        System.out.println("Nombre: " + p.getNombre() + " Puntos: " + p.getPuntos());
        System.out.println("\nPresione Enter para regresar al menu de reportes");
        n.nextLine(); 
        MenuMiPerfil();
    }
    
    public static void ModificarDatos(){
        Scanner n = new Scanner(System.in);
        String cambiar;
        String contra;
        String NuevoNombre;
        boolean SeCambio=false;
        boolean existe;
        
        System.out.println("Desea cambiar su usuario o su contraseña? (u o c)");
        cambiar = n.nextLine();
        
        
        while(!SeCambio){
            if(cambiar.equalsIgnoreCase("u")){
                System.out.println("Elija el nuevo usuario: ");
                NuevoNombre = n.nextLine();
                existe=false;
                        
                for(Player p : listaUsuarios){
                    if(p.getNombre().equals(NuevoNombre)){
                        existe = true;
                    }
                }
                
                if(existe){
                    System.out.println("Este usuario ya existe.");
                }else{
                    listaUsuarios.get(indice1).SetNombre(NuevoNombre);
                    jugador1 = NuevoNombre;
                    System.out.println("Usuario cambiado");
                    SeCambio=true;
                }
                
            }else if(cambiar.equalsIgnoreCase("c")){
                System.out.println("Elija la nueva contraseña: ");
                contra = n.nextLine();
                listaUsuarios.get(indice1).SetContra(contra);
                System.out.println("Contraseña cambiada con exito");
                SeCambio=true;
                
            }else{
                System.out.println("Opcion invalida. Escriba u(para cambiar usuario) o c(para cambiar contraseña)");
                cambiar = n.nextLine();
            }
        }
        MenuMiPerfil();
    }
    
    public static void EliminarCuenta(){
        Scanner n = new Scanner(System.in);
        String decidir;
        boolean Elemino=false;
        
        System.out.println("Desea eliminar la cuenta logeada (si o no)?");
        System.out.println("cuenta logeada: "+jugador1);
        decidir=n.nextLine();
        
        while(!Elemino){

            if(decidir.equalsIgnoreCase("si")){
                System.out.println("Esta segur@ de eliminar su cuenta (si o no)?");
                System.out.println("cuenta logeada: "+jugador1);
                decidir=n.nextLine();

                if(decidir.equalsIgnoreCase("si")){
                    listaUsuarios.remove(indice1);
                    System.out.println("Se elemino la cuenta.");
                    Elemino=true;

                }else if(decidir.equalsIgnoreCase("no")){
                    Elemino=true;
                }else{
                    System.out.println("Escriba si(para eliminar la cuenta) o no(para no eliminarla)");
                    decidir=n.nextLine();
                }
                
            }else if(decidir.equalsIgnoreCase("no")){
                Elemino=true;

            }else{
                System.out.println("Escriba si(para eliminar la cuenta) o no(para no eliminarla)");
                decidir=n.nextLine();
            }
        }
        Login();
    }
    
}
