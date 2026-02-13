/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoBattleShip;

public class Main {
    /**
     * El MAIN es simple: solo enciende el programa.
     * Requisito: No tiene lógica de juego.
     */
    public static void main(String[] args) {
        
        MenusDelJuego.listaUsuarios.add(new Player("chri", "1"));
        MenusDelJuego.listaUsuarios.add(new Player("car","1"));
        
        MenusDelJuego.Login();
    }
}