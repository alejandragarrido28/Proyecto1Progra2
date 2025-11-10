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
public class PntReportes extends JFrame{
    
    public PntReportes(JugadorS storage, Jugador jugador) {
        setTitle("Vampire Wargame - Reportes");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(25, 25, 30));
        
        inicializarComponentes(storage, jugador);
    }
    
    private void inicializarComponentes(JugadorS storage, Jugador jugador) {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(25, 25, 30));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        JLabel lblTitulo = new JLabel("REPORTES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(220, 50, 50));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblUsuario = new JLabel(jugador.getUsername());
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setForeground(new Color(180, 180, 190));
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(lblUsuario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        
        JButton btnRanking = crearBoton(" Ranking de Jugadores", new Color(100, 80, 50));
        JButton btnLogs = crearBoton(" Logs de mis últimos juegos", new Color(70, 80, 100));
        JButton btnVolver = crearBoton("️ Volver", new Color(60, 60, 70));
        
        panelPrincipal.add(btnRanking);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnLogs);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnVolver);
        
        add(panelPrincipal, BorderLayout.CENTER);
        
        btnRanking.addActionListener(e -> mostrarRanking(storage));
        btnLogs.addActionListener(e -> DialogoOscuro.mostrarMensaje(this, "No hay logs disponibles aún.", "Logs"));
        btnVolver.addActionListener(e -> dispose());
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

    private void mostrarRanking(JugadorS storage) {
        Jugador[] ranking = storage.obtenerRanking();
        StringBuilder sb = new StringBuilder(" RANKING DE JUGADORES \n\n");
        sb.append("Pos | Usuario | Puntos\n");
        int pos = 1;
        for (Jugador p : ranking) {
            String medalla = "";
            if (pos == 1) medalla = " ";
            else if (pos == 2) medalla = "";
            else if (pos == 3) medalla = "";
            else medalla = pos + "";
            
            sb.append(medalla).append(p.getUsername())
              .append(" - ").append(p.getPuntos()).append(" pts\n");
            pos++;
        }
        DialogoOscuro.mostrarMensaje(this, sb.toString(), "Ranking");
    }
}

