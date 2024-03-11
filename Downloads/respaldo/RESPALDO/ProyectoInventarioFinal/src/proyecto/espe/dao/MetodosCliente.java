/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.dao;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;
import proyecto.espe.modelos.Cliente;

/**
 *
 * @author vonch
 */
public class MetodosCliente implements ICliente {

    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection coleccionCliente;
    public List<Cliente> ListarCliente;

    public MetodosCliente() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionCliente = database.getCollection("cliente");

        }
    }

    private void cierreConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexion");
        }
    }

    @Override
    public List<Cliente> listaCliente() {
        List<Cliente> listaCliente = new ArrayList<>();
        FindIterable<Document> documentoCliente;
        
        try {
            documentoCliente = coleccionCliente.find();

            for (Document temp : documentoCliente) {
                Cliente cliente = new Cliente();
                cliente.setCedula(temp.getString("cedula"));
                cliente.setNombre(temp.getString("nombre"));
                cliente.setApellido(temp.getString("apellido"));
                cliente.setTelefono(temp.getString("telefono"));
                cliente.setDireccion(temp.getString("direccion"));

            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos " + ex.getMessage());
        } finally {
            cierreConexion();
        }
        return listaCliente();

    }

    @Override
    public Cliente BuscarCedulaCliente(String cedula) {
        Cliente cliente = null;
        Document filtro = null, resultado = null ;
        try {
            filtro = new Document("cedula", cedula);
            resultado = (Document) coleccionCliente.find(filtro).first();

            if (resultado != null) {
                cliente = new Cliente();
                cliente.setCedula(resultado.getString("cedula"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setTelefono(resultado.getString("telefono"));
                cliente.setDireccion(resultado.getString("direcion"));

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos segun id " + ex.getMessage());
        } finally {
            cierreConexion();
        }
        return cliente;
    }

    @Override
    public boolean InsertarCliente(Cliente cliente) {
        Document documento;
        try {
            documento = new Document("cedula", cliente.getCedula())
                    .append("nombre", cliente.getNombre())
                    .append("apellido", cliente.getApellido())
                    .append("telefono", cliente.getTelefono())
                    .append("direccion", cliente.getDireccion());
            coleccionCliente.insertOne(documento);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos : " + ex.toString());
        } finally {
            cierreConexion();
        }
        return true;
    }

    @Override
    public boolean ActualizarCliente(Cliente cliente) {
        Document filtro, update;
        UpdateResult resultado;
        boolean actualizar = false;
        try {
            filtro = new Document("cedula", cliente.getCedula());
            update = new Document("$set", new Document("nombre", cliente.getNombre())
                    .append("apellido", cliente.getApellido())
                    .append("telefono", cliente.getTelefono())
                    .append("direccion", cliente.getDireccion()));
            resultado = coleccionCliente.updateOne(filtro, update);

            if (resultado.getModifiedCount() > 0) {
                actualizar = true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos " + ex.getMessage());
        } finally {
            cierreConexion();
        }
        return actualizar;
    }

    @Override
    public boolean EliminarCliente(String cedula) {
        Bson filtro = null;
        DeleteResult result = null;
        boolean eliminar = false;

        try {
            filtro = new Document("cedula", cedula);
            result = coleccionCliente.deleteOne(filtro);
            if (result.getDeletedCount() > 0) {
                eliminar = true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el registro para eliminar");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos" + ex.getMessage());
        } finally {
            cierreConexion();
        }
        return eliminar;
    }

}
