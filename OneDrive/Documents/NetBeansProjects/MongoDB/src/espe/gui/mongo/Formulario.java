/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package espe.gui.mongo;

import com.mongodb.DB;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.systemjaade.edad.util.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author vonch
 */
public class Formulario extends javax.swing.JFrame {

    Conexion conn = new Conexion();
    MongoDatabase database;
    DB db;
    int filaSeleccionada = -1;

    public Formulario() {
        if (conn != null) {
            conn = conn.crearConexion();
            database = conn.getDataB();
        }
        initComponents();
        //txtID.setVisible(false);
        mostrarDatosTabla();
    }

  public int calcularEdad(Date fechaNacimiento){
      LocalDate Jin= fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate Jon= LocalDate.now();
      return Period.between(Jin, Jon).getYears();
  }

    public void mostrarDatosTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDatos.getModel();
        modeloTabla.setRowCount(0);

        MongoCollection<Document> collection = database.getCollection("Registro");
        FindIterable<Document> documents = collection.find();

        for (Document document : documents) {
            Object id = document.get("_id");
            String nombre = document.getString("estudiante");
            //Object edad = 0;
            int nivel = document.getInteger("nivel");
            String carrera = document.getString("carrera");
            Date fecha = document.getDate("fechaNacimiento");
            SimpleDateFormat calendario= new SimpleDateFormat("yyyy/MM/dd");
            String edad = document.getString("edad");
            modeloTabla.addRow(new Object[]{id, nombre, calendario.format(fecha), edad, nivel, carrera});
        }
        TableColumnModel columnModel = tblDatos.getColumnModel();
        TableColumn columna = columnModel.getColumn(0);
        columna.setMinWidth(0);
        columna.setMaxWidth(0);

    }

    private void limpiar() {
        NombreEstudiante.setText("");
        Edad.setText("");
        Nivel.setValue(0);
        calendar.setDate(null);
        Carrera.setSelectedIndex(0);
    }

    private void mostrarDatosCampos() {
        
        filaSeleccionada = tblDatos.getSelectedRow();
        if (filaSeleccionada == -1) {
            return;
        }
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDatos.getModel();
        txtID.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
        NombreEstudiante.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
        String fechaN= (String) modeloTabla.getValueAt(filaSeleccionada, 2);  
        Edad.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
        Nivel.setValue(modeloTabla.getValueAt(filaSeleccionada, 4));
        Carrera.setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 5).toString());
        SimpleDateFormat calendario=new SimpleDateFormat("yyyy/MM/dd");
        
        Date fecha= null;
        try{
            fecha=calendario.parse(fechaN);
            
        }catch(ParseException ex){
            System.out.println("Eror");
        }
        int edad= calcularEdad(fecha);
        Edad.setText(Integer.toString(edad));
        calendar.setDate(fecha);
        
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtID = new javax.swing.JLabel();
        saf = new javax.swing.JLabel();
        NombreEstudiante = new javax.swing.JTextField();
        txtNivel = new javax.swing.JLabel();
        Nivel = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        Carrera = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        Edad = new javax.swing.JTextField();
        Guardar = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saf.setText("Estudiante");

        NombreEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreEstudianteActionPerformed(evt);
            }
        });

        txtNivel.setText("Nivel");

        jLabel2.setText("Carrera");

        Carrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Escoja una Opcion", "Ing en Software", "Ing Mecanica", "Marketing" }));
        Carrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarreraActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha de Nacimiento");

        calendar.setDateFormatString("d/mm/yyy");
        calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPropertyChange(evt);
            }
        });

        jLabel4.setText("Edad");

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "Nacimiento", "Edad", "Nivel", "Carrera"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDatos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Nivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(159, 159, 159)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(saf, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Edad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saf)
                    .addComponent(NombreEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNivel)
                    .addComponent(Nivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Carrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(Edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(Guardar)
                .addGap(30, 30, 30)
                .addComponent(Actualizar)
                .addGap(26, 26, 26)
                .addComponent(Eliminar)
                .addGap(40, 40, 40)
                .addComponent(Limpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Guardar)
                    .addComponent(Actualizar)
                    .addComponent(Eliminar)
                    .addComponent(Limpiar))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreEstudianteActionPerformed

    private void CarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarreraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CarreraActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        int Anio=calendar.getCalendar().get(Calendar.YEAR);
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.set(Calendar.YEAR, Anio);
        Date fecha = fechaCalendar.getTime();
        MongoCollection coleccion = database.getCollection("Registro");
        Document documento = new Document("estudiante", NombreEstudiante.getText())
                .append("nivel", (Integer) Nivel.getValue())
                .append("carrera", Carrera.getSelectedItem())
                .append("fechaNacimiento", fecha)
                .append("edad", Edad.getText());
        coleccion.insertOne(documento);
        mostrarDatosTabla();
    }//GEN-LAST:event_GuardarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        int Anio=calendar.getCalendar().get(Calendar.YEAR);
        String fecha= String.valueOf(Anio);
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro de Actualizar los datos?", "Confirmar Actualizacion", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                MongoCollection coleccion = database.getCollection("Registro");

                Document filtro = new Document("_id", new ObjectId(txtID.getText()));
                Document documento = new Document("$set", new Document()
                        .append("estudiante", NombreEstudiante.getText())
                        .append("nivel", (Integer) (Nivel.getValue()))
                        .append("carrera", Carrera.getSelectedItem())
                        .append("fechaNacimiento", calendar.getDate())
                        .append("edad", Edad.getText()));

                UpdateResult result = coleccion.updateOne(filtro, documento);
                mostrarDatosTabla();
                if (result.getModifiedCount() > 0) {
                    JOptionPane.showMessageDialog(null, "Documento actualizado correctamente");
                } else {
                    JOptionPane.showConfirmDialog(null, "No se encontro el documento para actualizar");
                }
            } else {
                ListSelectionModel seleccionModel = tblDatos.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Seleccione el registro a Actualizar");
        }
        limpiar();
        Guardar.setVisible(true);
    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        if (filaSeleccionada >= 0) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "Seguro de eliminar los datos?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                DefaultTableModel modeloTabla = (DefaultTableModel) tblDatos.getModel();
                MongoCollection coleccion = database.getCollection("Registro");
                Document filtro = new Document("_id", new ObjectId(txtID.getText()));
                DeleteResult result = coleccion.deleteOne(filtro);

                if (result.getDeletedCount() > 0) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado Correctamente");
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro el registro para eliminarlo");

                }
            } else {
                ListSelectionModel seleccionModel = tblDatos.getSelectionModel();
                seleccionModel.clearSelection();
                filaSeleccionada = -1;

            }
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar");
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        mostrarDatosCampos();
        Guardar.setVisible(false);
    }//GEN-LAST:event_tblDatosMouseClicked

    private void calendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPropertyChange
        if("date".equals(evt.getPropertyName())&& calendar.getDate()!= null){
            int Anio = calendar.getCalendar().get(Calendar.YEAR);
            Date fecha= new Date();
            SimpleDateFormat Jun= new SimpleDateFormat("yyyy");
            String FechaSistema = Jun.format(fecha);
            int Ajio= Integer.parseInt(FechaSistema), resta;
            resta= (Ajio-Anio);
            String Edad1 = Integer.toString(resta);
            Edad.setText(Edad1);
        }
    }//GEN-LAST:event_calendarPropertyChange

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
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JComboBox<String> Carrera;
    private javax.swing.JTextField Edad;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JSpinner Nivel;
    private javax.swing.JTextField NombreEstudiante;
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel saf;
    private javax.swing.JTable tblDatos;
    private javax.swing.JLabel txtID;
    private javax.swing.JLabel txtNivel;
    // End of variables declaration//GEN-END:variables
}
