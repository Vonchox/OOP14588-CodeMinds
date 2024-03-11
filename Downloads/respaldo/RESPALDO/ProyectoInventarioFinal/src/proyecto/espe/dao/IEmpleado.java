/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Empleado;

/**
 *
 * @author G306M11
 */
public interface IEmpleado {
    public List<Empleado> ListarEmpleado();
    public Empleado BuscarCedulaEmpleado(String cedula);
    public boolean InsertarEmpleado (Empleado empleado);
    public boolean ActualizarEmpleado (Empleado empleado);
    public boolean EliminarEmpleado (String cedula);
    public boolean ActualizarClave (Empleado empleado);
    
    public boolean AutenticarEmpleado(String usuario, String clave);
    
    public String ClaveEncriptada(String contraseña);
    public boolean ClaveDesencriptada(String contraseña, String contraseñaBase);
    
}
