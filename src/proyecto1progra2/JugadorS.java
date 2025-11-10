/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author User
 */   

public final class JugadorS implements JugadorM {
    private Jugador[] jugadores = new Jugador[100];
    private int total = 0;

    @Override
    public boolean agregarJugador(String username, String password) {
        try {
            if (buscarJugador(username) != null) return false;
            if (!esPasswordValido(password)) return false; 

            if (total >= jugadores.length) {
            System.err.println("Error: No hay espacio para más jugadores.");
            return false;
            }

        jugadores[total++] = new Jugador(username, password);
        return true;
        } catch (Exception e) {
        System.err.println("Excepción al agregar jugador: " + e.toString());
        return false;
        }
    }

    @Override
    public Jugador buscarJugador(String username) {
        for (Jugador p : jugadores)
            if (p != null && p.getUsername().equalsIgnoreCase(username))
                return p;
        return null;
    }

    @Override
    public boolean eliminarJugador(String username) {
        try {
            for (int i = 0; i < total; i++) {
                if (jugadores[i] != null && jugadores[i].getUsername().equalsIgnoreCase(username)) {
                jugadores[i].setActivo(false);
                jugadores[i] = null;
                reorganizar(i);
                total--;
                return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.err.println("Excepción al eliminar jugador: " + e.toString());
            return false;
        }
    }

    private void reorganizar(int index) {
        if (index >= jugadores.length - 1 || jugadores[index + 1] == null) return;
        jugadores[index] = jugadores[index + 1];
        reorganizar(index + 1);
    }

    @Override
    public boolean validarLogin(String username, String password) {
        Jugador p = buscarJugador(username);
        return p != null && p.getPassword().equals(password) && p.isActivo();
    }

    @Override
    public Jugador[] obtenerRanking() {
        Jugador[] activos = Arrays.stream(jugadores)
                .filter(p -> p != null && p.isActivo())
                .toArray(Jugador[]::new);

        Arrays.sort(activos, Comparator.comparingInt(Jugador::getPuntos).reversed());
        return activos;
    }

    public boolean esPasswordValido(String password) {
        if (password.length() != 5) return false;
    
        String especiales = "!@#$%^&*()_+-=[]{};':\"\\|,<.>/?";
    
        return contieneCaracterEspecialRecursivo(password, especiales, 0);
    }


    private boolean contieneCaracterEspecialRecursivo(String password, String especiales, int index) {
        if (index >= password.length()) {
        return false; 
        }
    
        char c = password.charAt(index);
        if (especiales.indexOf(c) != -1) {
        return true; // Encontrado!
        }
    
        return contieneCaracterEspecialRecursivo(password, especiales, index + 1);
    }
}
