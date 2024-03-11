/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosEmpleado;
import proyecto.espe.modelos.Empleado;

/**
 *
 * @author PC
 */
public class EmpleadoServicio {
     public static List<Empleado> ListarEmpleado(){
        return new MetodosEmpleado().ListarEmpleado();
    }
    
    public static Empleado BuscarCedulaEmpleado(String cedula){
        return new MetodosEmpleado().BuscarCedulaEmpleado(cedula);
    }
    
    public static boolean InsertarEmpleado(Empleado empleado){
      return new MetodosEmpleado().InsertarEmpleado(empleado);
    }
    
    public static boolean ActualizarEmpleado(Empleado empleado){
      return new MetodosEmpleado().ActualizarEmpleado(empleado);
    }
    
    public static boolean EliminarEmpleado(String cedula){
      return new MetodosEmpleado().EliminarEmpleado(cedula); 
    }
    
    public static boolean AutenticarEmpleado(String usuarios, String clave){
      return new MetodosEmpleado().AutenticarEmpleado(usuarios, clave);
    }
    
    public static boolean ActualizarClave(Empleado empleado){
      return new MetodosEmpleado().ActualizarClave(empleado);
    }
    
    public static String ClaveEncriptada(String clave){
        return new MetodosEmpleado().ClaveEncriptada(clave);
    }
}
