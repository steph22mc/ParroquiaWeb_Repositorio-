/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modeloDao.UsuarioDao;
import user_manager.LoginManager;

/**
 *
 * @author DELL
 */

/**
 * Clase de control de acceso que actúa como un bean administrado en el contexto de JavaServer Faces (JSF).
 * 
 * @Named: Indica que esta clase es un bean administrado por CDI (Contexts and Dependency Injection). Permite que la clase sea reconocida y utilizada por el framework JSF.
 * 
 * @RequestScoped: Define el alcance del bean como el alcance de solicitud. Esto significa que el estado del bean se mantiene durante una sola solicitud HTTP. 
 * Cada nueva solicitud generará una nueva instancia del bean. Es útil cuando se desea que el estado del bean se reinicie en cada solicitud. Esto es beneficioso cuando se 
 * necesita mantener los datos del bean independientes entre diferentes solicitudes. Por ejemplo, en un formulario de inicio de sesión, se quiere que los datos ingresados 
 * en una solicitud de inicio de sesión no se mezclen con los datos de otra solicitud.
 */
@Named 
@RequestScoped
public class ControlAcceso {
    
    private String usuario;
    private String contrasena;
    String h;

    public ControlAcceso() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    
    

    // Método para autenticar al usuario
    public void autenticar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginManager loginManager = new LoginManager();

        try {
            // Validar las credenciales del usuario
            if (usuario != null && contrasena != null) {
                if (loginManager.autenticarUsuario(usuario, contrasena)) {
                    // Obtener el tipo de usuario
                    String tipoUsuario = loginManager.getTipoUsuario(usuario);
                    if (tipoUsuario != null) {
                        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                        session.setAttribute("usuario", usuario);
                        // Redirigir a diferentes páginas según el tipo de usuario
                        if (tipoUsuario.equals("Sacerdote")) {
                            redirigir("/sesion/pagina_principal_sacerdote.xhtml");
                        } else if (tipoUsuario.equals("Secretario")) {
                            redirigir("/sesion/pagina_principal_secretario.xhtml");
                        } else {
                            redirigir("/sesion/pagina_error.xhtml");
                        }
                    } else {
                        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo obtener el tipo de usuario.", "No se pudo obtener el tipo de usuario."));
                    }
                } else {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Credenciales inválidas.", "Credenciales inválidas."));
                }
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Ingrese usuario y contraseña.", "Ingrese usuario y contraseña."));
            }
        } catch (Exception e) {
            Logger.getLogger(ControlAcceso.class.getName()).log(Level.SEVERE, "Error en la autenticación", e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Credenciales inválidas.", "Credenciales inválidas."));
        }
    }

    // Método para cerrar la sesión del usuario
    public void cerrarSesion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        redirigir("/sesion/login.xhtml");
    }

    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
    
    // Método para redirigir a la página de inicio de sesión
    public void redirigirAlLogin() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/login.xhtml");
    }
    
    // Método para redirigir a la página de inicio
    public void redirigirAIndex() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
    }
    
    // Método para obtener el nombre de usuario actualmente autenticado
    public String obtenerNombreUsuario() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            return (String) session.getAttribute("usuario");
        }
        return null;
    }
    
}
