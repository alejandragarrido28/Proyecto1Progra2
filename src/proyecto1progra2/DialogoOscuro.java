package proyecto1progra2;
import javax.swing.*;
import java.awt.*;

public class DialogoOscuro {
    private static final Color BG_OSCURO = new Color(35, 35, 40);
    private static final Color BG_INPUT = new Color(45, 45, 50);
    private static final Color TEXTO_CLARO = new Color(220, 220, 230);
    private static final Color BORDE = new Color(60, 60, 70);
    
    public static String mostrarInput(JFrame parent, String mensaje, String titulo) {
        JDialog dialog = new JDialog(parent, titulo, true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(BG_OSCURO);
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(parent);
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(BG_OSCURO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setForeground(TEXTO_CLARO);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelContenido.add(lblMensaje, BorderLayout.NORTH);
        
        JTextField txtInput = new JTextField();
        txtInput.setBackground(BG_INPUT);
        txtInput.setForeground(TEXTO_CLARO);
        txtInput.setCaretColor(TEXTO_CLARO);
        txtInput.setFont(new Font("Arial", Font.PLAIN, 14));
        txtInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panelContenido.add(txtInput, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(BG_OSCURO);
        
        JButton btnOk = crearBoton("Aceptar", new Color(70, 100, 70));
        JButton btnCancelar = crearBoton("Cancelar", new Color(100, 50, 50));
        
        final String[] resultado = {null};
        
        btnOk.addActionListener(e -> {
            resultado[0] = txtInput.getText().trim();
            dialog.dispose();
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        txtInput.addActionListener(e -> {
            resultado[0] = txtInput.getText().trim();
            dialog.dispose();
        });
        
        panelBotones.add(btnOk);
        panelBotones.add(btnCancelar);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
        
        dialog.add(panelContenido);
        dialog.setVisible(true);
        
        return (resultado[0] != null && !resultado[0].isEmpty()) ? resultado[0] : null;
    }
    
    public static String mostrarInputPassword(JFrame parent, String mensaje, String titulo) {
        JDialog dialog = new JDialog(parent, titulo, true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(BG_OSCURO);
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(parent);
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(BG_OSCURO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblMensaje = new JLabel("<html>" + mensaje.replace("\n", "<br>") + "</html>");
        lblMensaje.setForeground(TEXTO_CLARO);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        panelContenido.add(lblMensaje, BorderLayout.NORTH);
        
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBackground(BG_INPUT);
        txtPassword.setForeground(TEXTO_CLARO);
        txtPassword.setCaretColor(TEXTO_CLARO);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDE, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panelContenido.add(txtPassword, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotones.setBackground(BG_OSCURO);
        
        JButton btnOk = crearBoton("Aceptar", new Color(70, 100, 70));
        JButton btnCancelar = crearBoton("Cancelar", new Color(100, 50, 50));
        
        final String[] resultado = {null};
        
        btnOk.addActionListener(e -> {
            resultado[0] = new String(txtPassword.getPassword());
            dialog.dispose();
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        txtPassword.addActionListener(e -> {
            resultado[0] = new String(txtPassword.getPassword());
            dialog.dispose();
        });
        
        panelBotones.add(btnOk);
        panelBotones.add(btnCancelar);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
        
        dialog.add(panelContenido);
        dialog.setVisible(true);
        
        return (resultado[0] != null && !resultado[0].isEmpty()) ? resultado[0] : null;
    }
    
    public static void mostrarMensaje(JFrame parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, new Color(70, 100, 70));
    }
    
    public static void mostrarError(JFrame parent, String mensaje, String titulo) {
        mostrarDialogo(parent, mensaje, titulo, new Color(150, 50, 50));
    }
    
    public static boolean mostrarConfirmacion(JFrame parent, String mensaje, String titulo) {
        JDialog dialog = new JDialog(parent, titulo, true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(BG_OSCURO);
        dialog.setSize(450, 200);
        dialog.setLocationRelativeTo(parent);
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(BG_OSCURO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblMensaje = new JLabel("<html><div style='text-align: center;'>" + 
            mensaje.replace("\n", "<br>") + "</div></html>");
        lblMensaje.setForeground(TEXTO_CLARO);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(lblMensaje, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setBackground(BG_OSCURO);
        
        JButton btnSi = crearBoton("Sí", new Color(70, 100, 70));
        JButton btnNo = crearBoton("No", new Color(100, 50, 50));
        
        final boolean[] resultado = {false};
        
        btnSi.addActionListener(e -> {
            resultado[0] = true;
            dialog.dispose();
        });
        
        btnNo.addActionListener(e -> dialog.dispose());
        
        panelBotones.add(btnSi);
        panelBotones.add(btnNo);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
        
        dialog.add(panelContenido);
        dialog.setVisible(true);
        
        return resultado[0];
    }
    
    public static int mostrarOpcionAtaque(JFrame parent, String nombreAtacante, int ataqueAtacante,
                                          String nombreObjetivo, int vidaObjetivo, int escudoObjetivo) {
        JDialog dialog = new JDialog(parent, "Seleccionar Ataque", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(BG_OSCURO);
        dialog.setSize(450, 280);
        dialog.setLocationRelativeTo(parent);
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(BG_OSCURO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitulo = new JLabel("¿Qué tipo de ataque deseas realizar?");
        lblTitulo.setForeground(TEXTO_CLARO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel panelInfo = new JPanel(new GridLayout(2, 1, 5, 5));
        panelInfo.setBackground(BG_OSCURO);
        
        JLabel lblAtacante = new JLabel("️ Atacante: " + nombreAtacante + " (Ataque: " + ataqueAtacante + ")");
        lblAtacante.setForeground(new Color(100, 200, 100));
        lblAtacante.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblObjetivo = new JLabel("? Objetivo: " + nombreObjetivo + " (Vida: " + vidaObjetivo + ", Escudo: " + escudoObjetivo + ")");
        lblObjetivo.setForeground(new Color(200, 100, 100));
        lblObjetivo.setFont(new Font("Arial", Font.PLAIN, 14));
        
        panelInfo.add(lblAtacante);
        panelInfo.add(lblObjetivo);
        
        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setBackground(BG_OSCURO);
        panelCentro.add(lblTitulo, BorderLayout.NORTH);
        panelCentro.add(panelInfo, BorderLayout.CENTER);
        
        panelContenido.add(panelCentro, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        panelBotones.setBackground(BG_OSCURO);
        
        JButton btnNormal = new JButton("️ Ataque Normal");
        btnNormal.setFont(new Font("Arial", Font.BOLD, 14));
        btnNormal.setPreferredSize(new Dimension(160, 40));
        btnNormal.setBackground(new Color(70, 80, 100));
        btnNormal.setForeground(Color.WHITE);
        btnNormal.setFocusPainted(false);
        btnNormal.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        
        JButton btnEspecial = new JButton(" Ataque Especial");
        btnEspecial.setFont(new Font("Arial", Font.BOLD, 14));
        btnEspecial.setPreferredSize(new Dimension(160, 40));
        btnEspecial.setBackground(new Color(120, 60, 100));
        btnEspecial.setForeground(Color.WHITE);
        btnEspecial.setFocusPainted(false);
        btnEspecial.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        
        JButton btnCancelar = crearBoton("Cancelar", new Color(100, 50, 50));
        
        final int[] resultado = {-1};
        
        btnNormal.addActionListener(e -> {
            resultado[0] = 0;
            dialog.dispose();
        });
        
        btnEspecial.addActionListener(e -> {
            resultado[0] = 1;
            dialog.dispose();
        });
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        
        panelBotones.add(btnNormal);
        panelBotones.add(btnEspecial);
        panelBotones.add(btnCancelar);
        panelContenido.add(panelBotones, BorderLayout.SOUTH);
        
        dialog.add(panelContenido);
        dialog.setVisible(true);
        
        return resultado[0];
    }
    
    private static void mostrarDialogo(JFrame parent, String mensaje, String titulo, Color colorBoton) {
        JDialog dialog = new JDialog(parent, titulo, true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(BG_OSCURO);
        dialog.setSize(400, 180);
        dialog.setLocationRelativeTo(parent);
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(BG_OSCURO);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblMensaje = new JLabel("<html><div style='text-align: center;'>" + 
            mensaje.replace("\n", "<br>") + "</div></html>");
        lblMensaje.setForeground(TEXTO_CLARO);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(lblMensaje, BorderLayout.CENTER);
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(BG_OSCURO);
        
        JButton btnOk = crearBoton("Aceptar", colorBoton);
        btnOk.addActionListener(e -> dialog.dispose());
        panelBoton.add(btnOk);
        
        panelContenido.add(panelBoton, BorderLayout.SOUTH);
        dialog.add(panelContenido);
        dialog.setVisible(true);
    }
    
    private static JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(100, 35));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(BORDE, 1));
        return btn;
    }
}

