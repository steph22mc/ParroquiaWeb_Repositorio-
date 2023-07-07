/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Bautizo;
import modeloDao.BautizoDao;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ControlBautizo {
    
    private String busqueda;
    private List<Bautizo> registros;
    private boolean mostrandoTabla;
    private String estado;
    private BautizoDao bautizoDao;
    
    private String pdfUrl;
    private boolean mostrandoModal;

    public ControlBautizo() {
        bautizoDao = new BautizoDao();
        
        // Inicializar los registros con datos de la base de datos
        registros = bautizoDao.getAll();
        mostrandoTabla = false;
        if(registros.isEmpty()){
            estado = "";
        }else{
            estado = "buscando";
        }
        
    }
    
    public void buscar() {
        try {
            registros = bautizoDao.buscarBautizo(busqueda);
        } catch (SQLException e) {
            e.printStackTrace();
            registros = new ArrayList<>();
        }
        mostrandoTabla = !registros.isEmpty();
        estado = "mostrandoTabla";
    }
    
    public void actualizar(Bautizo bautizo) {
        // Lógica para actualizar el bautizo en la base de datos
    }

    public void imprimir(Bautizo bautizo) {
        // Lógica para imprimir el bautizo
    }

    public void retroceder() {
        // Mostrar el buscador nuevamente
        mostrandoTabla = false;
        estado = "buscando";
    }

    // Getters y setters

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Bautizo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Bautizo> registros) {
        this.registros = registros;
    }

    public boolean isMostrandoTabla() {
        return mostrandoTabla;
    }

    public String getEstado() {
        return estado;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public boolean isMostrandoModal() {
        return mostrandoModal;
    }

    public void setMostrandoModal(boolean mostrandoModal) {
        this.mostrandoModal = mostrandoModal;
    }
    
    // Método para cerrar la sesión del usuario
    public void redirigirEditar() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        redirigir("/sacramento/bautizoEdit.xhtml");
    }

    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
