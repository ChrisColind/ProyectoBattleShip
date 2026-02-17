/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoBattleShip;

/**
 *
 * @author croge
 */
public class ModoYDificultad {
    enum Dificultad{
        EASY(5),
        NORMAL(4),
        EXPERT(2),
        GENIUS(1);
        
        private int cantidadBarcos;
        
        Dificultad(int cantidadBarcos){
        this.cantidadBarcos = cantidadBarcos;
        }
        
        public int getCantidadBarcos(){
            return cantidadBarcos;
        }
    }
    enum ModoJuego{
        ARCADE,
        TUTORIAL
    }
}
