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
public class Sacerdote implements Serializable, Comparable<Sacerdote>{
    private int idSacerdote;
    private String rango;
    private String cedulaPersona;
    private String primerNombre;
    private String primerApellido;
    private String sacerdote;

    public Sacerdote() {
    }

    public Sacerdote(String rango, String cedulaPersona) {
        this.rango = rango;
        this.cedulaPersona = cedulaPersona;
    }

    public int getIdSacerdote() {
        return idSacerdote;
    }

    public void setIdSacerdote(int idSacerdote) {
        this.idSacerdote = idSacerdote;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSacerdote() {
        return sacerdote;
    }

    public void setSacerdote(String sacerdote) {
        this.sacerdote = sacerdote;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sacerdote other = (Sacerdote) obj;
        return idSacerdote == other.idSacerdote && Objects.equals(sacerdote, other.sacerdote);
    }

    /**
     * Genera el código hash para la instancia actual.
     *
     * @return el código hash generado
     */
    @Override
    public int hashCode() {
        return Objects.hash(sacerdote);
    }

    @Override
    public String toString() {
        return sacerdote;
    }

    @Override
    public int compareTo(Sacerdote o) {
        return sacerdote.compareTo(o.sacerdote);
    }
}
