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
public abstract class PiezaAbstracta {
    protected String nombre;
    protected int vida; // Corazón
    protected int escudo; // Escudo
    protected int ataque; // Espada
    protected String color; // "Blanco" o "Negro"
    protected Point posicion;

    public PiezaAbstracta(String nombre, int vida, int escudo, int ataque, String color) {
        this.nombre = nombre;
        this.vida = vida;
        this.escudo = escudo;
        this.ataque = ataque;
        this.color = color;
    }
    
    public String getColor() { 
    return color; 
    }

    public boolean puedeMoverAdyacente(Point destino, PiezaAbstracta[][] tablero) {
        int dx = Math.abs(posicion.x - destino.x);
        int dy = Math.abs(posicion.y - destino.y);
        
        if ((dx <= 1 && dy <= 1) && (dx + dy >= 1) && tablero[destino.x][destino.y] == null) {
            return true;
        }
        return false;
    }


    public abstract boolean esAtaqueEspecialValido(Point objetivo, PiezaAbstracta[][] tablero);
    
    public abstract String realizarAtaqueEspecial(PiezaAbstracta objetivo);
    

    public String realizarAtaqueNormal(PiezaAbstracta objetivo) {
        int danio = this.ataque;
        return aplicarDaño(objetivo, danio, false);
    }

    protected String aplicarDaño(PiezaAbstracta objetivo, int danio, boolean ignoraEscudo) {
        String mensaje = "";
        
        if (!ignoraEscudo && objetivo.escudo > 0) {
            int danioEscudo = Math.min(danio, objetivo.escudo);
            objetivo.escudo -= danioEscudo;
            danio -= danioEscudo;
            mensaje += "Se quitó " + danioEscudo + " de escudo. ";
        }
        
        if (danio > 0) {
            objetivo.vida -= danio;
            mensaje += "Se quitó " + danio + " de vida.";
        }
        
        if (objetivo.vida <= 0) {
            objetivo.vida = 0;
            return "¡SE DESTRUYÓ LA PIEZA " + objetivo.getNombre() + " del jugador " + objetivo.getColor() + "!";
        } else {
            return "Ataque exitoso: " + mensaje + " Vida restante: " + objetivo.getVida() + ", Escudo restante: " + objetivo.getEscudo();
        }
    }
    
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getEscudo() { return escudo; }
    public int getAtaque() { return ataque; }
    public Point getPosicion() { return posicion; }
    public void setPosicion(Point posicion) { this.posicion = posicion; }
    public void setVida(int vida) { this.vida = vida; } // Necesario para la habilidad del Vampiro
    public void setEscudo(int escudo) { this.escudo = escudo; }
    
    @Override
    public String toString() {
        return nombre + " (" + color + ") - Vida: " + vida + ", Escudo: " + escudo + ", Ataque: " + ataque;
    }
}
