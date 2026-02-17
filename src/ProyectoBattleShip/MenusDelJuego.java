/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class MenusDelJuego {
    
    static int siguienteMenu=0;
    static int contador=1;
    static String jugador1;
    static String jugador2;
    static int indice1;  
    static ArrayList<Player> listaUsuarios = new ArrayList<>();
    boolean UsuarioExistente;
    
    public static void main(String[] args) {
        
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
    
    //MENU DE LOS MENU ==================================================================================
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
    //1 , Menu , MENU JUGAR
    public static void MenuJugar(){
        String user;
        boolean UsuarioExiste=false;
        Scanner n = new Scanner(System.in);

        while(!UsuarioExiste){
            System.out.println("");
            System.out.println("===MENU JUGAR===");
            System.out.println("JUGADOR 1: "+jugador1);
            System.out.println("Escriba el usuario del jugador 2 (o EXIT para salir): ");
            user = n.nextLine();

            if(user.equalsIgnoreCase("EXIT")){
                Menu();
                return;
            }

            if(user.equals(jugador1)){
                System.out.println("Este jugador ya esta en uso.");
                continue;
            }

            for(int x=0; x<listaUsuarios.size(); x++){
                if(listaUsuarios.get(x).getNombre().equals(user)){
                    jugador2=user;
                    UsuarioExiste=true;

                    Juego.jugador1 = MenusDelJuego.jugador1;
                    Juego.jugador2 = MenusDelJuego.jugador2;
                    Juego.vaciarTableros(); 

                    int limiteBarcos = 4; 
                    if(Juego.Dificultad==1){
                        limiteBarcos=5;
                    }
                    
                    if(Juego.Dificultad==3){
                        limiteBarcos=2;
                    }
                    
                    if(Juego.Dificultad==4){
                        limiteBarcos=1;
                    }

                    System.out.println("Jugador 1 coloca tus barcos");
                    MenuParaColocar(1, limiteBarcos);
                    crearEspacio();
                    
                    System.out.println("Jugador 2 coloca tus barcos");
                    MenuParaColocar(2, limiteBarcos);
                    crearEspacio();

                    Jugar();
                    break;
                }
            }
            
            if(!UsuarioExiste){
                System.out.println("No se encontro el jugador 2");
            }
        }
    }
    
    //SOLO PARA CREAR ESPACIO EN LA CONSOLA
    public static void crearEspacio() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    //1 , Menu , MENU JUGAR, el juego en si
    public static void Jugar(){
    Scanner n = new Scanner(System.in);
    
    int turno = 1;
    int oponente;
    int fila = 0;
    int columna = 0;
    int IndiceDelGanador;

    boolean fin = false;
    String atacante;
    String ganador = "";
    String perdedor = "";
    String confirmacion;
    String mensajeAnterior = "";
    
    while(!fin){
        try{
            atacante = (turno==1) ? jugador1 : jugador2;
            oponente = (turno==1) ? 2 : 1;

            Juego.LimpiarRastros(oponente);
            System.out.println("------------------------------");
            System.out.println("TURNO DE: " + atacante.toUpperCase());
            System.out.println("BARCOS ENEMIGOS RESTANTES: " + Juego.ContarBarcosRestantes(oponente));
            
            if(!mensajeAnterior.equals("")){
                System.out.println(mensajeAnterior);
            }

            Juego.imprimirPantallaDeJuego(turno);

            System.out.println("Ingrese fila para atacar (0-7 o -1 para salir):");
            fila = n.nextInt();

            if(fila==-1){ 
                System.out.println("Esta seguro que desea retirarse? (si o no)");
                n.nextLine();
                confirmacion = n.nextLine();

                if(confirmacion.equalsIgnoreCase("si")){
                    ganador = (turno==1) ? jugador2 : jugador1;
                    perdedor = atacante;
                    System.out.println(ganador + " GANA POR RETIRO DE " + perdedor);

                    IndiceDelGanador = (turno==1) ? Player.obtenerIndice(jugador2) : indice1;
                    listaUsuarios.get(IndiceDelGanador).SumarPuntos(3);

                    Juego.RegistrarJuegos(ganador, perdedor, true);
                    fin = true;
                    continue;
                }else{
                    continue;
                }
            }

            if(fila < 0 || fila > 7){
                System.out.println("Fila invalida.");
                continue;
            }

            System.out.println("Ingrese columna para atacar (0-7 o -1 para salir):");
            columna = n.nextInt();

            if(columna == -1){
                System.out.println("Esta seguro que desea retirarse? (si o no)");
                n.nextLine();
                confirmacion = n.nextLine();

                if(confirmacion.equalsIgnoreCase("si")){
                    ganador = (turno==1) ? jugador2 : jugador1;
                    perdedor = atacante;
                    System.out.println(ganador + " GANA POR RETIRO DE " + perdedor);

                    IndiceDelGanador = (turno==1) ? Player.obtenerIndice(jugador2) : indice1;
                    listaUsuarios.get(IndiceDelGanador).SumarPuntos(3);

                    Juego.RegistrarJuegos(ganador, perdedor, true);
                    fin = true;
                    continue;
                }else{
                    continue;
                }
            }

            if(columna < 0 || columna > 7){
                System.out.println("Columna invalida.");
                continue;
            }

            boolean acierto = Juego.Bombardear(turno, fila, columna);
            
            if(acierto){
                mensajeAnterior = "***" + atacante.toUpperCase() + " LE DIO A UN BARCO***";
                
                if(Juego.ContarBarcosRestantes(oponente)==0){
                    ganador = atacante;
                    perdedor = (turno==1) ? jugador2 : jugador1;

                    System.out.println("VICTORIA PARA " + ganador);
                    IndiceDelGanador = (turno==1) ? indice1 : Player.obtenerIndice(jugador2);
                    listaUsuarios.get(IndiceDelGanador).SumarPuntos(3);

                    Juego.RegistrarJuegos(ganador, perdedor, false);
                    fin = true;
                }
                
            }else{
                mensajeAnterior = "***" + atacante.toUpperCase() + " FALLO EL TIRO***";
                turno = (turno==1) ? 2 : 1;
            }

        }catch(Exception e){
            System.out.println("Entrada invalida. Por favor ingrese un numero.");
            n.nextLine();
            continue;
        }
    }
}
    
    //2 , Menu , MENU JUGAR, Menu para colocar barcos
    public static void MenuParaColocar(int jugador, int limite){ 
        Scanner n = new Scanner(System.in);
        String tipo = "";
        char rotacion;
        int columna = 0, fila = 0;
        boolean barcoValido;   
        
        for(int x = 0; x < limite; x++){
            barcoValido = false;

            while(!barcoValido){
                try{
                    Juego.imprimirTableroSolo(jugador); 
                    System.out.println("Colocando barco " + (x + 1) + "/" + limite);

                    System.out.println("Elija su barco(PA, AZ, SM, DT) o (SALIR) para salir: ");
                    tipo = n.next().toUpperCase();

                    if(tipo.equalsIgnoreCase("salir")){
                        MenuJugar();
                        return;
                    }

                    if(!Arrays.asList(Juego.tipos).contains(tipo)){
                        System.out.println("Codigo de barco no reconocido.");
                        continue; 
                    }

                    if(Juego.barcoYaExiste(jugador, tipo)){
                        if(Juego.Dificultad == 1 && tipo.equals("DT")){
                            int contadorDT = 0;
                            String[][] tab = (jugador == 1) ? Juego.Tablero1 : Juego.Tablero2;
                            for(int r=0; r<8; r++){
                                for(int c=0; c<8; c++){
                                    if(tab[r][c].equals("DT")){
                                        contadorDT++;
                                    }
                                }
                            }
                            if(contadorDT >= 4){ 
                                System.out.println("Ya colocaste los dos barcos DT permitidos (4 espacios totales).");
                                continue;
                            }
                        }else{

                            System.out.println("El barco " + tipo + " ya ha sido colocado. Elige otro.");
                            continue;
                        }
                    }

                    System.out.println("Fila inicial (0-7 o -1 para salir): ");
                    fila = n.nextInt();

                    if(fila == -1){
                        System.out.println("Esta seguro que desea salir? (si o no)");
                        n.nextLine();
                        String confirmacion = n.nextLine();

                        if(confirmacion.equalsIgnoreCase("si")){
                            MenuJugar();
                            return;
                        }else{
                            continue;
                        }
                    }

                    System.out.println("Columna inicial (0-7 o -1 para salir): ");
                    columna = n.nextInt();

                    if(columna == -1){
                        System.out.println("Esta seguro que desea salir? (si o no)");
                        n.nextLine();
                        String confirmacion = n.nextLine();

                        if(confirmacion.equalsIgnoreCase("si")){
                            MenuJugar();
                            return;
                        }else{
                            continue;
                        }
                    }

                    System.out.println("rotacion (H/V): ");
                    rotacion = n.next().toUpperCase().charAt(0);

                    if(Juego.puedeColocar(jugador, fila, columna, tipo, rotacion)){
                        Juego.ColocarBarco(jugador, fila, columna, tipo, rotacion);
                        crearEspacio();
                        barcoValido = true; 
                    }else{
                        System.out.println("Posicion ocupada o fuera de limites.");
                    }

                }catch(Exception e){
                    System.out.println("Entrada invalida. Por favor ingrese un numero.");
                    n.nextLine(); 
                    continue;
                }
            }
        }
    }
    
    //2 , Menu , MENU CONFIGURACION
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
    
    //1 , Menu , MENU CONFIGURACION , Dificultad
    public static void Dificultad(){
        Scanner n = new Scanner(System.in);
        int opcion;
        boolean opcionCorrecta=false;
        
        while(!opcionCorrecta){
            System.out.println("1. EASY (5 barcos)");
            System.out.println("2. NORMAL (4 barcos)");
            System.out.println("3. EXPERT (2 barcos)");
            System.out.println("4. GENIUS (1 barco)");
            System.out.println("Elija la dificultad: ");
            opcion = n.nextInt();
            
            if(opcion<=4 && opcion>0){
                Juego.Dificultad = opcion;
                System.out.println("Dificultad actualizada");
                opcionCorrecta=true;
            }else{
                System.out.println("Elija una opcion correcta.");
                opcionCorrecta=false;
            }
        }
        MenuConfiguracion();
    }
    
    //2 , Menu , MENU CONFIGURACION , Modo del juego
    public static void ModoDeJuego(){
        Scanner n = new Scanner(System.in);
        
        int opcion;
        
        System.out.println("\n===SELECCIONAR MODO DE JUEGO===");
        System.out.println("1. ARCADE (Barcos enemigos ocultos)");
        System.out.println("2. TUTORIAL (Barcos enemigos visibles)");
        System.out.println("3. Regresar");
        System.out.println("\nSeleccione una opcion: ");

        opcion = n.nextInt();

        switch(opcion){
            case 1: Juego.modoActual = "ARCADE";
                System.out.println("Modo cambiado a: ARCADE");
                break;
                
            case 2: Juego.modoActual = "TUTORIAL";
                System.out.println("Modo cambiado a: TUTORIAL");
                break;
                
            case 3: MenuConfiguracion();
                break;
        }
        MenuConfiguracion();
    }
    
    
    //3, Menu, MENU REPORTES
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
    
    //1, Menu, MENU REPORTES, Ultimos Juegos
    public static void UltimosJuegos(){
        Player user=listaUsuarios.get(indice1);
        String[] historial=user.getLogs();  

        System.out.println("===ULTIMOS JUEGOS===");

        int contador=1;
        for(int i=0; i<historial.length; i++){
            if(historial[i]!=null){
                System.out.println(contador+"- "+historial[i]);
                contador++;
            }
        }

        if(contador==1){
            System.out.println("No hay partidas registradas.");
        }
        
        MenuReportes();
    }
    
    //2, Menu, MENU REPORTES, Ranking
    public static void Ranking(){
        Scanner n = new Scanner(System.in);
        String salir;
        Player UsuarioTemporal;
        
        for(int x=0; x<listaUsuarios.size()-1; x++){
            for(int y=0; y<listaUsuarios.size()-x-1; y++){
                if(listaUsuarios.get(y).getPuntos() < listaUsuarios.get(y+1).getPuntos()){
                    UsuarioTemporal = listaUsuarios.get(y);
                    listaUsuarios.set(y, listaUsuarios.get(y+1));
                    listaUsuarios.set(y+1, UsuarioTemporal);
                }
            }
        }
        System.out.println("\n===RANKING DE PUNTOS===");
        for(int x=0; x<listaUsuarios.size(); x++){
            System.out.println((x+1) + ". " + listaUsuarios.get(x).getNombre() + " - " + listaUsuarios.get(x).getPuntos() + " pts");
            System.out.println("Ingrese (S) para salir");
            salir = n.nextLine().toUpperCase();
        
            if(salir.equals("S")){
                MenuReportes();
                break;
            }
        }
    }
    
    //4, Menu, MENU MI PERFIL
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
    //1, Menu, MENU MI PERFIL, Ver datos
    public static void VerDatos(){
        Scanner n = new Scanner(System.in);
        Player jugador = listaUsuarios.get(indice1);
        String salir;
        
        System.out.println("Nombre: " + jugador.getNombre() + " Puntos: " + jugador.getPuntos());
        System.out.println("Presione (S) para salir");
        salir = n.nextLine();
        
        if(salir.equalsIgnoreCase("S")){
            MenuMiPerfil();
        }
    }
    
    //2, Menu, MENU MI PERFIL, Modificar DAtos
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
    
    //3, Menu, MENU MI PERFIL, Eliminar cuenta
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
