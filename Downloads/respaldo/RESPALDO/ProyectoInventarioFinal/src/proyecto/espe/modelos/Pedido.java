/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.modelos;

/**
 *
 * @author PC
 */
public class Pedido {
    String idPedido,cantidad;
    int idcliente;
    String nombreCliente;
    int idcodigo;
    String nombreProducto;
    String precioProducto;

    public Pedido() {
    }

    public Pedido(String idPedido, String cantidad, int idcliente, String nombreCliente, int idcodigo, String nombreProducto, String precioProducto) {
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.idcliente = idcliente;
        this.nombreCliente = nombreCliente;
        this.idcodigo = idcodigo;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdcodigo() {
        return idcodigo;
    }

    public void setIdcodigo(int idcodigo) {
        this.idcodigo = idcodigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }
    
    
}
