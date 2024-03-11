/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Login;


/**
 *
 * @author PC
 */
public interface ILogin {
    
    public List<Login> listarDatos();
    
    public boolean RegistrarPerfil(Login login);
    
    public boolean EliminarLogin(String cedula);
    
     public boolean ModificarLogin( Login login);
    
    public boolean AutenticarLogin(Login login);
    
    public Login BuscarCedula(String cedula);
    
    public String ClaveEncriptada(String contraseña);
    
    
    
    
    
}
