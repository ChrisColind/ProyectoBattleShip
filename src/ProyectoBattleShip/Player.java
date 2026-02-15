/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;
import static ProyectoBattleShip.MenusDelJuego.listaUsuarios;
import java.util.ArrayList;
public class Player {
    private String nombre; 
    private String contra;
    private int puntos=0;
    private ArrayList<String> log;
    private int contadorLogs=0;
    private String[] historial=new String[10];
    
    public Player(String nombre, String contra){
        this.nombre = nombre;
        this.contra = contra;
        this.puntos = 0;
        this.log = new ArrayList<>(); 
    }
    
    public void SaveLog(String Registro){
        for(int x=9; x>0; x--){
            historial[x] = historial[x-1];
        }
        
        historial[0] = Registro;
    }
    
    public String[] getLogs(){ 
        
        return historial;
    }
    
    public void SetLog(String frase){
        if(contadorLogs<10){
            historial[contadorLogs]=frase;
            contadorLogs++;
        }else{
            for(int i=0; i<9; i++){
                historial[i]=historial[i+1];
            }
            historial[9]=frase;
        }
    }
    
    public String[] getHistorial(){
        return historial;
    }
    
    public int getPuntos(){
        
        return puntos;
    }
    
    public ArrayList<String> getLog(){ //Muestra el log
        
        return log;
    }
    
    public void SumarPuntos(int puntos){ //Guarda puntos de cada jugador
        this.puntos += puntos;
        
    }
    public String getNombre(){
        
        return nombre;
    }
    
    public String getContra(){
        
        return contra;
    }
    public void SetContra(String contra){
        
        this.contra = contra;
    }
    public void SetNombre(String nombre){
        
        this.nombre = nombre;
    }
    
    public static int obtenerIndice(String nombre){
        int resultado =-1;
        for(int x=0; x<listaUsuarios.size(); x++){
            if(listaUsuarios.get(x).getNombre().equals(nombre)){
                resultado = x;
                break;
            }
        }
        
        return resultado;
    }
}
