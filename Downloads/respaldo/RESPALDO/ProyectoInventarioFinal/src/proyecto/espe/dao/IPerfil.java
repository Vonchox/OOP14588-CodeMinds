/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto.espe.dao;


import java.util.List;
import proyecto.espe.modelos.Perfil;

/**
 *
 * @author G306M11
 */
public interface IPerfil {

    public List<Perfil> ListarPerfil();

    public boolean InsertarPerfil(Perfil perfil);

    public boolean ActualizarPerfil(Perfil perfil);

    public boolean EliminarPerfil(int idPerfil);
    
    public Perfil BuscarIdPerfil (int idPerfil);
}
