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
import proyecto.espe.modelos.Pedido;


/**
 *
 * @author USER
 */
public class MetodosPedido implements IPedido{
    
    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccionClientes;
    private MongoCollection<Document> coleccionProductos;
    private MongoCollection<Document> coleccionPedido;

    public MetodosPedido() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionClientes = database.getCollection("cliente");
            this.coleccionProductos = database.getCollection("productos");
            this.coleccionPedido = database.getCollection("pedido");
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
    public List<Pedido> ListarPedidos() {
        List<Pedido> listaPedido = new ArrayList<>();
        FindIterable<Document> documentoProductos;
        FindIterable<Document> documentoCliente;
        FindIterable<Document> documentoPedido;
        
        try{
            documentoCliente = coleccionClientes.find();
            documentoProductos = coleccionProductos.find();
            documentoPedido = coleccionPedido.find();
            
            for(Document temp : documentoPedido ){
                Pedido pedidos = new Pedido();
                int idcodigo = temp.getInteger("idcodigo");
                int idcliente = temp.getInteger("idcliente");
                Document producto = (Document) coleccionProductos.find(eq("idcodigo", idcodigo)).first();
                Document cliente = (Document) coleccionClientes.find(eq("idcliente", idcliente)).first();
                
                pedidos.setIdPedido(temp.getString("idpedido"));
                pedidos.setNombreCliente(temp.getString("nombreCliente"));
                pedidos.setNombreProducto(temp.getString("nombreProducto"));
                pedidos.setCantidad(temp.getString("cantidad"));
                pedidos.setPrecioProducto(temp.getString("precioProducto"));
                pedidos.setIdcliente(temp.getInteger("idcliente"));
                pedidos.setIdcodigo(temp.getInteger("idcodigo"));
                
                listaPedido.add(pedidos);
                
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido recuperar la información, error: " + ex.toString());
        }finally{
            cierreConexion();
        }
        return listaPedido;        
    }

    @Override
    public Pedido BuscarCodigoPedido(String idpedido) {
        Pedido pedidos = null;
        Document filtro = null, resultado = null, productos = null,clientes = null;
        
        try{
            filtro = new Document("idpedido", idpedido);
            resultado = (Document) coleccionPedido.find(filtro).first();
            
            if(resultado != null){
                productos = (Document) (Document) coleccionProductos.find(eq("idcodigo", resultado.getInteger("idcodigo"))).first();
                clientes = (Document) (Document) coleccionClientes.find(eq("idcliente", resultado.getInteger("idcliente"))).first();
                pedidos = new Pedido();
                pedidos.setIdPedido(resultado.getString("idpedido"));
                pedidos.setNombreCliente(resultado.getString("nombreCliente"));
                pedidos.setNombreProducto(resultado.getString("nombreProducto"));
                pedidos.setCantidad(resultado.getString("cantidad"));
                pedidos.setPrecioProducto(resultado.getString("precioProducto"));
                pedidos.setIdcliente(resultado.getInteger("idcliente"));
                pedidos.setIdcodigo(resultado.getInteger("idcodigo"));
            }
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido realizar la búsqueda, error: " + ex.toString());
        }finally{
            cierreConexion();
        }
        return pedidos;
    }

    @Override
    public boolean InsertarPedido(Pedido pedido) {
        Document documento;
        try{
            documento = new Document("idpedido", pedido.getIdPedido())
                    .append("nombreCliente", pedido.getNombreCliente())
                    .append("nombreProducto",pedido.getNombreProducto())
                    .append("cantidad",pedido.getCantidad())
                    .append("precioProducto",pedido.getPrecioProducto())
                    .append("idcliente", pedido.getIdcliente())
                    .append("idcodigo", pedido.getIdPedido());
            
            coleccionPedido.insertOne(documento);
            return true;
        }catch(MongoException ex){
            JOptionPane.showMessageDialog(null,"No se ha podido ingresar el registro, error: " + ex.toString());
            return false;
        }finally{
            cierreConexion();
        }
    }

    @Override
    public boolean ActualizarPedido(Pedido pedido) {
        Document filtro, update;
        UpdateResult resultado;
        boolean actualizar = false;
        
        try{
            filtro = new Document("idpedido", pedido.getIdPedido());
            update = new Document("$set",new Document("nombreCliente",pedido.getNombreCliente())
                    .append("nombreProducto",pedido.getNombreProducto())
                    .append("cantidad",pedido.getCantidad())
                    .append("precioProducto",pedido.getPrecioProducto())
                    .append("idcliente", pedido.getIdcliente())
                    .append("idcodigo", pedido.getIdPedido()));
            resultado = coleccionPedido.updateOne(filtro,update);
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
    public boolean EliminarPedido(String idpedido) {
        Bson filtro = null;
        DeleteResult resultado = null;
        boolean eliminar = false;
        
        try{
            filtro = new Document("idpedido", idpedido);
            resultado = coleccionPedido.deleteOne(filtro);
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
