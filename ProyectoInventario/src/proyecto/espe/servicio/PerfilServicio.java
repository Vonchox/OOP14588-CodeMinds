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
       public static List<Perfil> ListaPerfil(){
       return new MetodosPerfil().ListarPerfil();
       
   }
   public static boolean insertarPerfil(Perfil perfil){
       return new MetodosPerfil().InsetarPerfil(perfil);
   } 
   public static boolean ActualizarPerfil(Perfil perfil){
       return new MetodosPerfil().ActualizarPerfil(  perfil);
   }
   public static boolean EliminarPerfil(int idPerfil){
       return new MetodosPerfil().EliminarPerfil( idPerfil);
   }
   public Perfil BuscarIdPerfil(int idPerfil){
       return new MetodosPerfil().BuscarIdPerfil(idPerfil);
   }
}
