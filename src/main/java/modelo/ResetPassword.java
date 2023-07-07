/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class ResetPassword {
    private int idReset;
    private int idUsuario;
    private String hashCode;
    private Date exptime;
    private Date intime;

    public ResetPassword() {
    }

    public ResetPassword(int idUsuario, String hashCode, Timestamp exptime) {
        this.idUsuario = idUsuario;
        this.hashCode = hashCode;
        this.exptime = exptime;
    }

    public int getIdReset() {
        return idReset;
    }

    public void setIdReset(int idReset) {
        this.idReset = idReset;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public Date getExptime() {
        return exptime;
    }

    public void setExptime(Date exptime) {
        this.exptime = exptime;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }
    
    public boolean isValid() {
        return exptime.after(new Timestamp(System.currentTimeMillis()));
    }
}
