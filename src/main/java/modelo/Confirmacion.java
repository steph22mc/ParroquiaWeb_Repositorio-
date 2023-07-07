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
public class Confirmacion {
    private int idConfirmacion;
    private int idParroquia;
    private String nombre_parroquia;
    private String idPadrinoMadrina;
    private String padrinoMadrina;
    private Date fechaConfirmacion;
    private int idDireccion;
    private String direccion;
    private String idPadre;
    private String padre;
    private String idMadre;
    private String madre;
    private String cedulaPersona;
    private String persona;
    private int idSacerdote;
    private String sacerdote;

    public Confirmacion() {
    }

    public Confirmacion(int idParroquia, String nombre_parroquia, String idPadrinoMadrina, String padrinoMadrina, Date fechaConfirmacion, int idDireccion, String direccion, String idPadre, String padre, String idMadre, String madre, String cedulaPersona, String persona, int idSacerdote, String sacerdote) {
        this.idParroquia = idParroquia;
        this.nombre_parroquia = nombre_parroquia;
        this.idPadrinoMadrina = idPadrinoMadrina;
        this.padrinoMadrina = padrinoMadrina;
        this.fechaConfirmacion = fechaConfirmacion;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.idPadre = idPadre;
        this.padre = padre;
        this.idMadre = idMadre;
        this.madre = madre;
        this.cedulaPersona = cedulaPersona;
        this.persona = persona;
        this.idSacerdote = idSacerdote;
        this.sacerdote = sacerdote;
    }

    public int getIdConfirmacion() {
        return idConfirmacion;
    }

    public void setIdConfirmacion(int idConfirmacion) {
        this.idConfirmacion = idConfirmacion;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getNombre_parroquia() {
        return nombre_parroquia;
    }

    public void setNombre_parroquia(String nombre_parroquia) {
        this.nombre_parroquia = nombre_parroquia;
    }

    public String getIdPadrinoMadrina() {
        return idPadrinoMadrina;
    }

    public void setIdPadrinoMadrina(String idPadrinoMadrina) {
        this.idPadrinoMadrina = idPadrinoMadrina;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
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

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getIdMadre() {
        return idMadre;
    }

    public void setIdMadre(String idMadre) {
        this.idMadre = idMadre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
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

    public String getPadrinoMadrina() {
        return padrinoMadrina;
    }

    public void setPadrinoMadrina(String padrinoMadrina) {
        this.padrinoMadrina = padrinoMadrina;
    }
    
    
}
