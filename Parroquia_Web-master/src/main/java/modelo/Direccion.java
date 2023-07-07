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
public class Direccion implements Serializable, Comparable<Direccion> {
    private int idDireccion;
    private String direccion;
    private String nombreLugar;
    private String provincia;
    private String distrito;

    public Direccion() {
    }

    /**
     * Constructor de la clase Direccion.
     *
     * @param direccion   la dirección
     * @param nombreLugar el nombre del lugar
     * @param provincia   la provincia
     * @param distrito    el distrito
     */
    public Direccion(String direccion, String nombreLugar, String provincia, String distrito) {
        this.direccion = direccion;
        this.nombreLugar = nombreLugar;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    /**
     * Compara si la instancia actual es igual al objeto proporcionado.
     *
     * @param obj el objeto a comparar
     * @return true si son iguales, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Direccion other = (Direccion) obj;
        return idDireccion == other.idDireccion && Objects.equals(direccion, other.direccion);
    }

    /**
     * Genera el código hash para la instancia actual.
     *
     * @return el código hash generado
     */
    @Override
    public int hashCode() {
        return Objects.hash(idDireccion);
    }

    /**
     * Devuelve una representación en forma de cadena de la dirección.
     *
     * @return la representación en forma de cadena
     */
    @Override
    public String toString() {
        return direccion;
    }

    /**
     * Compara la dirección actual con otra dirección dada.
     *
     * @param o la dirección a comparar
     * @return un valor negativo si la dirección actual es menor que la dirección dada,
     *         cero si son iguales, o un valor positivo si la dirección actual es mayor
     */
    @Override
    public int compareTo(Direccion o) {
        return direccion.compareTo(o.direccion);
    }
}