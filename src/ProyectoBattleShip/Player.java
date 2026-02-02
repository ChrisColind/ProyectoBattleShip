/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;

import java.util.ArrayList;
import java.util.logging.Level;

public class Player {
    private String nombre; 
    private String contra;
    private int puntos=0;
    private ArrayList<String> log;
    
    
    public Player(String nombre, String contra){
        this.nombre = nombre;
        this.contra = contra;
        
    }
    
    public void SetLog(String Registro){ //Guarda el log
        
        this.log.add(Registro);
        
    }
    
    public int getPuntos(){
        
        return puntos;
    }
    
    public ArrayList<String> getLog(){ //Muestra el log
        
        return log;
    }
    
    public void SetPuntos(int puntos){ //Guarda puntos de cada jugador
        this.puntos += puntos;
        
    }
    public String getNombre(){
        
        return nombre;
    }
    
    public String getContra(){
        
        return contra;
    }
}
