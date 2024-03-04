/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosCliente;
import proyecto.espe.modelos.Cliente;

/**
 *
 * @author PC
 */
public class ClienteServicio {

    public static List<Cliente> ListaCliente() {
        return new MetodosCliente().listaCliente();
    }

    public static Cliente BuscarCedulaCliente(String cedula) {
        return new MetodosCliente().BuscarCedulaCliente(cedula);
    }

    public static boolean insertarCliente(Cliente cliente) {
        return new MetodosCliente().InsertarCliente(cliente);
    }

    public static boolean ActualizarCliente(Cliente cliente) {
        return new MetodosCliente().ActualizarCliente(cliente);
    }

    public static boolean EliminarCliente(String cedula) {
        return new MetodosCliente().EliminarCliente(cedula);
    }


}
