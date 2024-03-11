/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Cliente;

/**
 *
 * @author PC
 */
public interface ICliente {

    public List<Cliente> listaCliente();

    public Cliente BuscarCedulaCliente(String cedula);

    public boolean InsertarCliente(Cliente cliente);

    public boolean ActualizarCliente(Cliente cliente);

    public boolean EliminarCliente(String cedula);
}
