/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo76.entidades.Materia;

/**
 *
 * @author Mati_ssd
 */
public class MateriaData {

    public void guardarMateria(Materia materia) {

        String sql = "INSERT INTO materia (nombre,ano,estado) VALUES(?,?,?)";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAno());
            ps.setBoolean(3, materia.isEstado());
            ps.executeQuery();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "se inseto la materia correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Materia");
        }
    }

    public Materia buscarMateria(int id) {
        Materia materia = null;
        String sql = "SELECT nombre, ano,estado FROM materia WHERE idMateria = ? AND estado = 1 ";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAno(rs.getInt("ano"));
                materia.setEstado(true);;
            } else {
                JOptionPane.showMessageDialog(null, "No exite ninguna materia con ese codigo");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Materia");
        }
        return materia;

    }

    /*public void ModificarMateria(Materia materia) {
        String sql = "UPDATE materia SET nombre=?, ano=? ,estado=? WHERE idMateria=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAno());
           //  ps.setBoolean(3,materia.isEstado());
            ps.setInt(3, materia.getIdMateria());
            
             int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null,"Alumno modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");

        }
    }
*/
    
     public void modificarmateria(Materia materia) {
        String sql = "UPDATE materia SET nombre=?, ano=?, estado=? "
                + "WHERE idMateria=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getAno());
                ps.setBoolean(3, materia.isEstado());
                ps.setInt(4,materia.getIdMateria() );
            
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null,"Materia  modificada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");

        }

    }
     public void EliminadoLogiMateria(int id) {
        String sql = "UPDATE materia SET estado=0 "
                + "WHERE idMateria=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                ps.setInt(1, id);
              
            
            int exito = ps.executeUpdate();
         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia");

        }

    }
     
     public List<Materia> listarMateria(){
         String sql="SELECT idMateria, nombre, ano, estado FROM materia WHERE 1;";
         ArrayList<Materia> materias=new ArrayList<>();
         Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs= ps.executeQuery();
               while(rs.next()){
                Materia materia= new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAno(rs.getInt("ano"));
                materia.setEstado(true);
                
                materias.add(materia);
            }
            ps.close();
            
        }catch (SQLException ex) {
     JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        } 
        return materias;
     
     }
}
