/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto.espe.ec;

import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author PC
 */
public class VentanaPedido extends javax.swing.JFrame {

    String spCantidadProducto;
    int filaSeleccionada = -1;

    DefaultTableModel dtmproducto = new DefaultTableModel();
    DefaultTableModel dtmcliente = new DefaultTableModel();

    public VentanaPedido() {
        initComponents();
        initVentanaPedido();
    }
    
    private void cargarCodigoEnComboBox(DefaultTableModel modeloTabla, int columnaCodigo) {
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String nombre = modeloTabla.getValueAt(i, columnaCodigo).toString();
            modeloComboBox.addElement(nombre);
        }

        cmbCodigoPedidos.setModel(modeloComboBox);
    }

    private void cargarNombresEnComboBox(DefaultTableModel modeloTabla, int columnaNombre) {
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String nombre = modeloTabla.getValueAt(i, columnaNombre).toString();
            modeloComboBox.addElement(nombre);
        }

        cmbClientesNombres.setModel(modeloComboBox);
    }

    public void initVentanaPedido() {
        String[] titulo_1 = new String[]{"CODIGO", "PRODUCTO", "PRECIO", "STOCK"};
        dtmproducto.setColumnIdentifiers(titulo_1);
        tblProducto.setModel(dtmproducto);

        String[] titulo_2 = new String[]{"CEDULA", "NOMBRE", "TELEFONO", "DIRECCION"};
        dtmcliente.setColumnIdentifiers(titulo_2);
        tblCliente.setModel(dtmcliente);
    }

    void AgregarCliente() {
        if (validarCliente() == true) {
            dtmcliente.addRow(new Object[]{
                txtCedulaCliente.getText(), txtNombreCliente.getText(), txtContactoCliente.getText(), txtDireccionCliente.getText()
            });
            cargarNombresEnComboBox(dtmcliente, 1);

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
        }
    }

    public void mostrarDatosClientes() {
        filaSeleccionada = tblCliente.getSelectedRow();
        if (filaSeleccionada == -1) {
            System.out.println("hola");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblCliente.getModel();

        txtCedulaCliente.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        txtNombreCliente.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
        txtContactoCliente.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
        txtDireccionCliente.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());

    }

    public void eliminarCliente() {
        filaSeleccionada = tblCliente.getSelectedRow();

        if (filaSeleccionada != -1) {
            dtmcliente.removeRow(filaSeleccionada);
            limpiarCliente();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
        }
    }

    private boolean validarProducto() {
        boolean validar = false;
        if ((cmbSeleccionProducto.getSelectedItem().toString().length() > 0) && (txtCodigoProducto.getText().length() > 0) && (txtPrecioProducto.getText().length() > 0) && (spCantidadProducto.length() > 0)) {
            validar = true;
        }
        return validar;

    }

    private boolean validarCliente() {
        boolean validar = false;
        if ((txtContactoCliente.getText().length() > 0) && (txtNombreCliente.getText().length() > 0) && (txtContactoCliente.getText().length() > 0) && (txtDireccionCliente.getText().length() > 0)) {
            validar = true;
        }
        return validar;

    }

    void AgregarProducto() {
        if (validarProducto() == true) {
            dtmproducto.addRow(new Object[]{
                txtCodigoProducto.getText(), cmbSeleccionProducto.getSelectedItem().toString(), txtPrecioProducto.getText(), spCantidadProducto
            });
            cargarCodigoEnComboBox(dtmproducto, 0);

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
        }
    }

    public void mostrarDatosProducto() {
        filaSeleccionada = tblProducto.getSelectedRow();
        if (filaSeleccionada == -1) {
            System.out.println("hola");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tblProducto.getModel();

        txtCodigoProducto.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        cmbSeleccionProducto.setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
        txtPrecioProducto.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
        spCantidad.setValue(modeloTabla.getValueAt(filaSeleccionada, 3));

    }

    public void eliminarProducto() {
        filaSeleccionada = tblProducto.getSelectedRow();

        if (filaSeleccionada != -1) {
            dtmproducto.removeRow(filaSeleccionada);
            limpiarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
        }
    }

    private void limpiarProducto() {
        txtCodigoProducto.setText("");
        cmbSeleccionProducto.setSelectedIndex(0);
        txtPrecioProducto.setText("");
        spCantidad.setValue(0);

    }

    private void limpiarCliente() {
        txtCedulaCliente.setText("");
        txtNombreCliente.setText("");
        txtContactoCliente.setText("");
        txtDireccionCliente.setText("");
    }

    private void limpiarPedido() {
        
        txtProductoPedido.setText("");
        txtPrecioProducto.setText("");
        txtCantiddPedido.setText("");
        txtStockPedido.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnPedidos = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnInformacion = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtContactoCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccionCliente = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        txtCedulaCliente = new javax.swing.JTextField();
        btnAgregarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnLimpiarCliente = new javax.swing.JButton();
        txtID = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPrecioProducto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cmbSeleccionProducto = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        btnAgregarProducto = new javax.swing.JButton();
        btnModificarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnLimpiarProducto = new javax.swing.JButton();
        spCantidad = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        txtBuscarVentas = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDireccionEmpresa = new javax.swing.JTextArea();
        btnModificarEmpresa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProductoPedido = new javax.swing.JTextField();
        txtCantiddPedido = new javax.swing.JTextField();
        txtPrecioPedido = new javax.swing.JTextField();
        txtStockPedido = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPedido = new javax.swing.JTable();
        GuardarPedido = new javax.swing.JButton();
        ModificarPedido = new javax.swing.JButton();
        EliminarPedido = new javax.swing.JButton();
        LimpiarPedido = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbClientesNombres = new javax.swing.JComboBox<>();
        cmbCodigoPedidos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(126, 126, 178));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 153, 255));

        btnPedidos.setText("PEDIDOS");
        btnPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosActionPerformed(evt);
            }
        });

        btnClientes.setText("CLIENTE");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnProductos.setText("PRODUCTOS");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        btnVentas.setText("VENTAS");
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });

        btnInformacion.setText("INFORMACION");
        btnInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPedidos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProductos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(btnPedidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInformacion)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 510));

        jPanel7.setBackground(new java.awt.Color(153, 102, 255));

        jLabel10.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel10.setText("Cedula");

        jLabel11.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel11.setText("Nombre");

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel12.setText("Contacto");

        txtContactoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContactoClienteKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Serif", 3, 14)); // NOI18N
        jLabel13.setText("Dirección");

        txtDireccionCliente.setColumns(20);
        txtDireccionCliente.setRows(5);
        txtDireccionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionClienteKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtDireccionCliente);

        tblCliente.setBackground(new java.awt.Color(102, 102, 255));
        tblCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE", "CEDULA", "TELEFONO", "DIRECCION"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCliente);

        txtCedulaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyTyped(evt);
            }
        });

        btnAgregarCliente.setText("AGREGAR");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        btnEditarCliente.setText("MODIFICAR");
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnLimpiarCliente.setText("LIMPIAR");
        btnLimpiarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtContactoCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(btnAgregarCliente)
                        .addGap(32, 32, 32)
                        .addComponent(btnEditarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarCliente)
                        .addGap(30, 30, 30)
                        .addComponent(btnLimpiarCliente)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(13, 13, 13)
                        .addComponent(txtContactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLimpiarCliente))
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("CLIENTE", jPanel3);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel14.setText("Código");

        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel15.setText("Producto");

        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel16.setText("Precio");

        txtPrecioProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioProductoKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel17.setText("Cantidad");

        cmbSeleccionProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione...", "Mote Especial", "Mote Mediano", "Mote Grueso", "Mote Parejo", "Arbeja", "Lenteja", "Maiz" }));
        cmbSeleccionProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSeleccionProductoActionPerformed(evt);
            }
        });

        tblProducto.setBackground(new java.awt.Color(204, 255, 204));
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "PRODUCTO", "PRECIO", "STOCK"
            }
        ));
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblProducto);

        btnAgregarProducto.setText("AGREGAR");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnModificarProducto.setText("EDITAR");
        btnModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setText("ELIMINAR");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnLimpiarProducto.setText("LIMPIAR");
        btnLimpiarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProductoActionPerformed(evt);
            }
        });

        spCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        spCantidad.setInheritsPopupMenu(true);
        spCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spCantidadStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnAgregarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarProducto))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel14)
                                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(cmbSeleccionProducto, 0, 0, Short.MAX_VALUE)
                                    .addComponent(txtPrecioProducto))
                                .addGap(41, 41, 41)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbSeleccionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrecioProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarProducto)
                    .addComponent(btnModificarProducto)
                    .addComponent(btnEliminarProducto)
                    .addComponent(btnLimpiarProducto))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PRODUCTOS", jPanel4);

        tblVentas.setBackground(new java.awt.Color(255, 153, 255));
        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "PRODUCTO", "CANTIDAD", "TOTAL"
            }
        ));
        jScrollPane5.setViewportView(tblVentas);

        jLabel18.setText("BUSCAR");

        txtBuscarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarVentasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(27, 27, 27)
                        .addComponent(txtBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtBuscarVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("VENTAS", jPanel5);

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel19.setText("EMPRESA");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("RUC");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Nombre");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Telefono");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("Dirección");

        txtDireccionEmpresa.setColumns(20);
        txtDireccionEmpresa.setRows(5);
        jScrollPane6.setViewportView(txtDireccionEmpresa);

        btnModificarEmpresa.setText("MODIFICAR");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(btnModificarEmpresa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addGap(13, 13, 13))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addGap(115, 115, 115))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addComponent(btnModificarEmpresa))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INFORMACION", jPanel6);

        jLabel2.setText("Codigo");

        jLabel3.setText("Producto");

        jLabel4.setText("Cantidad");

        jLabel5.setText("Precio");

        jLabel6.setText("Stock");

        txtPrecioPedido.setEditable(false);

        tblPedido.setBackground(new java.awt.Color(122, 226, 235));
        tblPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tblPedido);

        GuardarPedido.setText("AGREGAR");
        GuardarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarPedidoActionPerformed(evt);
            }
        });

        ModificarPedido.setText("MODIFICAR");

        EliminarPedido.setText("ELIMINAR");

        LimpiarPedido.setText("LIMPIAR");

        jLabel1.setText("Clientes:");

        cmbClientesNombres.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbCodigoPedidos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbCodigoPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProductoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(txtCantiddPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbClientesNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(48, 48, 48)
                                    .addComponent(jLabel3)
                                    .addGap(97, 97, 97)
                                    .addComponent(jLabel4))))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel5)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtPrecioPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txtStockPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(GuardarPedido)
                        .addGap(38, 38, 38)
                        .addComponent(ModificarPedido)
                        .addGap(26, 26, 26)
                        .addComponent(EliminarPedido)
                        .addGap(41, 41, 41)
                        .addComponent(LimpiarPedido)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantiddPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCodigoPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbClientesNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(GuardarPedido)
                        .addComponent(ModificarPedido)
                        .addComponent(EliminarPedido))
                    .addComponent(LimpiarPedido))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PEDIDOS", jPanel2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 112, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 670, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosActionPerformed

    }//GEN-LAST:event_btnPedidosActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed

    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed

    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionActionPerformed

    }//GEN-LAST:event_btnInformacionActionPerformed

    private void txtBuscarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarVentasActionPerformed

    }//GEN-LAST:event_txtBuscarVentasActionPerformed

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        char validacionId = evt.getKeyChar();
        if (Character.isLetter(validacionId)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo digitos");
        }
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void txtPrecioProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioProductoKeyTyped
        char validacion = evt.getKeyChar();
        if (Character.isLetter(validacion)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo digitos");
        }
    }//GEN-LAST:event_txtPrecioProductoKeyTyped

    private void spCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spCantidadStateChanged
        int valorActual = (int) spCantidad.getValue();
        spCantidadProducto = String.valueOf(valorActual);
    }//GEN-LAST:event_spCantidadStateChanged

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        AgregarProducto();
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarProductoActionPerformed
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro de actualizar los datos?", "Confirmar actualizacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tblProducto.getModel();

                String nuevoCodigo = txtCodigoProducto.getText();
                Object nuevaProducto = cmbSeleccionProducto.getSelectedItem();
                String nuevoPrecio = txtPrecioProducto.getText();
                int nuevoCantidad = (Integer) spCantidad.getValue();

                model.setValueAt(nuevoCodigo, filaSeleccionada, 0);
                model.setValueAt(nuevaProducto, filaSeleccionada, 1);
                model.setValueAt(nuevoPrecio, filaSeleccionada, 2);
                model.setValueAt(nuevoCantidad, filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null, "Fila Actualizada Correctamente");
            } else {
                ListSelectionModel seleccionModel = tblProducto.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el registro a actualizar");
        }

    }//GEN-LAST:event_btnModificarProductoActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        mostrarDatosProducto();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar los datos?", "Confirmar actualizacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                eliminarProducto();
            } else {
                ListSelectionModel seleccionModel = tblProducto.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el registro a eliminar");
        }

    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnLimpiarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProductoActionPerformed
        limpiarProducto();
    }//GEN-LAST:event_btnLimpiarProductoActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        AgregarCliente();
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        mostrarDatosClientes();
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar los datos?", "Confirmar actualizacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                eliminarCliente();
            } else {
                ListSelectionModel seleccionModel = tblProducto.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el registro a eliminar");
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnLimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteActionPerformed
        limpiarCliente();
    }//GEN-LAST:event_btnLimpiarClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Seguro de actualizar los datos?", "Confirmar actualizacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tblCliente.getModel();

                String nuevaCedula = txtCedulaCliente.getText();
                String nuevoNombre = txtNombreCliente.getText();
                String nuevoContacto = txtContactoCliente.getText();
                String nuevaDireccion = txtDireccionCliente.getText();

                model.setValueAt(nuevaCedula, filaSeleccionada, 0);
                model.setValueAt(nuevoNombre, filaSeleccionada, 1);
                model.setValueAt(nuevoContacto, filaSeleccionada, 2);
                model.setValueAt(nuevaDireccion, filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null, "Fila Actualizada Correctamente");
            } else {
                ListSelectionModel seleccionModel = tblCliente.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el registro a actualizar");
        }
    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void GuardarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GuardarPedidoActionPerformed

    private void txtContactoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoClienteKeyTyped
        char validacionTelefono = evt.getKeyChar();
        if (Character.isLetter(validacionTelefono)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo digitos");
        }
    }//GEN-LAST:event_txtContactoClienteKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        char validacionApellido = evt.getKeyChar();
        if (Character.isDigit(validacionApellido)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");

        }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void txtDireccionClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionClienteKeyTyped
        char validacionApellido = evt.getKeyChar();
        if (Character.isDigit(validacionApellido)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo letras");

        }
    }//GEN-LAST:event_txtDireccionClienteKeyTyped

    private void txtCedulaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyTyped
        char validarCedula = evt.getKeyChar();
        if (Character.isLetter(validarCedula)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingrese solo numeros");
        }
    }//GEN-LAST:event_txtCedulaClienteKeyTyped

    private void cmbSeleccionProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSeleccionProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSeleccionProductoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarPedido;
    private javax.swing.JButton GuardarPedido;
    private javax.swing.JButton LimpiarPedido;
    private javax.swing.JButton ModificarPedido;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JButton btnLimpiarCliente;
    private javax.swing.JButton btnLimpiarProducto;
    private javax.swing.JButton btnModificarEmpresa;
    private javax.swing.JButton btnModificarProducto;
    private javax.swing.JButton btnPedidos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnVentas;
    private javax.swing.JComboBox<String> cmbClientesNombres;
    private javax.swing.JComboBox<String> cmbCodigoPedidos;
    private javax.swing.JComboBox<String> cmbSeleccionProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblPedido;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBuscarVentas;
    private javax.swing.JTextField txtCantiddPedido;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtContactoCliente;
    private javax.swing.JTextArea txtDireccionCliente;
    private javax.swing.JTextArea txtDireccionEmpresa;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtPrecioPedido;
    private javax.swing.JTextField txtPrecioProducto;
    private javax.swing.JTextField txtProductoPedido;
    private javax.swing.JTextField txtStockPedido;
    private javax.swing.JTextField txtTelefonoEmpresa;
    // End of variables declaration//GEN-END:variables
}
