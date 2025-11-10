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
public class Muerte extends PiezaAbstracta {

    // Stats: Ataque: 4, Vida: 3, Escudo: 2
    public Muerte(String color) {
        super("Muerte", 3, 2, 4, color);
    }

    @Override
    public boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero) {
        // Lanzar Lanza: a 2 casillas delante de él en cualquier dirección (sin obstrucción)
        int dx = Math.abs(posicion.x - objetivo.x);
        int dy = Math.abs(posicion.y - objetivo.y);
        
        // Verifica si es a 2 casillas de distancia
        if ((dx == 2 && dy == 0) || (dx == 0 && dy == 2) || (dx == 2 && dy == 2)) {
            // Verificar que no haya obstrucción en la casilla intermedia
            // Nota: La verificación de obstrucción se manejaría mejor en el controlador
            return true;
        }
        
        return false;
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        // Lanzar Lanza: Ignora escudo y quita la mitad del poder (2 puntos)
        int danioEspecial = this.ataque / 2; // 2 puntos
        return aplicarDaño(objetivo, danioEspecial, true); // true indica que IGNORA EL ESCUDO
    }
    
    // El ataque a través del Zombie es una lógica compleja que requiere la posición de un Zombie aliado, 
    // por lo que se gestionará en el controlador del juego.
}
