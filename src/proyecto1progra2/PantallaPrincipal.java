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
public class PantallaPrincipal extends JFrame {
    private Jugador jugador;
    private JugadorS storage;
    private JFrame inicio;

    public PantallaPrincipal(JugadorS storage, Jugador jugador, JFrame inicio) {
        this.storage = storage;
        this.jugador = jugador;
        this.inicio = inicio;

        setTitle("Vampire Wargame - Menú Principal");
        setSize(550, 700);
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
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(220, 50, 50));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblUsuario = new JLabel("Jugador: " + jugador.getUsername());
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setForeground(new Color(180, 180, 190));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblPuntos = new JLabel("Puntos: " + jugador.getPuntos());
        lblPuntos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPuntos.setForeground(new Color(150, 150, 160));
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblEmojis = new JLabel("🦇 💀 🐺");
        lblEmojis.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        lblEmojis.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(lblEmojis);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));
        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPrincipal.add(lblPuntos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        
        JButton btnJugar = crearBoton("⚔️ JUGAR", new Color(100, 50, 80));
        JButton btnCuenta = crearBoton("👤 MI CUENTA", new Color(70, 80, 100));
        JButton btnReportes = crearBoton("📊 REPORTES", new Color(80, 100, 70));
        JButton btnLogout = crearBoton("🚪 LOG OUT", new Color(150, 50, 50));
        
        panelPrincipal.add(btnJugar);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnCuenta);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnReportes);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnLogout);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        btnJugar.addActionListener(e -> iniciarJuego());
        btnCuenta.addActionListener(e -> new PntMiCuenta(storage, jugador, this).setVisible(true));
        btnReportes.addActionListener(e -> new PntReportes(storage, jugador).setVisible(true));
        btnLogout.addActionListener(e -> {
            DialogoOscuro.mostrarMensaje(this, "Sesión cerrada correctamente.", "Logout");
            inicio.setVisible(true);
            dispose();
        });
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setPreferredSize(new Dimension(350, 60));
        btn.setMaximumSize(new Dimension(350, 60));
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
    
    private void iniciarJuego() {
        Jugador[] jugadoresActivos = storage.obtenerRanking();
        Jugador jugador2 = null;
    
        for (Jugador j : jugadoresActivos) {
            if (!j.getUsername().equalsIgnoreCase(this.jugador.getUsername())) {
                jugador2 = j;
                break;
            }
        }

        if (jugador2 == null) {
            DialogoOscuro.mostrarError(this, "No hay oponentes disponibles en el sistema para empezar la partida.", "Error");
            return;
        }
    
        PantallaJuego juego = new PantallaJuego(storage, this.jugador, jugador2, this);
        juego.setVisible(true);
    }
}

