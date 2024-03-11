/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import proyecto.espe.dao.MetodosLogin;
import proyecto.espe.modelos.Login;

/**
 *
 * @author PC
 */
public class LoginServicio {
    
    public static boolean RegistrarLogin( Login login){
        return new MetodosLogin().RegistrarPerfil(login);
    }
    
     public static boolean EliminarLogin( String cedula){
        return new MetodosLogin().EliminarLogin(cedula);
    }
     
     public static boolean ModificarLogin( Login login){
     return new MetodosLogin().ModificarLogin(login);
    }
     
     public static boolean AutenticarLogin( Login login){
      return new MetodosLogin().AutenticarLogin(login);
    }
     
    public static String claveEncriptada(String clave){
        return new MetodosLogin().ClaveEncriptada(clave);
    }
    
}
