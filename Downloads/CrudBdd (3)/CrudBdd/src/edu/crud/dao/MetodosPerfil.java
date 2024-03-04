package edu.crud.dao;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import edu.crud.modelo.Perfil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

/**
 * Clase que implementa la interfaz IPerfil con métodos para operaciones CRUD en la colección "perfil".
 * @author Stiven Días, Juan Ortega
 */
public class MetodosPerfil implements IPerfil {

    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccion;

    /**
     * Constructor de la clase MetodosPerfil que establece la conexión y obtiene la colección "perfil".
     */
    public MetodosPerfil() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccion = database.getCollection("perfil");
        }
    }

    private void cerrarConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.toString());
        }
    }

    /**
     * Lee toda la lista de la colección perfil.
     * @return Una lista de perfiles.
     */
    @Override
    public List<Perfil> ListarPerfil() {
        List<Perfil> listaPerfil = new ArrayList<>();
        FindIterable<Document> documentos;

        try {
            documentos = coleccion.find();
            for (Document temp : documentos) {
                Perfil perfil = new Perfil();
                perfil.setId_perfil(temp.getInteger("id_perfil"));
                perfil.setNombrePerfil(temp.getString("nombrePerfil"));
                perfil.setDescripcion(temp.getString("descripcion"));
                listaPerfil.add(perfil);
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de datos: " + ex.getMessage());
        } finally {
            cerrarConexion();
        }

        return listaPerfil;
    }

    /**
     * "En este método, se busca un filtro dentro de la colección para encontrar un ID relacionado al que se requiere y luego retorna dicho ID.".
     * @param idPerfil ID del perfil a buscar.
     * @return El perfil encontrado o null si no existe.
     */
    @Override
    public Perfil BuscarIdPerfil(int idPerfil) {
        Perfil perfil = null;
        Document filtro = null;
        Document resultado = null;

        try {
            filtro = new Document("id_perfil", idPerfil);
            resultado = coleccion.find(filtro).first();

            if (resultado != null) {
                perfil = new Perfil();
                perfil.setId_perfil(resultado.getInteger("id_perfil"));
                perfil.setNombrePerfil(resultado.getString("nombrePerfil"));
                perfil.setDescripcion(resultado.getString("descripcion"));
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el perfil con ID " + idPerfil + ": " + ex.getMessage());
        } finally {
            cerrarConexion();
        }

        return perfil;
    }

    /**
     * "En este método, se realiza una inserción en la base de datos mediante un objeto llamado 'Perfil' Posteriormente, se crea un documento en la base de datos para generar un nuevo registro."
     * @param perfil El perfil a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    @Override
    public boolean InsetarPerfil(Perfil perfil) {
        Document documento;
        try {
            documento = new Document("id_perfil", perfil.getId_perfil())
                    .append("nombrePerfil", perfil.getNombrePerfil())
                    .append("descripcion", perfil.getDescripcion());

            coleccion.insertOne(documento);
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos: " + ex.toString());
            return false;
        } finally {
            cerrarConexion();
        }
        return true;
    }

    /**
     * Para actualizar un perfil existente, primero se llama el método 'buscar' para encontrar el ID que se desea actualizar, Si el resultado de la búsqueda es verdadero, entonces se procede a actualizar según los requisitos establecidos.
     * @param  perfil El perfil con los nuevos datos.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    @Override
public boolean ActualizarPerfil(Perfil perfil) {
    Document filtro = null;
    Document resultado = null;
    boolean actualizar = false;

    try {
        filtro = new Document("id_perfil", perfil.getId_perfil());
        resultado = coleccion.find(filtro).first();

        if (resultado != null) {
            perfil.setId_perfil(resultado.getInteger("id_perfil"));
            perfil.setNombrePerfil(resultado.getString("nombrePerfil"));
            perfil.setDescripcion(resultado.getString("descripcion"));
            actualizar = true;
        }

    } catch (MongoException ex) {
        JOptionPane.showMessageDialog(null, "Error al actualizar datos: " + ex.toString());
    } finally {
        cerrarConexion();
    }

    return actualizar;
}


    /**
     * Para eliminar, se llama al método 'buscar' para encontrar el ID que se desea eliminar, Si se encuentra el ID, entonces se procede a eliminar el registro dentro de la colección
     * @param idPerfil ID del perfil a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean EliminarPerfil(int idPerfil) {
        Document filtro = new Document("id_perfil", idPerfil);

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

   
}
