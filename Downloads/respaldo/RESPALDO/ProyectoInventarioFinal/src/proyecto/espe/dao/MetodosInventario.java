/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.dao;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import proyecto.espe.modelos.Inventario;

/**
 *
 * @author USER
 */
public class MetodosInventario implements IInventario {

    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccion;

    /**
     * Constructor de la clase MetodosPerfil que establece la conexión y obtiene
     * la colección "perfil".
     */
    public MetodosInventario() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccion = database.getCollection("inventario");
        }
    }

    private void cerrarConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.toString());
        }
    }

    @Override
    public List<Inventario> ListarInventario() {
        List<Inventario> listainventario = new ArrayList<>();
        FindIterable<Document> documentos;

        try {
            documentos = coleccion.find();
            for (Document temp : documentos) {
                Inventario inventario = new Inventario();
                inventario.setCodigo(temp.getInteger("id_codigo"));
                inventario.setProducto(temp.getString("producto"));

                listainventario.add(inventario);
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de datos: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }

        return listainventario;
    }

    @Override
    public boolean InsetarInventario(Inventario inventario) {
        Document documento;
        try {
            documento = new Document("id_codigo", inventario.getCodigo())
                    .append("producto", inventario.getProducto());

            coleccion.insertOne(documento);
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos: " + ex.toString());
            return false;
        } finally {
            cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean ActualizarInventario(Inventario inventario) {
        Document filtro = null;
        Document resultado = null;
        boolean actualizar = false;

        try {
            filtro = new Document("id_codigo", inventario.getCodigo());
            resultado = coleccion.find(filtro).first();

            if (resultado != null) {
                inventario.setCodigo(resultado.getInteger("id_codigo"));
                inventario.setProducto(resultado.getString("producto"));

                actualizar = true;
            }

        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar datos: " + ex.toString());
        } finally {
            cerrarConexion();
        }

        return actualizar;
    }

    @Override
    public boolean EliminarInventario(int idcodigo) {
        Document filtro = new Document("id_codigo", idcodigo);

        try {
            DeleteResult resultado = coleccion.deleteOne(filtro);
            return resultado.getDeletedCount() > 0;
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el perfil: " + ex.toString());
        } finally {
            cerrarConexion();
        }

        return false;
    }


    @Override
    public Inventario BuscarInvenatrio(int idcodigo) {
        Document filtro = new Document("id_codigo", idcodigo);
        Inventario inventario = new Inventario();
        Document documento = coleccion.find(filtro).first();
        inventario.setCodigo(documento.getInteger("id_codigo"));
        inventario.setProducto(documento.getString("producto"));

        return inventario;
    }

}
