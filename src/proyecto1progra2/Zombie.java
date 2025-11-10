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
        this.posicion = posicion; 
    }

    @Override
    public final boolean puedeMoverAdyacente(Point destino, PiezaAbstracta[][] tablero) {
        return false;
    }

    @Override
    public boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero) {
        return false;
    }

    @Override
    public String realizarAtaqueEspecial(PiezaAbstracta objetivo) {
        return "Error: El Zombie no inicia ataques especiales.";
    }
    
    public String atacarPorOrden(PiezaAbstracta objetivo) {
        int danio = this.ataque;
        return aplicarDaño(objetivo, danio, false);
    }
}
