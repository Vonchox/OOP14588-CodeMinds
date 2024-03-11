/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.espe.ec;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class AdaptarImagen {
    public void adaptarImagen(JLabel lb,String rutaImagen){
        lb.setIcon(new ImageIcon(new ImageIcon(getClass().getResource(rutaImagen)).getImage()
        .getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT)));
    }
}
