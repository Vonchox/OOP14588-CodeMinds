/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.modelos;

import java.util.Date;

/**
 *
 * @author G306M11
 */
public class Empleado {

    private String cedula, nombre, apellido, email, telefono, direccion,cargo, usuario, clave;
    private int idPerfil;
    private String nombrePerfil;

    //constructor vacio
    public Empleado() {
    }

    //constructor consultar (no clave)
    public Empleado(String cedula, String nombre, String apellido,  String cargo , String email,
            String telefono, String direccion, String usuario, int idPerfil, String nombrePerfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo=cargo;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.idPerfil = idPerfil;
        this.nombrePerfil = nombrePerfil;
    }
    
    //constructor INSERTAR (NO nombre perfil)

    public Empleado(String cedula, String nombre, String apellido,String cargo,String email, 
            String telefono, String direccion, String usuario, String clave, int idPerfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.clave = clave;
        this.idPerfil = idPerfil;
    }
    
    //constructor ACTUALIZAR (no nombreperfil, ni clave)

    public Empleado(String cedula, String nombre, String apellido,String cargo, String email,
    String telefono, String direccion, String usuario,  int idPerfil) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.usuario = usuario;
        this.idPerfil = idPerfil;
    }
    
    //constructor AUTENTICAR

    public Empleado(String nombre, String clave, int idPerfil) {
        this.nombre = nombre;
        this.clave = clave;
        this.idPerfil = idPerfil;
    }
    
    //Constructor CONTRASEÑA

    public Empleado(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
    //getter and setter

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

   

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @Override
    public String toString() {
        return "Empleado{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + ", cargo=" + cargo + ", usuario=" + usuario + ", clave=" + clave + ", idPerfil=" + idPerfil + ", nombrePerfil=" + nombrePerfil + '}';
    }

  
    
    
    
    

}
