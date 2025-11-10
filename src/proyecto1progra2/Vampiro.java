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
public class Vampiro extends PiezaAbstracta {

    // Stats: Ataque: 3, Vida: 4, Escudo: 5
    public Vampiro(String color) {
        super("Vampiro", 4, 5, 3, color);
    }

    @Override
    public boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero) {
        // Chupar sangre: solo a casilla adyacente
        int dx = Math.abs(posicion.x - objetivo.x);
        int dy = Math.abs(posicion.y - objetivo.y);
        
        // Verifica si es adyacente (movimiento de 1 casilla en cualquier dirección)
        return (dx <= 1 && dy <= 1) && (dx + dy >= 1);
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        // Chupar sangre: Quita 1 punto, restaura 1 punto de vida propia
        int danioEspecial = 1;
        
        // Aplica el daño al objetivo
        String resultado = aplicarDaño(objetivo, danioEspecial, false);
        
        // Restaura 1 punto de vida propia
        this.vida = Math.min(this.vida + 1, 4); // La vida máxima del Vampiro es 4
        
        return "¡Chupando Sangre! Vida restaurada en 1 punto. " + resultado;
    }
    
    // Opcional: El hombre lobo se mueve 2 casillas si quiere. Esto se maneja en la lógica del juego.
    // Aquí no se añade un método extra porque la lógica de movimiento la maneja el controlador.
}
