/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package a2.f1_fantasy;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.TitledBorder;
/**
/**
 *
 * @author ALUMNOS
 */
public class Principal extends javax.swing.JFrame {
         private static final Color FONDO = new Color(24, 24, 28);
    private static final Color ROJO_F1 = new Color(200, 16, 46);
    private static final Font FUENTE = new Font("Segoe UI", Font.BOLD, 14);

    private final Map<Integer, String[]> equiposPorAño = new LinkedHashMap<>();
    private final JComboBox<Integer> cmbAño = new JComboBox<>();
    private final JComboBox<String> cmbEquipo = new JComboBox<>();

    public Principal() {
    setTitle("Fantasy");
    setSize(500, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    getContentPane().setBackground(FONDO);
    setLayout(new BorderLayout());
    add(crearPanelSuperior(), BorderLayout.CENTER);

        GridBagConstraints gbcPrincipal = new GridBagConstraints();
        gbcPrincipal.fill = GridBagConstraints.BOTH;
        gbcPrincipal.gridx = 0;
        gbcPrincipal.gridy = 0;
        gbcPrincipal.weightx = 0.6;
        gbcPrincipal.weighty = 0.5;
        gbcPrincipal.insets = new Insets(10, 10, 5, 5);
        setLayout(new BorderLayout());
        add(crearPanelSuperior(), BorderLayout.CENTER);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ROJO_F1, 2),
                "FANTASY", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16), Color.WHITE));

        java.net.URL urlFondo = getClass().getResource("/imagenes/Fondo.jpg");
        Image imagen = new ImageIcon(urlFondo).getImage().getScaledInstance(500, 700, Image.SCALE_SMOOTH);

        JLabel fondo = new JLabel(new ImageIcon(imagen));
        fondo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Logo encima del botón
        java.net.URL urlLogo = getClass().getResource("/imagenes/Logo.png");
        Image logo = new ImageIcon(urlLogo).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 20, 0);
        fondo.add(new JLabel(new ImageIcon(logo)), c);

        // Botón debajo
        JButton btnIniciar = new JButton("Iniciar");
        estilizarBoton(btnIniciar);
        btnIniciar.addActionListener(e -> {
            getContentPane().removeAll();
            setLayout(new BorderLayout());
            add(crearPanelSeleccion(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        c.gridy = 1;
        c.insets = new Insets(0, 0, 200, 0);
        fondo.add(btnIniciar, c);

        panel.add(fondo, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelSeleccion() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 25));
        panel.setBackground(FONDO);

        cargarEquipos();
        cmbAño.removeAllItems();
        for (Integer año : equiposPorAño.keySet()) {
            cmbAño.addItem(año);
        }
        cmbAño.addActionListener(e -> actualizarEquipos());
        actualizarEquipos();

        cmbAño.setPreferredSize(new Dimension(100, 30));
        cmbEquipo.setPreferredSize(new Dimension(160, 30));

        JButton btnContinuar = new JButton("Continuar");
    btnContinuar.addActionListener(e -> {
            int año = (int) cmbAño.getSelectedItem();
            String equipo = (String) cmbEquipo.getSelectedItem();

            JOptionPane.showMessageDialog(this, "Temporada " + año + " - " + equipo);

            new Jugadores().setVisible(true);
            dispose();
        });
        panel.add(crearEtiqueta("Temporada:"));
        panel.add(cmbAño);
        panel.add(crearEtiqueta("Equipo:"));
        panel.add(cmbEquipo);
        panel.add(btnContinuar);
        return panel;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(Color.WHITE);
        label.setFont(FUENTE);
        return label;
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(ROJO_F1);
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE);
        boton.setFocusPainted(false);
    }

    private void cargarEquipos() {
        equiposPorAño.put(2024, new String[]{"Alpine", "Aston Martin", "Ferrari", "Haas",
            "McLaren", "Mercedes", "Racing Bulls", "Red Bull", "Sauber", "Williams"});
        equiposPorAño.put(2025, new String[]{"Alpine", "Aston Martin", "Ferrari", "Haas",
            "McLaren", "Mercedes", "Racing Bulls", "Red Bull", "Sauber", "Williams"});
        equiposPorAño.put(2026, new String[]{"Alpine", "Aston Martin", "Audi", "Cadillac",
            "Ferrari", "Haas", "McLaren", "Mercedes", "Racing Bulls", "Red Bull", "Williams"});
    }

    private void actualizarEquipos() {
        if (cmbAño.getSelectedItem() == null) return;
        int año = (int) cmbAño.getSelectedItem();
        cmbEquipo.setModel(new DefaultComboBoxModel<>(equiposPorAño.get(año)));
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Paleta global (después de Nimbus, antes de crear la ventana)
        UIManager.put("control", FONDO);
        UIManager.put("nimbusBase", ROJO_F1);
        UIManager.put("nimbusLightBackground", new Color(40, 40, 46));
        UIManager.put("text", Color.WHITE);
        UIManager.put("nimbusFocus", new Color(225, 6, 0));
        UIManager.put("defaultFont", FUENTE);

        java.awt.EventQueue.invokeLater(() -> new Jugadores().setVisible(true));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
