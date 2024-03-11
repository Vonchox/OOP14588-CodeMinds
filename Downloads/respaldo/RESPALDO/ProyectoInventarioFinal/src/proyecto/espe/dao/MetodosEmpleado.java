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
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;
import proyecto.espe.modelos.Empleado;
import proyecto.espe.vistaGeneral.MenuAdmin;
import proyecto.espe.vistaGeneral.MenuEncargado;

public class MetodosEmpleado implements IEmpleado {

    //Atributos con datos de la coleccion
    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection coleccionEmpleado;
    private MongoCollection coleccionPerfil;

    //constructor para realizar la conexion con la BBD
    public MetodosEmpleado() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionPerfil = database.getCollection("perfil");
            this.coleccionEmpleado = database.getCollection("empleado");

        }
    }

    //cierre de la conexion 
    private void cierreConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexion: " + ex.toString());

        }
    }

    @Override
    public List<Empleado> ListarEmpleado() {
        List<Empleado> listaEmpleado = new ArrayList<>();
        FindIterable<Document> documentoEmpleado;
        FindIterable<Document> documentoPerfiles;
        try {
            documentoPerfiles = coleccionPerfil.find();
            documentoEmpleado = coleccionEmpleado.find();
            for (Document temp : documentoEmpleado) {
                Empleado empleado = new Empleado();
                int idPerfil = temp.getInteger("idPerfil");
                Document perfil = (Document) coleccionPerfil.find(eq("id_perfil", idPerfil)).first();
                empleado.setCedula(temp.getString("cedula"));
                empleado.setNombre(temp.getString("nombre"));
                empleado.setApellido(temp.getString("apellido"));
                empleado.setCargo(temp.getString("cargo"));
                empleado.setEmail(temp.getString("mail"));
                empleado.setTelefono(temp.getString("telefono"));
                empleado.setDireccion(temp.getString("direccion"));
                empleado.setUsuario(temp.getString("usuario"));
                empleado.setClave(temp.getString("clave"));
                empleado.setIdPerfil(temp.getInteger("idPerfil"));
                empleado.setNombrePerfil(perfil.getString("nombreperfil"));
                listaEmpleado.add(empleado);
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar los datos " + ex.getMessage());
        } finally {
            cierreConexion();
        }
        return listaEmpleado;
    }

    @Override
    public Empleado BuscarCedulaEmpleado(String cedula) {
        Empleado empleado = null;
        Document filtro = null, resultado = null, perfil = null;
        try {
            filtro = new Document("cedula", cedula);
            resultado = (Document) coleccionEmpleado.find(filtro).first();
            if (resultado != null) {
                perfil = (Document) coleccionPerfil.find(eq("id_perfil", resultado.getInteger("idPerfil"))).first();
                empleado = new Empleado();
                empleado.setCedula(resultado.getString("cedula"));
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                empleado.setCargo(resultado.getString("cargo"));
                empleado.setEmail(resultado.getString("mail"));
                empleado.setTelefono(resultado.getString("telefono"));
                empleado.setDireccion(resultado.getString("direccion"));
                empleado.setUsuario(resultado.getString("usuario"));
                empleado.setClave(resultado.getString("clave"));
                empleado.setIdPerfil(resultado.getInteger("idPerfil"));
                empleado.setNombrePerfil(perfil.getString("nombreperfil"));
            }

        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos segun ID" + ex.getMessage());
        } finally {
            cierreConexion();
        }

        return empleado;
    }

    @Override
    public boolean InsertarEmpleado(Empleado empleado) {
        
        Document documento;
        
        try {
            documento = new Document("cedula", empleado.getCedula())
                    .append("nombre", empleado.getNombre())
                    .append("apellido", empleado.getApellido())
                    .append("cargo", empleado.getCargo())
                    .append("mail", empleado.getEmail())
                    .append("telefono", empleado.getTelefono())
                    .append("direccion", empleado.getDireccion())
                    .append("usuario", empleado.getUsuario())
                    .append("clave", empleado.getClave())
                    .append("idPerfil", empleado.getIdPerfil());
            coleccionEmpleado.insertOne(documento);

        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos" + ex.toString());
        } finally {
            cierreConexion();
        }
        return true;
    }

    @Override
    public boolean ActualizarEmpleado(Empleado empleado) {
        Document filtro, update;
        UpdateResult resultado;
        boolean actualizar = false;
        try {
            filtro = new Document("cedula", empleado.getCedula());
            update = new Document("$set", new Document("nombre", empleado.getNombre())
                    .append("apellido", empleado.getApellido())
                    .append("fecha_nacimiento", empleado.getCargo())
                    .append("mail", empleado.getEmail())
                    .append("telefono", empleado.getTelefono())
                    .append("direccion", empleado.getDireccion())
                    .append("usuario", empleado.getUsuario())
                    .append("clave", empleado.getClave())
                    .append("idPerfil", empleado.getIdPerfil()));
            resultado = coleccionEmpleado.updateOne(filtro, update);
            if (resultado.getModifiedCount() > 0) {
                actualizar = true;
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos" + ex.getMessage());
            actualizar = false;
        } finally {
            cierreConexion();
        }

        return actualizar;
    }

    @Override
    public boolean EliminarEmpleado(String cedula) {
        Bson filtro = null;
        DeleteResult result = null;
        boolean eliminar = false;
        try {
            filtro = new Document("cedula", cedula);
            result = coleccionEmpleado.deleteOne(filtro);
            if (result.getDeletedCount() > 0) {
                eliminar = true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el registro para eliminar");
                eliminar = false;
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar datos segun ID" + ex.getMessage());

        } finally {
            cierreConexion();
        }
        return eliminar;
    }

    @Override
    public boolean ActualizarClave(Empleado empleado) {
        Document filtro, update;
        UpdateResult resultado;
        boolean actualizar = false;
        try {
            filtro = new Document("cedula", empleado.getCedula());
            update = new Document("$set", new Document("clave", empleado.getClave()));
            resultado = coleccionEmpleado.updateOne(filtro, update);
            if (resultado.getModifiedCount() > 0) {
                actualizar = true;
            }

        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar Datos" + ex.getMessage());
            actualizar = false;
        } finally {
            cierreConexion();
        }
        return actualizar;
    }

    @Override
    public boolean AutenticarEmpleado(String usuario, String clave_s) {
        boolean verificador = false;
        Document query = new Document("usuario", usuario).append("clave", clave_s);
        FindIterable<Document> result = coleccionEmpleado.find(query);
        if (result.iterator().hasNext()) {
            Document empleado = result.iterator().next();
            int id_perfil = empleado.getInteger("idPerfil");
            switch (id_perfil) {
                case 1:
                    MenuAdmin admin = new MenuAdmin();
                    admin.setVisible(true);
                    admin.setLocationRelativeTo(null);
                    break;
                case 2:
                    MenuEncargado encargado = new MenuEncargado();
                    encargado.setVisible(true);
                    encargado.setLocationRelativeTo(null);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Perfil no reconocido");
                    break;

            }

            verificador = true;
        }

        return verificador;
    }

    @Override
    public String ClaveEncriptada(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(contraseña.getBytes());
            byte[] digest = md.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al encriptar la clave: " + ex.toString());
            return null;
        } finally {
            cierreConexion();
        }
    }

    @Override
    public boolean ClaveDesencriptada(String contraseña, String contraseñaBase) {
        String passw_Hash= ClaveEncriptada(contraseña);
        return passw_Hash.equals(contraseñaBase);
    }

}
