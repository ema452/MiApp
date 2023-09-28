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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo76.entidades.Alumno;

/**
 *
 * @author Mati_ssd
 */
public class AlumnoData {

    public void guardarAlumno(Alumno alumno) throws SQLException {
        String sql = "INSERT INTO alumno(dni, apellido,nombre,fechaNacimiento,estado)"
                + "VALUES(?,?,?,?,?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "se inseto el alumno exitosamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }

    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?"
                + "WHERE idAlumno=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setInt(5, alumno.getIdAlumno());
            
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null,"Alumno modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }

    }
    
    public void eliminarAlumno(int id){
        String sql="UPDATE alumno SET estado = 0 WHERE idAlumno = ?";
        
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int exito=ps.executeUpdate();
            if(exito == 1){
            JOptionPane.showMessageDialog(null, "Alumno eliminado");
            }
        } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
      public Alumno buscarAlumno(int id){
          Alumno alumno=null;
          String sql= "SELECT dni, apellido,nombre,fechaNacimiento FROM alumno WHERE idAlumno = ? AND estado = 1 ";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if (rs.next()) {
                alumno= new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }else{
                 JOptionPane.showMessageDialog(null, "no existe un alumno con ese id");
            }
            ps.close();
            
        } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumno;
      }
      
      
       public Alumno buscarAlumnoPordni(int dni){
          Alumno alumno=null;
          String sql= "SELECT idAlumno, dni, apellido,nombre,fechaNacimiento FROM alumno WHERE dni = ? AND estado = 1 ";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs= ps.executeQuery();
            if (rs.next()) {
                alumno= new Alumno();
               // alumno.setIdAlumno(id);
               // alumno.setDni(rs.getInt("dni"));
                alumno.setDni(dni);
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            }else{
                 JOptionPane.showMessageDialog(null, "no existe un alumno con ese id");
            }
            ps.close();
            
        } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumno;
      }
       
        public List<Alumno> listarAlumnos(){
        
          String sql= "SELECT idAlumno, dni, apellido,nombre,fechaNacimiento FROM alumno WHERE estado = 1 ";
          ArrayList<Alumno> alumnos=new ArrayList<>();
          Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Alumno alumno= new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                
                alumnos.add(alumno);
            }
            ps.close();
            
        } catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumnos;
      }
        
      
       
}
