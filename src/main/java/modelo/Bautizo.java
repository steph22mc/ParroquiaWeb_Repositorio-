/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Bautizo {
    private int idBautizo;
    private Date fechaBautizo;
    private int idParroquia;
    private String nombreParroquia;
    private int idDireccion;
    private String direccion;
    private String idMadrina;
    private String madrina;
    private String idPadrino;
    private String padrino;
    private String cedulaPersona;
    private String persona;
    private int idSacerdote;
    private String sacerdote;

    public Bautizo() {
    }

    public Bautizo(Date fechaBautizo, int idParroquia, String nombreParroquia, int idDireccion, String direccion, String idMadrina, String madrina, String idPadrino, String padrino, String cedulaPersona, String persona, int idSacerdote, String sacerdote) {
        this.fechaBautizo = fechaBautizo;
        this.idParroquia = idParroquia;
        this.nombreParroquia = nombreParroquia;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.idMadrina = idMadrina;
        this.madrina = madrina;
        this.idPadrino = idPadrino;
        this.padrino = padrino;
        this.cedulaPersona = cedulaPersona;
        this.persona = persona;
        this.idSacerdote = idSacerdote;
        this.sacerdote = sacerdote;
    }

    public Bautizo(int i, String string, int i0, int i1, String madrina_1, String padrino_1, String string0, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdBautizo() {
        return idBautizo;
    }

    public void setIdBautizo(int idBautizo) {
        this.idBautizo = idBautizo;
    }

    public Date getFechaBautizo() {
        return fechaBautizo;
    }

    public void setFechaBautizo(Date fechaBautizo) {
        this.fechaBautizo = fechaBautizo;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getNombreParroquia() {
        return nombreParroquia;
    }

    public void setNombreParroquia(String nombreParroquia) {
        this.nombreParroquia = nombreParroquia;
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

    public String getIdMadrina() {
        return idMadrina;
    }

    public void setIdMadrina(String idMadrina) {
        this.idMadrina = idMadrina;
    }

    public String getMadrina() {
        return madrina;
    }

    public void setMadrina(String madrina) {
        this.madrina = madrina;
    }

    public String getIdPadrino() {
        return idPadrino;
    }

    public void setIdPadrino(String idPadrino) {
        this.idPadrino = idPadrino;
    }

    public String getPadrino() {
        return padrino;
    }

    public void setPadrino(String padrino) {
        this.padrino = padrino;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public int getIdSacerdote() {
        return idSacerdote;
    }

    public void setIdSacerdote(int idSacerdote) {
        this.idSacerdote = idSacerdote;
    }

    public String getSacerdote() {
        return sacerdote;
    }

    public void setSacerdote(String sacerdote) {
        this.sacerdote = sacerdote;
    }
    
    
}
