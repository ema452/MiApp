/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JFrame;
import universidadgrupo76.AccesoADatos.AlumnoData;
import universidadgrupo76.AccesoADatos.Conexion;
import universidadgrupo76.AccesoADatos.InscripcionData;
import universidadgrupo76.AccesoADatos.MateriaData;
import universidadgrupo76.Vistas.VistaPrincipal;
import universidadgrupo76.entidades.Alumno;
import universidadgrupo76.entidades.Inscripcion;
import universidadgrupo76.entidades.Materia;

/**
 *
 * @author Fernandez
 */
public class UniversidadGrupo76 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        VistaPrincipal ventana = new VistaPrincipal();

        // Establecer el tamaño deseado
        int ancho = 800;
        int alto = 600;
        ventana.setSize(ancho, alto);

        // Obtener las dimensiones de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular las coordenadas X e Y para centrar la ventana en la pantalla
        int xPos = (screenSize.width - ancho) / 2;
        int yPos = (screenSize.height - alto) / 2;

        // Establecer la posición en el centro de la pantalla
        ventana.setLocation(xPos, yPos);

        // Hacer la ventana visible
        ventana.setVisible(true);
    }
    }
   

