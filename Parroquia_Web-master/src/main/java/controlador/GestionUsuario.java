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
public class GestionUsuario {
    
    public void redirigirAUsuario() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/usuario/usuario.xhtml");
    }

    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
    
}
