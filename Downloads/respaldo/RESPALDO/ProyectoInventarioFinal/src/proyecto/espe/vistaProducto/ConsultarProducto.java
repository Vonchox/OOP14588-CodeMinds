/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package proyecto.espe.vistaProducto;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import proyecto.espe.modelos.Productos;
import proyecto.espe.servicio.ProductosServicio;
import proyecto.espe.vistaGeneral.MenuAdmin;

/**
 *
 * @author PC
 */
public class ConsultarProducto extends javax.swing.JInternalFrame {

    public static boolean abierto = false;
    private static DefaultTableModel modeloTabla;
    private static List<Productos> listaProductos = null;
    public static String codCodigo = "";

    FondoPanel f1 = new FondoPanel();

    public ConsultarProducto() {
        this.setContentPane(f1);
        initComponents();
        cargarProductos();

    }

    public static void cargarProductos() {
        cmbCodigo.removeAllItems();
        cmbCodigo.addItem("Todos");
        cmbCodigo.setSelectedItem("Todos");
        listaProductos = ProductosServicio.ListarProductos();
        cargarTablaTodasProductos(listaProductos);
        cargarComboCodigo(listaProductos);

    }

    public static void cargarComboCodigo(List<Productos> productos) {
        for (Productos product : productos) {
            cmbCodigo.addItem(product.getCodigo() + " - " + product.getNombreProducto());
        }
    }

    public static void limpiarTabla() {
        modeloTabla = (DefaultTableModel) tblDatosProductos.getModel();
        modeloTabla.setRowCount(0);
    }

    public static void cargarTablaTodasProductos(List<Productos> productos) {
        limpiarTabla();
        for (Productos temp : productos) {
            modeloTabla.addRow(new Object[]{
                temp.getCodigo(),
                temp.getNombreProducto(),
                temp.getPrecio(),
                temp.getCantidad()
            });
        }
    }

    public void cargarTablaBusqueda(String codigo) {
        limpiarTabla();
        Productos temp = ProductosServicio.BuscarCodigoProductos(codigo);
        modeloTabla.addRow(new Object[]{
            temp.getCodigo(),
            temp.getNombreProducto(),
            temp.getPrecio(),
            temp.getCantidad()
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatosProductos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnArchivo = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        btnInsertar1 = new javax.swing.JButton();
        cmbCodigo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("CONSULTAR PRODUCTO");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblDatosProductos.setBackground(new java.awt.Color(153, 204, 255));
        tblDatosProductos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblDatosProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PRODUCTO", "PRECIO", "CANTIDAD"
            }
        ));
        jScrollPane1.setViewportView(tblDatosProductos);

        btnModificar.setBackground(new java.awt.Color(255, 204, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 204, 204));
        btnEliminar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/boton-eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnArchivo.setBackground(new java.awt.Color(51, 51, 51));
        btnArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/carpeta.png"))); // NOI18N
        btnArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArchivoActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/atras (1).png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnInsertar1.setBackground(new java.awt.Color(255, 204, 204));
        btnInsertar1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnInsertar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editar.png"))); // NOI18N
        btnInsertar1.setText("INSERTAR");
        btnInsertar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertar1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Preoductos: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addComponent(cmbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(73, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInsertar1)
                                .addGap(38, 38, 38)
                                .addComponent(btnModificar)
                                .addGap(29, 29, 29)
                                .addComponent(btnEliminar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(btnArchivo))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInsertar1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEliminar)
                        .addComponent(btnModificar)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArchivoActionPerformed
        CodigosProductos codigos = new CodigosProductos();
        MenuAdmin.escritorio1.add(codigos);
        codigos.toFront();
        codigos.setVisible(true);
    }//GEN-LAST:event_btnArchivoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tblDatosProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            codCodigo = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            ModificarProducto modificar = new ModificarProducto();
            MenuAdmin.escritorio1.add(modificar);
            modificar.toFront();
            modificar.setVisible(true);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro a modificar");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnInsertar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertar1ActionPerformed
        IngresarProductos ingresar = new IngresarProductos();
        ingresar.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnInsertar1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        limpiarTabla();
        if (cmbCodigo.getSelectedItem() == "Todos") {
            listaProductos = ProductosServicio.ListarProductos();
            cargarTablaTodasProductos(listaProductos);
        } else {
            String dato = cmbCodigo.getSelectedItem().toString();
            String codigo[] = dato.split("-");
            cargarTablaBusqueda(codigo[0].trim());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = tblDatosProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                codCodigo = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
                if (ProductosServicio.EliminarProductos(codCodigo)) {
                    JOptionPane.showMessageDialog(null, "El registro se eliminó correctamente");
                    modeloTabla.removeRow(filaSeleccionada);
                }
            } else {
                ListSelectionModel selectionModel = tblDatosProductos.getSelectionModel();
                selectionModel.clearSelection();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro a eliminar");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArchivo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegresar;
    private static javax.swing.JComboBox<String> cmbCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTable tblDatosProductos;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/fondos/fondo10.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
