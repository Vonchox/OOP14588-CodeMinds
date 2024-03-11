/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosPerfil;
import proyecto.espe.modelos.Perfil;

/**
 *
 * @author PC
 */
public class PerfilServicio {
     //COMUNICACION ENTRE USUARIO Y LOGICA DEL PROGRAMA
    public static List <Perfil> ListarPerfil(){
        return new MetodosPerfil().ListarPerfil();
    }
    
    public static boolean InsertarPerfil(Perfil perfil){
        return new MetodosPerfil().InsertarPerfil(perfil);
    }
    
     public static boolean ActualizarPerfil(Perfil perfil){
        return new MetodosPerfil().ActualizarPerfil(perfil);
    }
     
      public static boolean EliminarPerfil(int id){
        return new MetodosPerfil().EliminarPerfil(id);
    }
      
      public static Perfil BuscarIdPerfil(int idPerfil){
          return new MetodosPerfil().BuscarIdPerfil(idPerfil);
      }
    
}
