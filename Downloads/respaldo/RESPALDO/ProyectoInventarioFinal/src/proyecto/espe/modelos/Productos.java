/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.modelos;

/**
 *
 * @author USER
 */
public class Productos {
    
    String nombreProducto, precio,cantidad;
    int idcodigo;
    String codigo;

    public Productos() {
    }

    public Productos(String codigo, String cantidad, String precio, String nombreProducto, int idcodigo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.idcodigo = idcodigo;
    }

    public Productos(String codigo, String cantidad, String precio, int idcodigo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idcodigo = idcodigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getIdcodigo() {
        return idcodigo;
    }

    public void setIdcodigo(int idcodigo) {
        this.idcodigo = idcodigo;
    }
    
}
