/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package proyecto.espe.vistaCliente;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import proyecto.espe.modelos.Cliente;
import proyecto.espe.servicio.ClienteServicio;
import proyecto.espe.vistaGeneral.MenuAdmin;
import proyecto.espe.vistaGeneral.MenuEncargado;

/**
 *
 * @author PC
 */
public class Cliente_Consultar extends javax.swing.JInternalFrame {

     private Cliente cliente;
    private DefaultTableModel modeloTabla;
    public static String codCedula = "";

    FondoPanel fondo2 = new FondoPanel();
    public Cliente_Consultar() {
        this.setContentPane(fondo2);
        initComponents();
        modeloTabla = (DefaultTableModel) tblDatosCliente.getModel();
    }

    
    public void limpiarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDatosCliente.getModel();
        modeloTabla.setRowCount(0);

    }
    public void cargarDatos() {
        limpiarTabla();
        cliente = ClienteServicio.BuscarCedulaCliente(txtBuscarCliente.getText().trim());
        if (cliente != null) {
            modeloTabla.addRow(new Object[]{
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                cliente.getDireccion(),});
            btnModificarCliente.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosCliente = new javax.swing.JTable();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras (1).png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("CLIENTES REGISTRADOS ");

        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        tblDatosCliente.setBackground(new java.awt.Color(153, 204, 255));
        tblDatosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CEDULA", "NOMBRE ", "APELLIDO", "TELEFONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDatosCliente);

        btnModificarCliente.setBackground(new java.awt.Color(95, 245, 232));
        btnModificarCliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnModificarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnModificarCliente.setText("MODIFICAR");
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setBackground(new java.awt.Color(95, 245, 232));
        btnEliminarCliente.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnRegresar)
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarCliente))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btnModificarCliente)
                        .addGap(28, 28, 28)
                        .addComponent(btnEliminarCliente)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarCliente)
                    .addComponent(btnEliminarCliente))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed

         if (!txtBuscarCliente.getText().trim().equals("")) {
            limpiarTabla();

            cliente = ClienteServicio.BuscarCedulaCliente(txtBuscarCliente.getText().trim());
            if (cliente != null) {

                modeloTabla.addRow(new Object[]{
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono(),
                    
                });
                btnModificarCliente.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe registros con esa cedula");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese la cedula");
        }

    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        Cliente_Modificar actualizar= new Cliente_Modificar();
        MenuAdmin.escritorio1.add(actualizar);
        actualizar.toFront();
        actualizar.setVisible(true);
    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        int filaSeleccionada = tblDatosCliente.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar los datos?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                int idPerfil = (int) tblDatosCliente.getValueAt(filaSeleccionada, 0);

                if (ClienteServicio.EliminarCliente(codCedula)) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró el registro para eliminarlo");
                }
            } else {
                ListSelectionModel seleccionModel = tblDatosCliente.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar");
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDatosCliente;
    private javax.swing.JTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/fondos/fondo8.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
