/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import universidadgrupo76.AccesoADatos.AlumnoData;
import universidadgrupo76.AccesoADatos.Conexion;
import universidadgrupo76.entidades.Alumno;

/**
 *
 * @author Fernandez
 */
public class UniversidadGrupo76 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Alumno juan = new Alumno(12312312,"luna","pedro",LocalDate.of(1980,4,25),true);
        AlumnoData alu=new AlumnoData();
        alu.guardarAlumno(juan);
    }
    
}
