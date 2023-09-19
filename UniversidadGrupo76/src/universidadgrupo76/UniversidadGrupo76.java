/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import universidadgrupo76.AccesoADatos.AlumnoData;
import universidadgrupo76.AccesoADatos.Conexion;
import universidadgrupo76.AccesoADatos.InscripcionData;
import universidadgrupo76.AccesoADatos.MateriaData;
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
        //Connection con = Conexion.getConexion();
        // TODO code application logic here
       // Alumno juan = new Alumno(12,12312312,"Guzman","Gabriel",LocalDate.of(1980,4,25),true);
       // AlumnoData alu=new AlumnoData();
        //alu.guardarAlumno(juan);
        //alu.modificarAlumno(juan);
        //alu.eliminarAlumno(12);
        
        
      /*  Alumno alumnoEncontrado=alu.buscarAlumno(12);
       if(alumnoEncontrado!=null){
            System.out.println("Dni: "+ alumnoEncontrado.getDni());
            System.out.println("Apellido: "+ alumnoEncontrado.getApellido());
            System.out.println("Nombre: "+ alumnoEncontrado.getNombre());
            System.out.println("Fecha de Nacimiento: "+ alumnoEncontrado.getFechaNacimiento());
            
    }
        
         //Alumno alumnoEncontrado=alu.buscarAlumnoPordni(12312312);
         //if(alumnoEncontrado!=null){
           //System.out.println("Dni: "+ alumnoEncontrado.getDni());
                      System.out.println("el id del alumno es : "+ alumnoEncontrado.getIdAlumno());
           System.out.println("Apellido: "+ alumnoEncontrado.getApellido());
           System.out.println("Nombre: "+ alumnoEncontrado.getNombre());
           System.out.println("Fecha de Nacimiento: "+ alumnoEncontrado.getFechaNacimiento());
         }*/
     /*
                ESTE METODO ES PARA LISTAR TODOS LOS  ALUMNOS
     AlumnoData alu=new AlumnoData();
     for(Alumno alumno:alu.listarAlumnos()){
         System.out.println(alumno.getDni());
         System.out.println(alumno.getDni());
         System.out.println(alumno.getNombre());
         System.out.println(alumno.getIdAlumno());
     }
     */
     
     
    // Materia programacion = new Materia(13,"java",1,true);
     //MateriaData mata=new MateriaData();
  
    // mata.guardarMateria(programacion);
    // mata.modificarmateria(programacion);
    // mata.EliminadoLogiMateria(13);
    /*Materia materiaEncontrada=mata.buscarMateria(13);
    if(materiaEncontrada!=null){
        System.out.println("Nombre : " +materiaEncontrada.getNombre());
        System.out.println("AÑO:" + materiaEncontrada.getAno());
        System.out.println("Estado "+ materiaEncontrada.isEstado());
    }
      */
   
   //  ESTE METODO ES PARA LISTAR TODOS LAS MATERIAS
   
    /*MateriaData mata=new MateriaData();
        for (Materia materia:mata.listarMateria()) {
            System.out.println("idMateria: "+ materia.getIdMateria());
            System.out.println("Nombre: " + materia.getNombre());
            System.out.println("Año: "+ materia.getAno());
            System.out.println("Estado de la materia: " + materia.isEstado());
        }
   */
    //ESTE METODO SE ULILIZA PARA INSCRIBIR A UN ALUMNO EN UNA MATERIA 
    
    
    AlumnoData ad= new AlumnoData();
    MateriaData md= new MateriaData();
    InscripcionData id=new InscripcionData();
    
    Alumno gabriel=ad.buscarAlumno(12);
    Materia java=md.buscarMateria(13);
    Inscripcion insc=new Inscripcion(gabriel,java,8);
   
   
    //id.guardarInscripcion(insc); 
    //id.modificarNota(12, 13, 9);
   // id.eliminadoFisicoDeNota(12, 13);
   /*
            //metodo para listar toda las incripciones//
   for(Inscripcion inscripciones:id.obtenerInscreipciones()){
       System.out.println("idinscripciones: "+ inscripciones.getIdInscripcion());
       System.out.println("nota : "+ inscripciones.getNota());
       System.out.println("Apellido : " +inscripciones.getAlumno().getApellido() );
    System.out.println("Materia: "+ inscripciones.getMateria().getNombre());
       
     */  
   for(Alumno inscripciones:id.obtenerAlumnosXMateria(13)){
       System.out.println("id Alumno: "+ inscripciones.getIdAlumno());
       System.out.println("nombre : "+ inscripciones.getNombre());
       System.out.println("apellido : " +inscripciones.getApellido());
       System.out.println("dni: "+ inscripciones.getDni());
   System.out.println("fecha de nacimiento: "+ inscripciones.getFechaNacimiento());
       System.out.println("estado : "+ inscripciones.isEstado());
     
   }
    }
}
