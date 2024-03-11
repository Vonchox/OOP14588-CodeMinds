/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.modelos;

/**
 *
 * @author PC
 */
public class Login {
    private String email;
    private String contraseña;
    private String usuario;
    private int id_Login;
    private String NombreUsuario;
    private String Perfil;

    public Login() {
    }

    public Login(int id_Login,String NombreUsuario,String usuario,String email, String contraseña, String perfil ) {
        this.email = email;
        this.contraseña = contraseña;
        this.usuario = usuario;
        this.id_Login = id_Login;
        this.NombreUsuario = NombreUsuario;
        this.Perfil= perfil;
    }

    

    public Login(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId_Login() {
        return id_Login;
    }

    public void setId_Login(int id_Login) {
        this.id_Login = id_Login;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
    
    

    public String getPerfil() {
        return Perfil;
    }

    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

    
    
    

    @Override
    public String toString() {
        return "Login{" + "email=" + email + ", contrase\u00f1a=" + contraseña + ", usuario=" + usuario + 
                ", id_Login=" + id_Login + '}';
    }
    
    
    
    
    
}
