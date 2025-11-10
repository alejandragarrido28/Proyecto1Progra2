/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import java.awt.Point;

/**
 *
 * @author User
 */
public final class HombreLobo extends PiezaAbstracta { // Requisito 3: Clase Final

    // Stats: Ataque: 5, Vida: 5, Escudo: 5
    public HombreLobo(String color) {
        super("HombreLobo", 5, 5, 5, color);
    }

    // No tiene ataque especial, solo un movimiento especial que se gestiona en la lógica del tablero
    @Override
    public boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero) {
        return false; // No aplica
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        return "Error: Hombre Lobo no tiene ataque especial.";
    }

    // Se puede mover hasta 2 casillas vacías en cualquier dirección.
    // Esta validación se hace a nivel de la pantalla de juego, no aquí.
}
