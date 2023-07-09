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
public class Defuncion {
    private int idDefuncion;
    private String estatusCompromiso;
    private Date fechaDefuncion;
    private String idPadre;
    private String padre;
    private String idMadre;
    private String madre;
    private int idDireccion;
    private String direccion;
    private int idParroquia;
    private String nombre_Parroquia;
    private String cedulaPersona;
    private String persona;
    private int idSacerdote;
    private String sacerdote;

    public Defuncion() {
    }

    public Defuncion(String estatusCompromiso, Date fechaDefuncion, String idPadre, String padre, String idMadre, String madre, int idDireccion, String direccion, int idParroquia, String nombre_Parroquia, String cedulaPersona, String persona, int idSacerdote, String sacerdote) {
        this.estatusCompromiso = estatusCompromiso;
        this.fechaDefuncion = fechaDefuncion;
        this.idPadre = idPadre;
        this.padre = padre;
        this.idMadre = idMadre;
        this.madre = madre;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.idParroquia = idParroquia;
        this.nombre_Parroquia = nombre_Parroquia;
        this.cedulaPersona = cedulaPersona;
        this.persona = persona;
        this.idSacerdote = idSacerdote;
        this.sacerdote = sacerdote;
    }

    public int getIdDefuncion() {
        return idDefuncion;
    }

    public void setIdDefuncion(int idDefuncion) {
        this.idDefuncion = idDefuncion;
    }

    public String getEstatusCompromiso() {
        return estatusCompromiso;
    }

    public void setEstatusCompromiso(String estatusCompromiso) {
        this.estatusCompromiso = estatusCompromiso;
    }

    public Date getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Date fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
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

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getNombre_Parroquia() {
        return nombre_Parroquia;
    }

    public void setNombre_Parroquia(String nombre_Parroquia) {
        this.nombre_Parroquia = nombre_Parroquia;
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
