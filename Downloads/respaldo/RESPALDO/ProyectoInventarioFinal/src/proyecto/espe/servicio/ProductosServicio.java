/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosProductos;
import proyecto.espe.modelos.Productos;

/**
 *
 * @author USER
 */
public class ProductosServicio {
    
    public static List<Productos> ListarProductos() {
        return new MetodosProductos().ListarProductos();
    }

    public static boolean InsertarProductos(Productos productos) {
        return new MetodosProductos().InsertarProductos(productos);
    }

    public static boolean ActualizarProductos(Productos productos) {
        return new MetodosProductos().ActualizarProductos(productos);
    }

    public static boolean EliminarProductos(String codigo) {
        return new MetodosProductos().EliminarProductos(codigo);
    }

    public static Productos BuscarCodigoProductos(String codigo) {
        return new MetodosProductos().BuscarCodigoProductos(codigo);
    }
}
