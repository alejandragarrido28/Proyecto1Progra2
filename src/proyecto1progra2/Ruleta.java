package proyecto1progra2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ruleta extends JDialog {
    private JLabel lblImagen;
    private JButton btnStop;
    private Timer timer;
    private List<ImageIcon> imagenes;
    private List<String> nombresPiezas;
    private int indiceActual;
    private String resultadoSeleccionado;
    private boolean detenida;
    
    public Ruleta(JFrame parent) {
        super(parent, "RULETA - Presiona STOP", true);
        setSize(400, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 35));
        
        inicializarImagenes();
        inicializarComponentes();
        
        indiceActual = 0;
        detenida = false;
        resultadoSeleccionado = null;
    }
    
    private void inicializarImagenes() {
        imagenes = new ArrayList<>();
        nombresPiezas = new ArrayList<>();
        
        String[] tipos = {"Vampiro", "HombreLobo", "Muerte", "Vampiro", "HombreLobo", "Muerte"};
        String[] rutas = {
            "/proyecto1progra2/Imagenes/Vampiro.jpg",
            "/proyecto1progra2/Imagenes/Hombre_Lobo.png",
            "/proyecto1progra2/Imagenes/Muerte.jpg",
            "/proyecto1progra2/Imagenes/Vampiro.jpg",
            "/proyecto1progra2/Imagenes/Hombre_Lobo.png",
            "/proyecto1progra2/Imagenes/Muerte.jpg"
        };
        
        for (int i = 0; i < tipos.length; i++) {
            try {
                ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutas[i]));
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                imagenes.add(new ImageIcon(imagenEscalada));
                nombresPiezas.add(tipos[i]);
            } catch (Exception e) {
                System.err.println("Error al cargar imagen: " + rutas[i]);
            }
        }
    }
    
    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(30, 30, 35));
        JLabel lblTitulo = new JLabel("¡GIRANDO LA RULETA!");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(255, 215, 0));
        panelSuperior.add(lblTitulo);
        add(panelSuperior, BorderLayout.NORTH);
        
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(new Color(40, 40, 45));
        panelCentro.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 3));
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(JLabel.CENTER);
        lblImagen.setVerticalAlignment(JLabel.CENTER);
        if (!imagenes.isEmpty()) {
            lblImagen.setIcon(imagenes.get(0));
        }
        panelCentro.add(lblImagen);
        add(panelCentro, BorderLayout.CENTER);
        
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(30, 30, 35));
        btnStop = new JButton("STOP");
        btnStop.setFont(new Font("Arial", Font.BOLD, 24));
        btnStop.setBackground(new Color(200, 50, 50));
        btnStop.setForeground(Color.WHITE);
        btnStop.setFocusPainted(false);
        btnStop.setPreferredSize(new Dimension(200, 60));
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                detenerRuleta();
            }
        });
        panelInferior.add(btnStop);
        add(panelInferior, BorderLayout.SOUTH);
        
        timer = new Timer(150, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cambiarImagen();
            }
        });
    }
    
    private void cambiarImagen() {
        if (imagenes.isEmpty()) return;
        
        indiceActual = (indiceActual + 1) % imagenes.size();
        lblImagen.setIcon(imagenes.get(indiceActual));
    }
    
    private void detenerRuleta() {
        if (detenida) return;
        
        detenida = true;
        timer.stop();
        
        if (!nombresPiezas.isEmpty()) {
            resultadoSeleccionado = nombresPiezas.get(indiceActual);
        }
        
        btnStop.setText("SELECCIONADO!");
        btnStop.setBackground(new Color(50, 150, 50));
        btnStop.setEnabled(false);
        
        Timer timerCierre = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        timerCierre.setRepeats(false);
        timerCierre.start();
    }
    
    public String girarYObtenerResultado() {
        timer.start();
        setVisible(true);
        return resultadoSeleccionado;
    }
}

