/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosInventario;
import proyecto.espe.dao.MetodosPerfil;
import proyecto.espe.modelos.Inventario;

/**
 *
 * @author USER
 */
public class InventarioServicio {
   public static List<Inventario> ListaInventario(){
       return new MetodosInventario().ListarInventario();
       
   }
   public static boolean InsertarInventario(Inventario inventario){
       return new MetodosInventario().InsetarInventario(inventario);
   } 
   public static boolean ActualizarInventario(Inventario inventario){
       return new MetodosInventario().ActualizarInventario(inventario);
   }
   public static boolean EliminarInventario(int idInventario){
       return new MetodosInventario().EliminarInventario(idInventario);
   }
   public static Inventario BuscarInventario(int idInventario){
       return new MetodosInventario().BuscarInvenatrio(idInventario);
   }
}
