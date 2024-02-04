/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.ec;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;

/**
 *
 * @author nandi
 */
public class Conexion {
    private MongoClient mongo;
    private MongoDatabase dataB;

    public MongoClient getMongo() {
        return mongo;
    }

    public MongoDatabase getDataB() {
        return dataB;
    }

    public Conexion() {
    }

    public Conexion(MongoClient mongoClient, MongoDatabase database) {
        this.mongo = mongoClient;
        this.dataB = database;
    }
    public Conexion crearConexion(){
        String servidor ="localhost";
        int puerto =27017;
        try{
            mongo=new MongoClient(servidor, puerto);
            dataB= mongo.getDatabase("Proyecto");
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        }catch(MongoException ex){
            JOptionPane.showConfirmDialog(null, "Error en la conexion a MongoDB"+ex.toString());
            
        }
        return new Conexion(mongo,dataB);
        }
    
}
