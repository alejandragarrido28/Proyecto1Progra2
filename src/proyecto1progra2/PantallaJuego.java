/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1progra2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author User
 */
public class PantallaJuego extends JFrame{
    private JugadorS storage;
    private Tablero tableroJuego;
    private PiezaAbstracta piezaSeleccionadaRuleta;
    private PiezaAbstracta piezaOrigen; // Pieza seleccionada en el tablero

    private JButton[][] botonesTablero; // Para representar las casillas
    private JLabel lblTurno;
    private JButton btnRuleta;
    private JButton btnRetirar;
    private JTextArea txtLog;
    private JScrollPane scrollLog;
    
    public PantallaJuego(JugadorS storage, Jugador j1, Jugador j2, JFrame menuPrincipal) {
        this.storage = storage;
        this.tableroJuego = new Tablero(j1, j2);
        
        setTitle("Vampire Wargame - Turno de " + tableroJuego.getJugadorActivo().getUsername());
        setSize(700, 800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        
        actualizarGUI(); 
    }

    private void inicializarComponentes() {
        getContentPane().setBackground(new Color(30, 30, 35));
        
        JPanel panelTablero = new JPanel(new GridLayout(6, 6, 2, 2));
        panelTablero.setBackground(new Color(20, 20, 25));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        botonesTablero = new JButton[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(90, 90));
                btn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
                btn.setFocusPainted(false);
                btn.setBorderPainted(true);
                btn.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70), 1));
                if ((i + j) % 2 == 0) {
                    btn.setBackground(new Color(45, 52, 64));
                } else {
                    btn.setBackground(new Color(35, 42, 54));
                }
                final int fila = i;
                final int col = j;
                btn.addActionListener(e -> manejarClickCasilla(fila, col));
                botonesTablero[i][j] = btn;
                panelTablero.add(btn);
            }
        }
        add(panelTablero, BorderLayout.CENTER);

        JPanel panelControl = new JPanel(new FlowLayout());
        panelControl.setBackground(new Color(25, 25, 30));
        lblTurno = new JLabel("Turno: " + tableroJuego.getJugadorActivo().getUsername() + " (" + tableroJuego.getJugadorActivo().getColor() + ")");
        lblTurno.setForeground(new Color(220, 220, 230));
        lblTurno.setFont(new Font("Arial", Font.BOLD, 14));
        btnRuleta = new JButton("Girar Ruleta");
        btnRuleta.setBackground(new Color(70, 80, 100));
        btnRuleta.setForeground(Color.WHITE);
        btnRuleta.setFocusPainted(false);
        btnRetirar = new JButton("RETIRARSE");
        btnRetirar.setBackground(new Color(150, 50, 50));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setFocusPainted(false);
        
        btnRuleta.addActionListener(this::manejarRuleta);
        btnRetirar.addActionListener(this::manejarRetiro);

        panelControl.add(lblTurno);
        panelControl.add(btnRuleta);
        panelControl.add(btnRetirar);
        add(panelControl, BorderLayout.NORTH);

        txtLog = new JTextArea(5, 50);
        txtLog.setEditable(false);
        txtLog.setBackground(new Color(25, 25, 30));
        txtLog.setForeground(new Color(200, 200, 210));
        txtLog.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtLog.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollLog = new JScrollPane(txtLog);
        scrollLog.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70)));
        add(scrollLog, BorderLayout.SOUTH);
    }
    
    private void actualizarGUI() {
        PiezaAbstracta[][] tablero = tableroJuego.getTablero();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                PiezaAbstracta p = tablero[i][j];
                JButton btn = botonesTablero[i][j];
                if (p != null) {
                    ImageIcon icono = obtenerIconoPieza(p);
                    if (icono != null) {
                        btn.setIcon(icono);
                        btn.setText("");
                    } else {
                        btn.setText(p.getNombre().substring(0, 1));
                        btn.setIcon(null);
                    }
                    btn.setToolTipText(p.toString());
                } else {
                    btn.setIcon(null);
                    btn.setText("");
                    btn.setToolTipText("");
                }
                if ((i + j) % 2 == 0) {
                    btn.setBackground(new Color(45, 52, 64));
                } else {
                    btn.setBackground(new Color(35, 42, 54));
                }
                btn.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 70), 1));
            }
        }
        lblTurno.setText("Turno: " + tableroJuego.getJugadorActivo().getUsername() + " (" + tableroJuego.getJugadorActivo().getColor() + ")");
    }
    
    private ImageIcon obtenerIconoPieza(PiezaAbstracta pieza) {
        String nombre = pieza.getNombre();
        String color = pieza.getColor();
        String rutaImagen = "";
        
        switch (nombre) {
            case "Vampiro":
                rutaImagen = "/proyecto1progra2/Imagenes/Vampiro.png";
                break;
            case "HombreLobo":
                rutaImagen = "/proyecto1progra2/Imagenes/Hombre_Lobo.png";
                break;
            case "Muerte":
                rutaImagen = "/proyecto1progra2/Imagenes/Muerte.png";
                break;
            case "Zombie":
                rutaImagen = "/proyecto1progra2/Imagenes/Zombie.png";
                break;
            default:
                return null;
        }
        
        try {
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            
            if (color.equals("Negro")) {
                Image imagenOscurecida = oscurecerImagen(imagenEscalada);
                return new ImageIcon(imagenOscurecida);
            }
            
            return iconoEscalado;
        } catch (Exception e) {
            System.err.println("Error al cargar imagen: " + rutaImagen + " - " + e.getMessage());
            return null;
        }
    }
    
    private Image oscurecerImagen(Image img) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        
        java.awt.image.BufferedImage buffered = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffered.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        
        return buffered;
    }
    
    private void logMensaje(String mensaje) {
        txtLog.append(mensaje + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    private void manejarRuleta(java.awt.event.ActionEvent e) {
        if (piezaSeleccionadaRuleta != null) {
            logMensaje("Error: ¡Ya se ha girado la ruleta para este turno!");
            return;
        }

        int perdidas = tableroJuego.contarPiezasPerdidas();
        int intentos = 1;
        if (perdidas >= 2 && perdidas < 4) intentos = 2;
        if (perdidas >= 4) intentos = 3;

        logMensaje("Piezas perdidas: " + perdidas + ". Intentos de ruleta: " + intentos);

        for (int i = 0; i < intentos; i++) {
            Ruleta ruleta = new Ruleta(this);
            String tipoSeleccionado = ruleta.girarYObtenerResultado();
            
            if (tipoSeleccionado == null) {
                logMensaje("Ruleta cancelada.");
                return;
            }

            piezaSeleccionadaRuleta = buscarPiezaActiva(tipoSeleccionado);
            
            if (piezaSeleccionadaRuleta != null) {
                logMensaje("¡Ruleta! Pieza seleccionada: " + piezaSeleccionadaRuleta.getNombre());
                logMensaje("Ahora haz clic en una pieza " + piezaSeleccionadaRuleta.getNombre() + " para moverla.");
                btnRuleta.setEnabled(false);
                resaltarPiezasMovibles();
                return;
            } else {
                logMensaje("La ruleta seleccionó '" + tipoSeleccionado + "', pero no tienes piezas de ese tipo. Intentando de nuevo...");
            }
        }

        logMensaje("No se pudo seleccionar una pieza válida. ¡PIERDE EL TURNO!");
        finalizarTurno();
    }

    private PiezaAbstracta buscarPiezaActiva(String nombrePieza) {
    String colorActivo = tableroJuego.getJugadorActivo().getColor();
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            PiezaAbstracta p = tableroJuego.getTablero()[i][j];
            if (p != null && p.getColor().equals(colorActivo) && p.getNombre().equals(nombrePieza)) {
                return p;
            }
        }
    }
    return null;
}

