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
        int dx = Math.abs(posicion.x - objetivo.x);
        int dy = Math.abs(posicion.y - objetivo.y);
        
        return (dx <= 1 && dy <= 1) && (dx + dy >= 1);
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        int danioEspecial = 1;
        
        String resultado = aplicarDaño(objetivo, danioEspecial, false);
        
        this.vida = Math.min(this.vida + 1, 4); // La vida máxima del Vampiro es 4
        
        return "¡Chupando Sangre! Vida restaurada en 1 punto. " + resultado;
    }
    
}
