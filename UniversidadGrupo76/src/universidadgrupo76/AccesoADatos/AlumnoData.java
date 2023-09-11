/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76.AccesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo76.entidades.Alumno;

/**
 *
 * @author Mati_ssd
 */
public class AlumnoData {
  
    public void guardarAlumno(Alumno alumno) throws SQLException{
        String sql="INSERT INTO alumno(dni, apellido,nombre,fechaNacimiento,estado)"
                + "VALUES(?,?,?,?,?)";
     try(Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
        ps.setInt(1, alumno.getDni());
        ps.setString(2, alumno.getApellido());
        ps.setString(3, alumno.getNombre());
        ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
        ps.setBoolean(5, alumno.isEstado());
        ps.executeUpdate();
        
        ResultSet rs= ps.getGeneratedKeys();
         if (rs.next()) {
             alumno.setIdAlumno(rs.getInt(1));
             JOptionPane.showMessageDialog(null, "se inseto el alumno exitosamente");
         }
         ps.close();
      }catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
      } 
    }
}
