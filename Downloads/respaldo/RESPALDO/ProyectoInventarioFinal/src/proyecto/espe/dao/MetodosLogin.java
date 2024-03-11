/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.dao;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bson.Document;
import proyecto.espe.modelos.Login;
import proyecto.espe.vistaGeneral.MenuAdmin;
import proyecto.espe.vistaGeneral.MenuEncargado;

/**
 *
 * @author PC
 */
public class MetodosLogin implements ILogin {

    Conexion conn = new Conexion();
    MongoDatabase database;
    private MongoCollection<Document> coleccionLogin;

    public MetodosLogin(MongoDatabase database, MongoCollection<Document> coleccion) {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionLogin = database.getCollection("login");
        }
    }

    public MetodosLogin() {
        if (conn != null) {
            this.conn = conn.crearConexion();
            this.database = conn.getDataB();
            this.coleccionLogin = database.getCollection("login");
        }
    }

    private void cierreConexion() {
        try {
            conn.getMongo().close();
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexion: " + ex.toString());

        }
    }

    @Override
    public boolean RegistrarPerfil(Login login) {
        Document documento;

        try {

            documento = new Document("id_login", login.getId_Login())
                    .append("nombreUsuario", login.getNombreUsuario())
                    .append("correo", login.getEmail())
                    .append("cargo", login.getUsuario())
                    .append("cedula", login.getPerfil())
                    .append("contrasenia", login.getContraseña());

            coleccionLogin.insertOne(documento);
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar datos: " + ex.toString());
            return false;
        } finally {
            cierreConexion();

        }
        return true;

    }

    @Override
    public boolean AutenticarLogin(Login login) {
        boolean validar = false;

        String contraseniaEncriptada = ClaveEncriptada(login.getContraseña());

        try {
            //String contraseniaEncriptada = ClaveEncriptada(login.getContraseña());

            Document query = new Document("correo", login.getEmail())
                    .append("contrasenia", contraseniaEncriptada);
            //FindIterable<Document> result = coleccionLogin.find(query);
            try (MongoCursor<Document> cursor = coleccionLogin.find(query).iterator()) {
                if (cursor.hasNext()) {

                    Document usuario = cursor.next();
                    int idPerfil = usuario.getInteger("id_login");
                    switch (idPerfil) {
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
                        case 3:
                            JOptionPane.showMessageDialog(null, "En un momento se le asignará un perfil");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Perfil no reconocido", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }

                }
                 return validar=true;

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al autenticar"+ex.toString());
            return validar = false;
        } finally {
            cierreConexion();
        }

        //return validar = true;

    }

    @Override
    public Login BuscarCedula(String cedula) {

        Login login = null;
        Document filtro = null, resultado = null, perfil = null;
        try {
            filtro = new Document("cedula", cedula);
            resultado = (Document) coleccionLogin.find(filtro).first();
            if (resultado != null) {
                login = new Login();
                login.setId_Login(resultado.getInteger("id_login"));
                login.setNombreUsuario(resultado.getString("nombreUsuario"));
                login.setEmail(resultado.getString("correo"));
                login.setUsuario(resultado.getString("cargo"));

            } else {
                JOptionPane.showMessageDialog(null, "Perfil No Encontrado");
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        } finally {
            cierreConexion();
        }

        return login;
    }

    @Override
    public boolean EliminarLogin(String cedula) {

        Document filtro = new Document("cedula", cedula);
        coleccionLogin = database.getCollection("login");
        try {
            DeleteResult result = coleccionLogin.deleteOne(filtro);
            if (result.getDeletedCount() > 0) {

            } else {
                return false;
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar" + ex.toString());
            return false;
        } finally {
            cierreConexion();
        }
        return true;

    }

    @Override
    public boolean ModificarLogin(Login login) {

        boolean actualizar = false;
        Document filtro = null, update = null;
        UpdateResult resultado = null;

        try {
            filtro = new Document("cedula", login.getPerfil());
            update = new Document("$set", new Document()
                    .append("id_perfil", login.getId_Login())
                    .append("nombreUsuario", login.getNombreUsuario())
                    .append("correo", login.getEmail())
                    .append("cargo", login.getUsuario())
            );
            resultado = coleccionLogin.updateOne(filtro, update);
            if (resultado.getModifiedCount() > 0) {

                actualizar = true;
            } else {
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
    public List<Login> listarDatos() {

        List<Login> ListaDatos = new ArrayList<>();
        FindIterable<Document> documentos = coleccionLogin.find();
        try {
            for (Document documento : documentos) {
                Login perfil = new Login();
                perfil.setId_Login(documento.getInteger("id_login"));
                perfil.setNombreUsuario(documento.getString("nombreUsuario"));
                perfil.setEmail(documento.getString("correo"));
                perfil.setUsuario(documento.getString("cargo"));

                ListaDatos.add(perfil);
            }
        } catch (MongoException ex) {
            JOptionPane.showMessageDialog(null, "Error en la consulta");
        } finally {
            cierreConexion();
        }
        return ListaDatos;

    }

}
