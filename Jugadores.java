/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package a2.f1_fantasy;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ALUMNOS
 */
public class Jugadores extends javax.swing.JFrame {
        
    private static final Color FONDO = new Color(24, 24, 28);
    private static final Color ROJO_F1 = new Color(200, 16, 46);
    private static final Font FUENTE = new Font("Segoe UI", Font.BOLD, 14);

    private final Plantilla plantilla = new Plantilla(100.0);
    private final List<Jugador> mercado = new ArrayList<>();
    private final DefaultListModel<Jugador> modeloPlantilla = new DefaultListModel<>();
    private final JLabel lblPresupuesto = new JLabel();
    private JTable tabla;
    private JList<Jugador> listaPlantilla;


    /**
     * Creates new form Jugadores
     */
    public Jugadores() {
       setTitle("Fantasy - Mercado");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(FONDO);
        setLayout(new BorderLayout(10, 10));

        cargarJugadores();

        lblPresupuesto.setForeground(Color.WHITE);
        lblPresupuesto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPresupuesto.setHorizontalAlignment(SwingConstants.CENTER);
        actualizarPresupuesto();

        add(lblPresupuesto, BorderLayout.NORTH);
        add(crearPanelMercado(), BorderLayout.CENTER);
        add(crearPanelPlantilla(), BorderLayout.EAST);
    }

    private JPanel crearPanelMercado() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Piloto", "Equipo", "Valor (M)"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int col) {
                return false;
            }
        };
        for (Jugador j : mercado) {
            modelo.addRow(new Object[]{j.getNombre(), j.getEquipo(), j.getValor()});
        }

        tabla = new JTable(modelo);
        tabla.setRowHeight(26);
        tabla.setAutoCreateRowSorter(true);

        JButton btnFichar = new JButton("Fichar");
        estilizarBoton(btnFichar);
        btnFichar.addActionListener(e -> fichar());

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 0));
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(btnFichar, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelPlantilla() {
        listaPlantilla = new JList<>(modeloPlantilla);

        JButton btnVender = new JButton("Vender");
        estilizarBoton(btnVender);
        btnVender.addActionListener(e -> vender());

        JLabel titulo = new JLabel("Mi plantilla", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(FUENTE);

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        panel.setPreferredSize(new Dimension(230, 0));
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(listaPlantilla), BorderLayout.CENTER);
        panel.add(btnVender, BorderLayout.SOUTH);
        return panel;
    }

    private void fichar() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) return;
        Jugador j = mercado.get(tabla.convertRowIndexToModel(fila));
        if (plantilla.fichar(j)) {
            modeloPlantilla.addElement(j);
            actualizarPresupuesto();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No puedes fichar a " + j.getNombre()
                    + ": sin presupuesto, plantilla llena o ya lo tienes.");
        }
    }

    private void vender() {
        Jugador j = listaPlantilla.getSelectedValue();
        if (j == null) return;
        if (plantilla.vender(j)) {
            modeloPlantilla.removeElement(j);
            actualizarPresupuesto();
        }
    }

    private void actualizarPresupuesto() {
        lblPresupuesto.setText(String.format("Presupuesto: %.1f M", plantilla.getPresupuesto()));
    }

    private void estilizarBoton(JButton boton) {
        boton.setBackground(ROJO_F1);
        boton.setForeground(Color.WHITE);
        boton.setFont(FUENTE);
        boton.setFocusPainted(false);
    }

      private void cargarJugadores() {
        mercado.add(new Jugador("Max Verstappen", "Red Bull", 60.0));
    mercado.add(new Jugador("Liam Lawson", "Red Bull", 24.0));

    mercado.add(new Jugador("Lando Norris", "McLaren", 30.0));
    mercado.add(new Jugador("Oscar Piastri", "McLaren", 34.0));

    mercado.add(new Jugador("Charles Leclerc", "Ferrari", 26.0));
    mercado.add(new Jugador("Lewis Hamilton", "Ferrari", 50.0));

    mercado.add(new Jugador("George Russell", "Mercedes-Benz", 44.0));
    mercado.add(new Jugador("Kimi Antonelli", "Mercedes-Benz", 36.0));

    mercado.add(new Jugador("Fernando Alonso", "Aston Martin", 30.0));
    mercado.add(new Jugador("Lance Stroll", "Aston Martin", 16.0));

    mercado.add(new Jugador("Pierre Gasly", "Alpine", 20.0));
    mercado.add(new Jugador("Franco Colapinto", "Alpine", 16.0));

    mercado.add(new Jugador("Alex Albon", "Williams", 20.0));
    mercado.add(new Jugador("Carlos Sainz", "Williams", 24.2));

    mercado.add(new Jugador("Esteban Ocon", "Haas", 22.0));
    mercado.add(new Jugador("Oliver Bearman", "Haas", 23.3));

    mercado.add(new Jugador("Isack Hadjar", "Racing Bulls", 29.0));
    mercado.add(new Jugador("Arvid Lindblad", "Racing Bulls", 14.5));

    mercado.add(new Jugador("Nico Hulkenberg", "Audi", 16.0));
    mercado.add(new Jugador("Gabriel Bortoleto", "Audi", 14.0));

    mercado.add(new Jugador("Sergio Perez", "Cadillac", 24.0));
    mercado.add(new Jugador("Valtteri Bottas", "Cadillac", 20.0));
  
}

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
