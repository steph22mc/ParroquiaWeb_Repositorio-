/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author DELL
 */
@Named
@RequestScoped
public class ControlSacramento {
    
    private String iframeSrc;

    public String getIframeSrc() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        iframeSrc = externalContext.getRequestContextPath() + "/pagina_inicio.xhtml";
        return iframeSrc;
    }

    public void setIframeSrc(String iframeSrc) {
        this.iframeSrc = iframeSrc;
    }
    
    public void redireccionarCrearPersona() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/personas/crearPersona.xhtml");
    }
    
    public void redireccionarCrearDireccion() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/direccion/crearDireccion.xhtml");
    }
    
    public void redireccionarCrearParroquia() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/parroquia/crearParroquia.xhtml");
    }
    
    public void redireccionarCrearSacerdote() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacerdote/crearSacerdote.xhtml");
    }
    
    public void redirigirABautizo() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacramento/bautizo.xhtml");
    }
    
    public void redirigirAComunion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacramento/comunion.xhtml");
    }
    
    public void redirigirAConfirmacion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacramento/confirmacion.xhtml");
    }
    
    public void redirigirABoda() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacramento/boda.xhtml");
    }
    
    public void redirigirADefuncion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacramento/defuncion.xhtml");
    }
    
    public void redirigirAInicio() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        iframeSrc = externalContext.getRequestContextPath() + "/pagina_inicio.xhtml";
    }

    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
