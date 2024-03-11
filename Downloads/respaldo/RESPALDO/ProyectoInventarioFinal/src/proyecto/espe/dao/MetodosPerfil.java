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
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.types.ObjectId;
import proyecto.espe.modelos.Perfil;

/**
 *
 * @author G306M11
 */
public class MetodosPerfil implements IPerfil {

    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccion;

    public MetodosPerfil(MongoDatabase database, MongoCollection<Document> coleccion) {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccion = database.getCollection("perfil");
        }
    }

    public MetodosPerfil() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccion = database.getCollection("perfil");
        }
    }

    private void cierreConexion() {
        //IMPLEMENTAR TRY - CATCH
        //conn.close();// CREAR DESDE CONEXION
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la coneccion: " + ex.toString());
        }
    }

    @Override
    public List<Perfil> ListarPerfil() {

        List<Perfil> ListaPerfil = new ArrayList<>();
        FindIterable<Document> documentos = coleccion.find();
        try {
            for (Document documento : documentos) {
                Perfil perfil = new Perfil();
                perfil.setId_perfil(documento.getInteger("id_perfil"));
                perfil.setNombrePerfil(documento.getString("nombreperfil"));
                ListaPerfil.add(perfil);
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta");
        } finally {
            cierreConexion();
        }
        return ListaPerfil;

    }

    @Override
    public boolean InsertarPerfil(Perfil perfil) {
        Document documento;
        try {
            documento = new Document("id_perfil", perfil.getId_perfil())
                    .append("nombreperfil", perfil.getNombrePerfil());                 
            coleccion.insertOne(documento);
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos: " + ex.toString());
            return false;
        } finally {
            cierreConexion();
        }
        return true;

    }

    @Override
    public boolean ActualizarPerfil(Perfil perfil) {
        
        boolean actualizar=false;
        Document filtro=null, update=null;
        UpdateResult resultado=null;
        
        try {
            filtro = new Document("id_perfil", perfil.getId_perfil());
            update = new Document("$set", new Document()
                    .append("id_perfil", perfil.getId_perfil())
                    .append("nombreperfil", perfil.getNombrePerfil()));
          resultado = coleccion.updateOne(filtro, update);
           if(resultado.getModifiedCount()>0){
               
               actualizar=true;
           }else{
               return false;
           }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar datos: " + ex.toString());
            return false;
        } finally {
            cierreConexion();
        }
        return true;
    }

    @Override
    public boolean EliminarPerfil(int idPerfil) {
        Document filtro = new Document("id_perfil", idPerfil);
        coleccion = database.getCollection("perfil");
        try {
            DeleteResult result = coleccion.deleteOne(filtro);
            if (result.getDeletedCount() > 0) {

            } else {
                return false;
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar"+ex.toString());
            return false;
        } finally {
            cierreConexion();
        }
        return true;

    }

    @Override
    public Perfil BuscarIdPerfil(int idPerfil) {
        Perfil perfil= null;
        Document filtro= null;
        Document result= null;
     
        try{
            filtro= new Document("id_perfil",idPerfil);
            result = (Document)coleccion.find(filtro).first();
        if(result !=null){
            perfil= new Perfil();
            perfil.setId_perfil(result.getInteger("id_perfil"));
            perfil.setNombrePerfil(result.getString("nombreperfil"));

            
           
        }else{
            JOptionPane.showMessageDialog(null, "Perfil No Encontrado");
        }
        }catch (MongoException ex){
            JOptionPane.showMessageDialog(null, "Error"+ex.toString());
        }finally{
            cierreConexion();
        }
        
         
         
        return perfil;

    }

}
