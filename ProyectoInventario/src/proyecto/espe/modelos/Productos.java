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
    int codigo, cantidad;
    double precio;
    String nombreProducto;
    int idcodigo;

    public Productos() {
    }

    public Productos(int codigo, int cantidad, double precio, String nombreProducto, int idcodigo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
        this.idcodigo = idcodigo;
    }

    public Productos(int codigo, int cantidad, double precio, int idcodigo) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idcodigo = idcodigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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
