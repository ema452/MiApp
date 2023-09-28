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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadgrupo76.entidades.Alumno;
import universidadgrupo76.entidades.Inscripcion;
import universidadgrupo76.entidades.Materia;

/**
 *
 * @author Mati_ssd
 */
public class InscripcionData {

    private AlumnoData ad = new AlumnoData();
    private MateriaData md = new MateriaData();

    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion(idAlumno,idMateria,nota) VALUES(?,?,?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, insc.getAlumno().getIdAlumno());
            ps.setInt(2, insc.getMateria().getIdMateria());
            ps.setDouble(3, insc.getNota());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "se inseto exitosamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }
    }

    public void modificarNota(int idAlumno, int idMateria, double nota) {
        String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "nota Actualizada");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }
    }

    public void eliminadoFisicoDeNota(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria=?  ";
        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "nota Borrada");

            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }

    }

    public List<Inscripcion> obtenerInscreipciones() throws SQLException {
        String sql = "SELECT idInscripcion, nota, idAlumno, idMateria FROM inscripcion WHERE 1;";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setAlumno(alu);
                inscripcion.setMateria(mat);
                inscripcion.setNota(rs.getDouble("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }

        return inscripciones;

    }

    public List<Inscripcion> obtenerInscreipcionesPorAlumnos(int idAlumno) throws SQLException {
        String sql = "SELECT idInscripcion, nota, idAlumno, idMateria FROM inscripcion WHERE idAlumno=?;";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                Alumno alu = ad.buscarAlumno(rs.getInt("idAlumno"));
                Materia mat = md.buscarMateria(rs.getInt("idMateria"));
                inscripcion.setAlumno(alu);
                inscripcion.setMateria(mat);
                inscripcion.setNota(rs.getDouble("nota"));
                inscripciones.add(inscripcion);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }

        return inscripciones;

    }

    public List<Materia> obtenerMateriasCursadas(int idAlumno) {

        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT inscripcion.idMateria , nombre,ano FROM inscripcion,materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno=?;";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getNString("nombre"));
                materia.setAno(rs.getInt("ano"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");

        }
        return materias;
    }
    public List<Materia> obtenernotaCursadas(int idAlumno) {

        ArrayList<Materia> materias = new ArrayList<>();

        String sql = "SELECT inscripcion.idMateria , nombre,nota FROM inscripcion,materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno=?;";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getNString("nombre"));
                materia.setAno(rs.getInt("nota"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");

        }
        return materias;
    }
    public List<Materia> obtenerMAteriasNoCursadas(int idAlumno){
                ArrayList<Materia> materias = new ArrayList<>();

       String sql = "SELECT * FROM materia WHERE estado=1 AND idMateria not in (SELECT idMateria FROM inscripcion WHERE idAlumno=? )";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getNString("nombre"));
                materia.setAno(rs.getInt("ano"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }
        return materias;
}
    
     public List<Alumno> obtenerAlumnosXMateria(int idMateria){
            ArrayList<Alumno> alumnos=new ArrayList<>();
            String sql = "SELECT a.idAlumno, dni,nombre,apellido ,fechaNacimiento,estado FROM inscripcion i,alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado=1;";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno=new Alumno();
               alumno.setIdAlumno(rs.getInt("idAlumno"));
               alumno.setDni(rs.getInt("dni"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());;
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }
            
            
            
        return alumnos;
       }
     public List<Alumno> obtenerAlumnosXMateria1(){
            ArrayList<Alumno> alumnos=new ArrayList<>();
            String sql = "SELECT DISTINCT a.idAlumno, dni,nombre,apellido ,fechaNacimiento,estado FROM inscripcion i,alumno a WHERE i.idAlumno = a.idAlumno AND a.estado=1;";
        Connection conn = Conexion.getConexion();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno=new Alumno();
               alumno.setIdAlumno(rs.getInt("idAlumno"));
               alumno.setDni(rs.getInt("dni"));
               alumno.setNombre(rs.getString("nombre"));
               alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());;
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR al acceder a la tabla ionscripcion");
        }
            
            
            
        return alumnos;
       }

}
