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

public class PantallaInicio extends JFrame {
    private JugadorS storage = new JugadorS();
    private Jugador jugadorActivo;

    public PantallaInicio() {
        setTitle("Vampire Wargame");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(25, 25, 30));
        
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(25, 25, 30));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        JLabel lblTitulo = new JLabel("VAMPIRE WARGAME");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(220, 50, 50));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblSubtitulo = new JLabel("🦇 💀 🐺");
        lblSubtitulo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblBienvenida = new JLabel("Bienvenido al juego");
        lblBienvenida.setFont(new Font("Arial", Font.ITALIC, 16));
        lblBienvenida.setForeground(new Color(180, 180, 190));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(lblSubtitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(lblBienvenida);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JButton btnLogin = crearBoton("LOG IN", new Color(70, 80, 100));
        JButton btnCrear = crearBoton("CREAR PLAYER", new Color(80, 100, 70));
        JButton btnSalir = crearBoton("SALIR", new Color(150, 50, 50));
        
        panelPrincipal.add(btnLogin);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnCrear);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnSalir);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        btnLogin.addActionListener(e -> login());
        btnCrear.addActionListener(e -> crearJugador());
        btnSalir.addActionListener(e -> System.exit(0));
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(300, 50));
        btn.setMaximumSize(new Dimension(300, 50));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 70), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return btn;
    }

    private void login() {
    try {
        String user = DialogoOscuro.mostrarInput(this, "Ingresa tu nombre de usuario:", "LOG IN");
        if (user == null) return;
        
        String pass = DialogoOscuro.mostrarInputPassword(this, "Ingresa tu contraseña:", "LOG IN");
        if (pass == null) return;

        if (storage.validarLogin(user, pass)) {
            jugadorActivo = storage.buscarJugador(user);
            DialogoOscuro.mostrarMensaje(this, "¡Bienvenido " + user + "!", "Éxito");
            new PantallaPrincipal(storage, jugadorActivo, this).setVisible(true);
            setVisible(false);
        } else {
            DialogoOscuro.mostrarError(this, "Usuario o contraseña incorrectos", "Error de Login");
        }
    } catch (Exception e) {
        DialogoOscuro.mostrarError(this, "Error crítico durante el Log In: " + e.getMessage(), "Error Crítico");
    }
}

    private void crearJugador() {
    try {
        String user = DialogoOscuro.mostrarInput(this, "Ingresa un nombre de usuario:", "CREAR PLAYER");
        if (user == null) return;
        
        String pass = DialogoOscuro.mostrarInputPassword(this, 
            "Ingresa una contraseña (5 caracteres, con mayúscula, número y símbolo):", 
            "CREAR PLAYER");
        if (pass == null) return;

        if (storage.agregarJugador(user, pass)) {
            jugadorActivo = storage.buscarJugador(user);
            DialogoOscuro.mostrarMensaje(this, "¡Jugador creado correctamente!", "Éxito");
            new PantallaPrincipal(storage, jugadorActivo, this).setVisible(true);
            setVisible(false);
        } else {
            DialogoOscuro.mostrarError(this, 
                "Error al crear usuario.\nPuede que ya exista o la contraseña no cumpla los requisitos.", 
                "Error");
        }
    } catch (Exception e) {
        DialogoOscuro.mostrarError(this, "Error crítico al crear jugador: " + e.getMessage(), "Error Crítico");
    }
}
}
