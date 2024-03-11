/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Productos;

/**
 *
 * @author USER
 */
public interface IProductos {
    public List<Productos> ListarProductos();
    public Productos BuscarCodigoProductos(String codigo);
    public boolean InsertarProductos(Productos productos);
    public boolean ActualizarProductos(Productos productos);
    public boolean EliminarProductos(String codigo);
    
}
