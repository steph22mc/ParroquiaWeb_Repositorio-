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
public class Comunion {
    private int idComunion;
    private String idPadre;
    private String idMadre;
    private Date fechaComunion;
    private int idDireccion;
    private int idParroquia;
    private String cedulaPersona;
    private int idSacerdote;
    private String padre;
    private String madre;
    private String direccion;
    private String parroquia;
    private String persona;
    private String sacerdote;

    public Comunion() {
    }

    public Comunion(String idPadre, String idMadre, Date fechaComunion, int idDireccion, int idParroquia, String cedulaPersona, int idSacerdote) {
        this.idPadre = idPadre;
        this.idMadre = idMadre;
        this.fechaComunion = fechaComunion;
        this.idDireccion = idDireccion;
        this.idParroquia = idParroquia;
        this.cedulaPersona = cedulaPersona;
        this.idSacerdote = idSacerdote;
    }

    public int getIdComunion() {
        return idComunion;
    }

    public void setIdComunion(int idComunion) {
        this.idComunion = idComunion;
    }

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public String getIdMadre() {
        return idMadre;
    }

    public void setIdMadre(String idMadre) {
        this.idMadre = idMadre;
    }

    public Date getFechaComunion() {
        return fechaComunion;
    }

    public void setFechaComunion(Date fechaComunion) {
        this.fechaComunion = fechaComunion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public int getIdSacerdote() {
        return idSacerdote;
    }

    public void setIdSacerdote(int idSacerdote) {
        this.idSacerdote = idSacerdote;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getSacerdote() {
        return sacerdote;
    }

    public void setSacerdote(String sacerdote) {
        this.sacerdote = sacerdote;
    }
    
    
}
