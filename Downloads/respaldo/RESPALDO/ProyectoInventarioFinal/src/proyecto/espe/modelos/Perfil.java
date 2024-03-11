/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.modelos;


public class Perfil {
    private int id_perfil;
    private String nombrePerfil;
    
    //CONSTRUCTOR VACIO

    public Perfil() {
    }
    //CONSTRUCTOR PARA INSERTAR

    public Perfil(int id_perfil, String nombrePerfil) {
        this.id_perfil = id_perfil;
        this.nombrePerfil = nombrePerfil;
        
    }
    //GETTER AND SETTER

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @Override
    public String toString() {
        return "Perfil{" + "id_perfil=" + id_perfil + ", nombrePerfil=" + nombrePerfil + '}';
    }

  

   
    
    
    
}
