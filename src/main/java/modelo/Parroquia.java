/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Parroquia implements Serializable, Comparable<Parroquia>{
    private int idParroquia;
    private int idDireccion;
    private String nombreParroquia;
    private String direccion;

    public Parroquia() {
    }

    public Parroquia(int idDireccion, String nombreParroquia) {
        this.idDireccion = idDireccion;
        this.nombreParroquia = nombreParroquia;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNombreParroquia() {
        return nombreParroquia;
    }

    public void setNombreParroquia(String nombreParroquia) {
        this.nombreParroquia = nombreParroquia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Parroquia other = (Parroquia) obj;
        return idParroquia == other.idParroquia && Objects.equals(nombreParroquia, other.nombreParroquia);
    }

    /**
     * Genera el código hash para la instancia actual.
     *
     * @return el código hash generado
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombreParroquia);
    }

    @Override
    public String toString() {
        return nombreParroquia;
    }

    @Override
    public int compareTo(Parroquia o) {
        return nombreParroquia.compareTo(o.nombreParroquia);
    }
}
