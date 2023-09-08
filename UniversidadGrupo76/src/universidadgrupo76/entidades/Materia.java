/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo76.entidades;

/**
 *
 * @author Fernandez
 */
public class Materia {
    private int idMateria;
    private String nombre;
    private int ano ;
    private boolean estado;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, int ano, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.ano = ano;
        this.estado = estado;
    }

    public Materia(String nombre, int ano, boolean estado) {
        this.nombre = nombre;
        this.ano = ano;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", ano=" + ano + ", estado=" + estado + '}';
    }
    
   
    
}
