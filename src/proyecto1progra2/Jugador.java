/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class Jugador {
    private String username;
    private String password;
    private int puntos;
    private LocalDate fechaIngreso;
    private boolean activo;
    protected String color;

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
        this.fechaIngreso = LocalDate.now();
        this.activo = true;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getPuntos() { return puntos; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public boolean isActivo() { return activo; }

    public void setPassword(String password) { this.password = password; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public final void addPuntos(int p) { this.puntos += p; }

    public String getColor() {
return color;
}
    @Override
    public String toString() {
        return username + " - Puntos: " + puntos + " - Activo: " + activo;
    }
}

