/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Inventario;

/**
 *
 * @author USER
 */
public interface IInventario {
    public List<Inventario> ListarInventario();
    public boolean InsetarInventario(Inventario inventario);
    public boolean ActualizarInventario(Inventario inventario);
    public boolean EliminarInventario(int idcodigo);
    public Inventario BuscarIdInvenatrio(int idcodigo);
}
