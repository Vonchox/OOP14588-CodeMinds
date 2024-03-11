/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.servicio;

import java.util.List;
import proyecto.espe.dao.MetodosPedido;
import proyecto.espe.modelos.Pedido;



/**
 *
 * @author PC
 */
public class PedidoServicio {

    public static List<Pedido> ListarPedidos() {
        return new MetodosPedido().ListarPedidos();
    }

    public static boolean InsertarPedidos(Pedido pedidos) {
        return new MetodosPedido().InsertarPedido(pedidos);
    }

    public static boolean ActualizarPedidos(Pedido pedidos) {
        return new MetodosPedido().ActualizarPedido(pedidos);
    }

    public static boolean EliminarPedidos(String idPedido) {
        return new MetodosPedido().EliminarPedido(idPedido);
    }

    public static Pedido BuscarCodigoProductos(String idPedido) {
        return new MetodosPedido().BuscarCodigoPedido(idPedido);
    }
}