private void resaltarPiezasMovibles() {
    if (piezaSeleccionadaRuleta == null) return;
    
    String nombreBuscado = piezaSeleccionadaRuleta.getNombre();
    String colorActivo = tableroJuego.getJugadorActivo().getColor();
    
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            PiezaAbstracta p = tableroJuego.getTablero()[i][j];
            if (p != null && p.getColor().equals(colorActivo) && p.getNombre().equals(nombreBuscado)) {
                botonesTablero[i][j].setBorder(BorderFactory.createLineBorder(new Color(100, 200, 100), 3));
            }
        }
    }
}
    
    

    private void manejarClickCasilla(int fila, int col) {
        try {
        Point click = new Point(fila, col);
        PiezaAbstracta piezaEnClick = tableroJuego.getTablero()[fila][col];
        
        if (piezaSeleccionadaRuleta == null) {
            logMensaje("Primero debes girar la ruleta.");
            return;
        }

        if (piezaOrigen == null) {
            if (piezaEnClick == null) {
                logMensaje("Error: Casilla vacía. Selecciona una pieza propia.");
                return;
            }
            if (!piezaEnClick.getColor().equals(tableroJuego.getJugadorActivo().getColor())) {
                logMensaje("Error: Selecciona una pieza de tu color.");
                return;
            }
            if (!piezaEnClick.getNombre().equals(piezaSeleccionadaRuleta.getNombre())) {
                 logMensaje("Error: Debes mover la pieza seleccionada por la ruleta (" + piezaSeleccionadaRuleta.getNombre() + ").");
                 return;
            }
            
            piezaOrigen = piezaEnClick;
            botonesTablero[fila][col].setBackground(new Color(100, 150, 200));
            logMensaje("Pieza seleccionada: " + piezaOrigen.getNombre() + ". Selecciona la casilla de destino.");
            
        } else {
            if (click.equals(piezaOrigen.getPosicion())) {
                piezaOrigen = null;
                actualizarGUI();
                return;
            }
            
            if (piezaEnClick == null) {
                if (piezaOrigen.getNombre().equals("Zombie")) {
                    logMensaje("Error: Los Zombies no se pueden mover.");
                    return;
                }
                
                if (piezaOrigen.getNombre().equals("Muerte")) {
                    boolean opcionMuerte = DialogoOscuro.mostrarConfirmacion(this,
                        "¿Qué deseas hacer?\n\nSÍ = Mover la Muerte\nNO = Conjurar Zombie en esta casilla",
                        "Acción de la Muerte");
                    
                    if (opcionMuerte) {
                        if (esMovimientoValido(piezaOrigen, click)) {
                            tableroJuego.moverPieza(piezaOrigen.getPosicion(), click);
                            logMensaje("¡Movimiento de Muerte exitoso!");
                        } else {
                            logMensaje("Movimiento inválido. Solo puedes mover a casillas adyacentes vacías.");
                            return;
                        }
                    } else {
                        tableroJuego.conjurarZombie(click);
                        logMensaje("¡Zombie conjurado en posición [" + click.x + "," + click.y + "]!");
                    }
                } else {
                    if (esMovimientoValido(piezaOrigen, click)) {
                        tableroJuego.moverPieza(piezaOrigen.getPosicion(), click);
                        logMensaje("¡Movimiento de " + piezaOrigen.getNombre() + " exitoso!");
                    } else {
                        if (piezaOrigen.getNombre().equals("HombreLobo")) {
                            logMensaje("Movimiento inválido. El Hombre Lobo puede moverse hasta 2 casillas vacías en cualquier dirección.");
                        } else {
                            logMensaje("Movimiento inválido. Solo puedes mover a casillas adyacentes vacías.");
                        }
                        return;
                    }
                }
                
            } else if (piezaEnClick.getColor().equals(tableroJuego.getJugadorActivo().getColor())) {
                logMensaje("Error: No puedes atacar a una pieza propia.");
                return;
            } else {
                boolean esAdyacente = esMovimientoAdyacenteValido(piezaOrigen.getPosicion(), click);
                
                if (piezaOrigen.getNombre().equals("Muerte")) {
                    boolean ataqueExitoso = manejarAtaqueMuerte(piezaOrigen, piezaEnClick, click, esAdyacente);
                    if (!ataqueExitoso) {
                        return;
                    }
                } else {
                    if (!esAdyacente) {
                        logMensaje("Error: Solo puedes atacar piezas adyacentes.");
                        return;
                    }
                    
                    int opcion = DialogoOscuro.mostrarOpcionAtaque(this,
                        piezaOrigen.getNombre(), piezaOrigen.getAtaque(),
                        piezaEnClick.getNombre(), piezaEnClick.getVida(), piezaEnClick.getEscudo());
                    
                    if (opcion == 0) {
                        String resultado = piezaOrigen.realizarAtaqueNormal(piezaEnClick);
                        logMensaje(resultado);
                        if (resultado.contains("DESTRUYÓ")) {
                            tableroJuego.eliminarPieza(piezaEnClick);
                        }
                    } else if (opcion == 1) {
                        if (piezaOrigen.esAtaqueEspecialValido(click, tableroJuego.getTablero())) {
                            String resultado = piezaOrigen.realizarAtaqueEspecial(piezaEnClick);
                            logMensaje(resultado);
                            if (resultado.contains("DESTRUYÓ")) {
                                tableroJuego.eliminarPieza(piezaEnClick);
                            }
                        } else {
                            logMensaje("Ataque especial no válido para esa posición/pieza.");
                            return;
                        }
                    } else {
                        logMensaje("Ataque cancelado.");
                        return;
                    }
                }
            }
            
            finalizarTurno();
            
        }
        } catch (Exception ex) {
        logMensaje("Error crítico durante la jugada (" + ex.getClass().getSimpleName() + "): " + ex.getMessage());
        DialogoOscuro.mostrarError(this, "Ocurrió un error inesperado. Inténtalo de nuevo.", "Error de Jugada");
        piezaOrigen = null; 
        actualizarGUI();
        }
    }
    
    private boolean manejarAtaqueMuerte(PiezaAbstracta muerte, PiezaAbstracta objetivo, Point clickObjetivo, boolean esAdyacente) {
        String[] opciones = {"Ataque Normal", "Lanzar Lanza (2 casillas)", "Zombie Attack"};
        StringBuilder mensaje = new StringBuilder("Selecciona el tipo de ataque:\n\n");
        mensaje.append("1. Ataque Normal (adyacente, 4 de daño)\n");
        mensaje.append("2. Lanzar Lanza (2 casillas, ignora escudo, 2 de daño)\n");
        mensaje.append("3. Zombie Attack (enemigo adyacente a zombie, 1 de daño)");
        
        int opcion = JOptionPane.showOptionDialog(this, mensaje.toString(), "Ataque de la Muerte",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        
        if (opcion == 0) {
            if (!esAdyacente) {
                logMensaje("Error: El ataque normal solo funciona con enemigos adyacentes.");
                return false;
            }
            String resultado = muerte.realizarAtaqueNormal(objetivo);
            logMensaje(resultado);
            if (resultado.contains("DESTRUYÓ")) {
                tableroJuego.eliminarPieza(objetivo);
            }
            return true;
        } else if (opcion == 1) {
            if (esAtaqueEspecialLanzaValido(muerte.getPosicion(), clickObjetivo)) {
                String resultado = muerte.realizarAtaqueEspecial(objetivo);
                logMensaje(resultado);
                if (resultado.contains("DESTRUYÓ")) {
                    tableroJuego.eliminarPieza(objetivo);
                }
                return true;
            } else {
                logMensaje("Error: Lanzar Lanza requiere que el enemigo esté a exactamente 2 casillas sin obstrucción.");
                return false;
            }
        } else if (opcion == 2) {
            Zombie zombieAtacante = buscarZombieAdyacente(clickObjetivo);
            if (zombieAtacante != null) {
                String resultado = zombieAtacante.atacarPorOrden(objetivo);
                logMensaje("¡Zombie Attack! " + resultado);
                if (resultado.contains("DESTRUYÓ")) {
                    tableroJuego.eliminarPieza(objetivo);
                }
                return true;
            } else {
                logMensaje("Error: No hay ningún Zombie propio adyacente al enemigo.");
                return false;
            }
        } else {
            logMensaje("Ataque cancelado.");
            return false;
        }
    }
    
    private boolean esAtaqueEspecialLanzaValido(Point origen, Point destino) {
        int dx = Math.abs(origen.x - destino.x);
        int dy = Math.abs(origen.y - destino.y);
        
        if (!((dx == 2 && dy == 0) || (dx == 0 && dy == 2) || (dx == 2 && dy == 2))) {
            return false;
        }
        
        int stepX = (destino.x - origen.x) == 0 ? 0 : (destino.x - origen.x) / Math.abs(destino.x - origen.x);
        int stepY = (destino.y - origen.y) == 0 ? 0 : (destino.y - origen.y) / Math.abs(destino.y - origen.y);
        
        int x = origen.x + stepX;
        int y = origen.y + stepY;
        
        if (tableroJuego.getTablero()[x][y] != null) {
            return false;
        }
        
        return true;
    }
    
    private Zombie buscarZombieAdyacente(Point posicionEnemigo) {
        String colorActivo = tableroJuego.getJugadorActivo().getColor();
        
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                
                int nx = posicionEnemigo.x + dx;
                int ny = posicionEnemigo.y + dy;
                
                if (nx >= 0 && nx < 6 && ny >= 0 && ny < 6) {
                    PiezaAbstracta pieza = tableroJuego.getTablero()[nx][ny];
                    if (pieza != null && pieza.getNombre().equals("Zombie") && pieza.getColor().equals(colorActivo)) {
                        return (Zombie) pieza;
                    }
                }
            }
        }
        return null;
    }
    
    private void manejarRetiro(ActionEvent e) {
        boolean confirm = DialogoOscuro.mostrarConfirmacion(this, 
            "¿Estás seguro que deseas retirarte?\nEl rival ganará automáticamente.", 
            "Confirmar Retiro");
        if (confirm) {
            Jugador ganador = tableroJuego.getJugadorRival();
            Jugador perdedor = tableroJuego.getJugadorActivo();
            
            ganador.addPuntos(3);
            logMensaje("JUGADOR " + perdedor.getUsername().toUpperCase() + " SE HA RETIRADO. ¡GANADOR: " + ganador.getUsername().toUpperCase() + "!");
            DialogoOscuro.mostrarMensaje(this, 
                "JUGADOR " + ganador.getUsername().toUpperCase() + " HA GANADO!\n" + 
                perdedor.getUsername().toUpperCase() + " se retiró.", 
                "Fin del Juego");
            dispose();
        }
    }
    
    private void finalizarTurno() {
        Jugador ganador = tableroJuego.verificarGanador();
        if (ganador != null) {
            ganador.addPuntos(3);
            DialogoOscuro.mostrarMensaje(this, 
                "¡VICTORIA!\n\n" + 
                "JUGADOR " + ganador.getUsername().toUpperCase() + 
                " VENCIÓ A JUGADOR " + tableroJuego.getJugadorRival().getUsername().toUpperCase() + 
                "\n\n¡FELICIDADES! Has ganado 3 puntos", 
                "Fin del Juego");
            dispose();
        } else {
            tableroJuego.cambiarTurno();
            piezaOrigen = null;
            piezaSeleccionadaRuleta = null;
            btnRuleta.setEnabled(true);
            actualizarGUI();
            logMensaje("--- CAMBIO DE TURNO ---");
        }        
    }
    
    private boolean esMovimientoAdyacenteValido(Point origen, Point destino) {
        int dx = Math.abs(origen.x - destino.x);
        int dy = Math.abs(origen.y - destino.y);
        
        return (dx <= 1 && dy <= 1) && (dx + dy >= 1);
    }
    
    private boolean esMovimientoValido(PiezaAbstracta pieza, Point destino) {
        Point origen = pieza.getPosicion();
        int dx = Math.abs(origen.x - destino.x);
        int dy = Math.abs(origen.y - destino.y);
        
        if (pieza.getNombre().equals("HombreLobo")) {
            if ((dx <= 2 && dy <= 2) && (dx + dy >= 1)) {
                return verificarCaminoLibre(origen, destino);
            }
            return false;
        } else {
            return esMovimientoAdyacenteValido(origen, destino);
        }
    }
    
    private boolean verificarCaminoLibre(Point origen, Point destino) {
        int dx = destino.x - origen.x;
        int dy = destino.y - origen.y;
        
        int pasos = Math.max(Math.abs(dx), Math.abs(dy));
        int stepX = dx == 0 ? 0 : dx / Math.abs(dx);
        int stepY = dy == 0 ? 0 : dy / Math.abs(dy);
        
        int x = origen.x + stepX;
        int y = origen.y + stepY;
        
        for (int i = 1; i < pasos; i++) {
            if (tableroJuego.getTablero()[x][y] != null) {
                return false;
            }
            x += stepX;
            y += stepY;
        }
        
        return tableroJuego.getTablero()[destino.x][destino.y] == null;
    }
}
