/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author User
 */
public class PntMiCuenta extends JFrame{
    private Jugador jugador;
    private JugadorS storage;
    private JFrame menu;

    public PntMiCuenta(JugadorS storage, Jugador jugador, JFrame menu) {
        this.storage = storage;
        this.jugador = jugador;
        this.menu = menu;

        setTitle("Mi Cuenta - " + jugador.getUsername());
        setSize(400, 300);
        setLayout(new GridLayout(4, 1, 10, 10));
        setLocationRelativeTo(null);

        JButton btnVer = new JButton("Ver Mi Información");
        JButton btnCambiar = new JButton("Cambiar Password");
        JButton btnEliminar = new JButton("Eliminar Mi Cuenta");
        JButton btnVolver = new JButton("Volver");

        add(btnVer);
        add(btnCambiar);
        add(btnEliminar);
        add(btnVolver);

        btnVer.addActionListener(e -> verInfo());
        btnCambiar.addActionListener(e -> cambiarPassword());
        btnEliminar.addActionListener(e -> eliminarCuenta());
        btnVolver.addActionListener(e -> dispose());
    }

    private void verInfo() {
        JOptionPane.showMessageDialog(this, "Usuario: " + jugador.getUsername() +
                "\nPuntos: " + jugador.getPuntos() +
                "\nFecha ingreso: " + jugador.getFechaIngreso() +
                "\nActivo: " + jugador.isActivo());
    }

    private void cambiarPassword() {
    try {
        String actual = JOptionPane.showInputDialog("Ingrese password actual:");
        
        if (actual == null) return; // Operación cancelada

        if (!jugador.getPassword().equals(actual)) {
            JOptionPane.showMessageDialog(this, "Password incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nuevo = JOptionPane.showInputDialog("Nuevo password (5 chars, con símbolo):");
        
        if (nuevo == null) return; // Operación cancelada

        // Aquí deberías usar la validación completa de JugadorS, no solo la longitud
        if (storage.esPasswordValido(nuevo)) {
            jugador.setPassword(nuevo);
            JOptionPane.showMessageDialog(this, "Password cambiado exitosamente.");
        } else {
             JOptionPane.showMessageDialog(this, "Error: El nuevo password debe ser de 5 caracteres y contener un símbolo.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error crítico al cambiar password: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
    }
}

private void eliminarCuenta() {
    try {
        String confirm = JOptionPane.showInputDialog("Confirme su password para eliminar cuenta:");
        
        if (confirm == null) return; // Operación cancelada

        if (jugador.getPassword().equals(confirm)) {
            storage.eliminarJugador(jugador.getUsername());
            JOptionPane.showMessageDialog(this, "Cuenta eliminada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
            menu.dispose(); // Cierra el Menu Principal
            dispose();      // Cierra Mi Cuenta
            
        } else {
            JOptionPane.showMessageDialog(this, "Password incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error crítico al eliminar cuenta: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }
}

