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
public class Zombie extends PiezaAbstracta {

    // Stats: Ataque: 1 (solo cuando la Muerte ordena), Vida: 1, Escudo: 0
    public Zombie(String color, Point posicion) {
        super("Zombie", 1, 0, 1, color);
        this.posicion = posicion; // Se crea con posición fija
    }

    // Los zombies NO se pueden mover (se maneja en el controlador)
    @Override
    public final boolean puedeMoverAdyacente(Point destino, PiezaAbstracta[][] tablero) {
        return false;
    }

    // Solo atacan si la Muerte lo ordena
    @Override
    public boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero) {
        // Un zombie por sí mismo no tiene ataque especial
        return false;
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        return "Error: El Zombie no inicia ataques especiales.";
    }
    
    // Método para ser llamado por la Muerte (Ataque Zombie)
    public String atacarPorOrden(PiezaAbstracta objetivo) {
        // Daño de 1 punto (su poder de ataque)
        int danio = this.ataque; // 1 punto
        return aplicarDaño(objetivo, danio, false);
    }
}
