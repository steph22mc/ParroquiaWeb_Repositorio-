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
public class Boda {
    private int idBoda;
    private String idEsposo;
    private String esposo;
    private String idEsposa;
    private String esposa;
    private Date fechaBoda;
    private int idDireccion;
    private String direccion;
    private int idParroquia;
    private String parroquia;
    private String idPadrino;
    private String madrinaPadrino;
    private String idMadreEsposo;
    private String madreEsposo;
    private String idPadreEsposo;
    private String padreEsposo;
    private String idMadreEsposa;
    private String madreEsposa;
    private String idPadreEsposa;
    private String padreEsposa;
    private String cedulaPersona;
    private String persona;
    private int idSacerdote;
    private String sacerdote;

    public Boda() {
    }

    public Boda(String idEsposo, String esposo, String idEsposa, String esposa, Date fechaBoda, int idDireccion, String direccion, int idParroquia, String parroquia, String idPadrino, String madrinaPadrino, String idMadreEsposo, String madreEsposo, String idPadreEsposo, String padreEsposo, String idMadreEsposa, String madreEsposa, String idPadreEsposa, String padreEsposa, String cedulaPersona, String persona, int idSacerdote, String sacerdote) {
        this.idEsposo = idEsposo;
        this.esposo = esposo;
        this.idEsposa = idEsposa;
        this.esposa = esposa;
        this.fechaBoda = fechaBoda;
        this.idDireccion = idDireccion;
        this.direccion = direccion;
        this.idParroquia = idParroquia;
        this.parroquia = parroquia;
        this.idPadrino = idPadrino;
        this.madrinaPadrino = madrinaPadrino;
        this.idMadreEsposo = idMadreEsposo;
        this.madreEsposo = madreEsposo;
        this.idPadreEsposo = idPadreEsposo;
        this.padreEsposo = padreEsposo;
        this.idMadreEsposa = idMadreEsposa;
        this.madreEsposa = madreEsposa;
        this.idPadreEsposa = idPadreEsposa;
        this.padreEsposa = padreEsposa;
        this.cedulaPersona = cedulaPersona;
        this.persona = persona;
        this.idSacerdote = idSacerdote;
        this.sacerdote = sacerdote;
    }

    public int getIdBoda() {
        return idBoda;
    }

    public void setIdBoda(int idBoda) {
        this.idBoda = idBoda;
    }

    public String getIdEsposo() {
        return idEsposo;
    }

    public void setIdEsposo(String idEsposo) {
        this.idEsposo = idEsposo;
    }

    public String getEsposo() {
        return esposo;
    }

    public void setEsposo(String esposo) {
        this.esposo = esposo;
    }

    public String getIdEsposa() {
        return idEsposa;
    }

    public void setIdEsposa(String idEsposa) {
        this.idEsposa = idEsposa;
    }

    public String getEsposa() {
        return esposa;
    }

    public void setEsposa(String esposa) {
        this.esposa = esposa;
    }

    public Date getFechaBoda() {
        return fechaBoda;
    }

    public void setFechaBoda(Date fechaBoda) {
        this.fechaBoda = fechaBoda;
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

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getIdPadrino() {
        return idPadrino;
    }

    public void setIdPadrino(String idPadrino) {
        this.idPadrino = idPadrino;
    }

    public String getMadrinaPadrino() {
        return madrinaPadrino;
    }

    public void setMadrinaPadrino(String madrinaPadrino) {
        this.madrinaPadrino = madrinaPadrino;
    }

    public String getIdMadreEsposo() {
        return idMadreEsposo;
    }

    public void setIdMadreEsposo(String idMadreEsposo) {
        this.idMadreEsposo = idMadreEsposo;
    }

    public String getMadreEsposo() {
        return madreEsposo;
    }

    public void setMadreEsposo(String madreEsposo) {
        this.madreEsposo = madreEsposo;
    }

    public String getIdPadreEsposo() {
        return idPadreEsposo;
    }

    public void setIdPadreEsposo(String idPadreEsposo) {
        this.idPadreEsposo = idPadreEsposo;
    }

    public String getPadreEsposo() {
        return padreEsposo;
    }

    public void setPadreEsposo(String padreEsposo) {
        this.padreEsposo = padreEsposo;
    }

    public String getIdMadreEsposa() {
        return idMadreEsposa;
    }

    public void setIdMadreEsposa(String idMadreEsposa) {
        this.idMadreEsposa = idMadreEsposa;
    }

    public String getMadreEsposa() {
        return madreEsposa;
    }

    public void setMadreEsposa(String madreEsposa) {
        this.madreEsposa = madreEsposa;
    }

    public String getIdPadreEsposa() {
        return idPadreEsposa;
    }

    public void setIdPadreEsposa(String idPadreEsposa) {
        this.idPadreEsposa = idPadreEsposa;
    }

    public String getPadreEsposa() {
        return padreEsposa;
    }

    public void setPadreEsposa(String padreEsposa) {
        this.padreEsposa = padreEsposa;
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
