/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;

import java.util.List;
import proyecto.espe.modelos.Pedido;


/**
 *
 * @author PC
 */
public interface IPedido {

    public List<Pedido> ListarPedidos();

    public Pedido BuscarCodigoPedido(String idpedido);

    public boolean InsertarPedido(Pedido pedido);

    public boolean ActualizarPedido(Pedido pedido);

    public boolean EliminarPedido(String idpedido);
}
