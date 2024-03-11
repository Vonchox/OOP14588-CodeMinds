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

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;
import proyecto.espe.modelos.Productos;

/**
 *
 * @author USER
 */
public class MetodosProductos implements IProductos{
    
    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccionProductos;
    private MongoCollection<Document> coleccionInventario;

    public MetodosProductos() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionInventario = database.getCollection("inventario");
            this.coleccionProductos = database.getCollection("productos");
        }
    }

    private void cierreConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexión " + ex.toString());
        }
    }

    @Override
    public List<Productos> ListarProductos() {
        List<Productos> listaPersonas = new ArrayList<>();
        FindIterable<Document> documentoProductos;
        FindIterable<Document> documentoInventario;
        
        try{
            documentoInventario = coleccionInventario.find();
            documentoProductos = coleccionProductos.find();
            
            for(Document temp : documentoProductos ){
                Productos productos = new Productos();
                int idcodigo = temp.getInteger("idcodigo");
                Document inventario = (Document) coleccionInventario.find(eq("idPerfil", idcodigo)).first();
                
                productos.setCodigo(temp.getString("codigo"));
                productos.setNombreProducto(temp.getString("nombreProducto"));
                productos.setPrecio(temp.getString("precio"));
                productos.setCantidad(temp.getString("cantidad"));
                productos.setIdcodigo(temp.getInteger("idcodigo"));
                listaPersonas.add(productos);
                
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido recuperar la información, error: " + ex.toString());
        }finally{
            cierreConexion();
        }
        return listaPersonas;        
    }

    @Override
    public Productos BuscarCodigoProductos(String codigo) {
        Productos productos = null;
        Document filtro = null, resultado = null, inventario = null;
        
        try{
            filtro = new Document("codigo", codigo);
            resultado = (Document) coleccionProductos.find(filtro).first();
            
            if(resultado != null){
                inventario = (Document) (Document) coleccionInventario.find(eq("idcodigo", resultado.getInteger("idcodigo"))).first();
                productos = new Productos();
                productos.setCodigo(resultado.getString("codigo"));
                productos.setNombreProducto(resultado.getString("nombreProducto"));
                productos.setPrecio(resultado.getString("precio"));
                productos.setCantidad(resultado.getString("cantidad"));
                productos.setIdcodigo(resultado.getInteger("idcodigo"));
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido realizar la búsqueda, error: " + ex.toString());
        }finally{
            cierreConexion();
        }
        return productos;
    }

    @Override
    public boolean InsertarProductos(Productos productos) {
        Document documento;
        try{
            documento = new Document("codigo", productos.getCodigo())
                    .append("nombreProducto",productos.getNombreProducto())
                    .append("precio",productos.getPrecio())
                    .append("cantidad",productos.getCantidad())
                    .append("idcodigo", productos.getIdcodigo());
            coleccionProductos.insertOne(documento);
            return true;
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido ingresar el registro, error: " + ex.toString());
            return false;
        }finally{
            cierreConexion();
        }
    }

    @Override
    public boolean ActualizarProductos(Productos productos) {
        Document filtro, update;
        UpdateResult resultado;
        boolean actualizar = false;
        
        try{
            filtro = new Document("codigo", productos.getCodigo());
            update = new Document("$set",new Document("nombreProducto",productos.getNombreProducto())
                    .append("precio",productos.getPrecio())
                    .append("cantidad",productos.getCantidad())
                    .append("idcodigo", productos.getIdcodigo()));
            resultado = coleccionProductos.updateOne(filtro,update);
            if(resultado.getModifiedCount() > 0){
                actualizar = true;
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido ingresar el registro, error: " + ex.toString());
            actualizar = false;
        }finally{
            cierreConexion();
        }
        
        return actualizar;
    }

    @Override
    public boolean EliminarProductos(String codigo) {
        Bson filtro = null;
        DeleteResult resultado = null;
        boolean eliminar = false;
        
        try{
            filtro = new Document("codigo", codigo);
            resultado = coleccionProductos.deleteOne(filtro);
            if(resultado.getDeletedCount() > 0){
                eliminar = true;
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido eliminar el registro, error: " + ex.toString());
            eliminar = false;
        }finally{
            cierreConexion();
        }
        return eliminar;
    }
    
}
