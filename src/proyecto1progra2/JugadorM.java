/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;

/**
 *
 * @author User
 */
public interface JugadorM {
    boolean agregarJugador(String username, String password);
    Jugador buscarJugador(String username);
    boolean eliminarJugador(String username);
    boolean validarLogin(String username, String password);
    Jugador[] obtenerRanking();
}


