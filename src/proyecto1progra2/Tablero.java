/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 *
 * @author User
 */
public class Tablero {
    private PiezaAbstracta[][] tablero; // Matriz 6x6
    private Jugador jugador1; // Blancas
    private Jugador jugador2; // Negras
    private Jugador jugadorActivo;
    private Random random;

    public Tablero(Jugador j1, Jugador j2) {
        this.tablero = new PiezaAbstracta[6][6];
        this.jugador1 = j1;
        this.jugador2 = j2;
        j1.color = "Blanco";
        j2.color = "Negro";
        this.jugadorActivo = j1;
        this.random = new Random();
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Orden: HOMBRE LOBO - VAMPIRO – MUERTE – MUERTE – VAMPIRO – HOMBRE LOBO

        tablero[0][0] = new HombreLobo("Blanco");
        tablero[0][1] = new Vampiro("Blanco");
        tablero[0][2] = new Muerte("Blanco");
        tablero[0][3] = new Muerte("Blanco");
        tablero[0][4] = new Vampiro("Blanco");
        tablero[0][5] = new HombreLobo("Blanco");
        
        for(int j=0; j<6; j++) {
            if (tablero[0][j] != null) tablero[0][j].setPosicion(new Point(0, j));
        }

        tablero[5][0] = new HombreLobo("Negro");
        tablero[5][1] = new Vampiro("Negro");
        tablero[5][2] = new Muerte("Negro");
        tablero[5][3] = new Muerte("Negro");
        tablero[5][4] = new Vampiro("Negro");
        tablero[5][5] = new HombreLobo("Negro");

        for(int j=0; j<6; j++) {
            if (tablero[5][j] != null) tablero[5][j].setPosicion(new Point(5, j));
        }
    }
    
    public PiezaAbstracta simularRuleta() {
        String[] tiposPieza = {"Vampiro", "HombreLobo", "Muerte", "Vampiro", "HombreLobo", "Muerte"};
        String tipoSeleccionado = tiposPieza[random.nextInt(tiposPieza.length)];
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                PiezaAbstracta p = tablero[i][j];
                if (p != null && p.getColor().equals(jugadorActivo.getColor()) && p.getNombre().equals(tipoSeleccionado)) {
                    return p;
                }
            }
        }
        return null; 
    }
    
    public int contarPiezasPerdidas() {
        int piezasActuales = 0;
        String colorActivo = jugadorActivo.getColor();
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                PiezaAbstracta p = tablero[i][j];
                // Cuenta solo piezas principales
                if (p != null && p.getColor().equals(colorActivo) && !p.getNombre().equals("Zombie")) {
                    piezasActuales++;
                }
            }
        }
        return 6 - piezasActuales;
    }
    
    public void moverPieza(Point origen, Point destino) {
        PiezaAbstracta pieza = tablero[origen.x][origen.y];
        
        tablero[destino.x][destino.y] = pieza;
        tablero[origen.x][origen.y] = null;
        pieza.setPosicion(destino);
    }
    
    public PiezaAbstracta conjurarZombie(Point destino) {
        PiezaAbstracta zombie = new Zombie(jugadorActivo.getColor(), destino);
        tablero[destino.x][destino.y] = zombie;
        return zombie;
    }

    public void eliminarPieza(PiezaAbstracta p) {
        if (p != null) {
            tablero[p.getPosicion().x][p.getPosicion().y] = null;
        }
    }
    
    public Jugador verificarGanador() {
        int piezasJ1 = 0;
        int piezasJ2 = 0;
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                PiezaAbstracta p = tablero[i][j];
                if (p != null) {
                    if (p.getColor().equals(jugador1.getColor())) {
                        piezasJ1++;
                    } else {
                        piezasJ2++;
                    }
                }
            }
        }
        
        if (piezasJ1 == 0) return jugador2;
        if (piezasJ2 == 0) return jugador1;
        return null;
    }
    
    public void cambiarTurno() {
        jugadorActivo = (jugadorActivo == jugador1) ? jugador2 : jugador1;
    }

    public PiezaAbstracta[][] getTablero() { return tablero; }
    public Jugador getJugadorActivo() { return jugadorActivo; }
    public Jugador getJugadorRival() { return (jugadorActivo == jugador1) ? jugador2 : jugador1; }
}
