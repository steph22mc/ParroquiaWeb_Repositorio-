/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Direccion;
import modelo.Parroquia;
import modeloDao.DireccionDao;
import modeloDao.ParroquiaDao;
import modeloDao.UsuarioDao;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ControlParroquia implements Serializable{
    private Parroquia parroquia;
    private ParroquiaDao parroquiaDao;
    private List<Parroquia> parroquias;
    private int idParroquia;
    
    private String nuevoNombre;
    private String direccionSeleccionada;
    String direccionSeleccionada2;
    
    private String searchText;
    
    private boolean sacerdoteTrue;
    private boolean sacerdoteTrueInitialized = false;
    
    private UsuarioDao userDao = new UsuarioDao();
    private DireccionDao direccionDao;
    private Direccion direccion;

    public ControlParroquia() {
        parroquiaDao = new ParroquiaDao();
        parroquias = parroquiaDao.getAll();
        direccionDao = new DireccionDao();
    }

    public Parroquia getParroquia() {
        return parroquia;
    }

    public void setParroquia(Parroquia parroquia) {
        this.parroquia = parroquia;
    }

    public List<Parroquia> getParroquias() {
        return parroquias;
    }

    public void setParroquias(List<Parroquia> parroquias) {
        this.parroquias = parroquias;
    }

    public int getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(int idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getDireccionSeleccionada() {
        return direccionSeleccionada;
    }

    public void setDireccionSeleccionada(String direccionSeleccionada) {
        this.direccionSeleccionada = direccionSeleccionada;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public boolean isSacerdoteTrue() throws SQLException, ClassNotFoundException {
        if (!sacerdoteTrueInitialized) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(false);

            // Obtener el nombre de usuario de la sesión
            String nombreUsuario = (String) session.getAttribute("usuario");

            // Obtener el tipo de usuario desde la base de datos utilizando el UsuarioDao
            String tipoUsuario = userDao.obtenerTipoUsuario(nombreUsuario);
            sacerdoteTrue = "Sacerdote".equals(tipoUsuario);
            sacerdoteTrueInitialized = true;
        }
        return sacerdoteTrue;
    }

    public void setSacerdoteTrue(boolean sacerdoteTrue) {
        this.sacerdoteTrue = sacerdoteTrue;
    }

    public boolean isSacerdoteTrueInitialized() {
        return sacerdoteTrueInitialized;
    }

    public void setSacerdoteTrueInitialized(boolean sacerdoteTrueInitialized) {
        this.sacerdoteTrueInitialized = sacerdoteTrueInitialized;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDireccionSeleccionada2() {
        return direccionSeleccionada2;
    }

    public void setDireccionSeleccionada2(String direccionSeleccionada2) {
        this.direccionSeleccionada2 = direccionSeleccionada2;
    }
    
    public List<Direccion> buscarDirecciones(String query) {
        try {
            direccionDao = new DireccionDao();
            return direccionDao.buscarDirecciones(query);
        } catch (SQLException | ClassNotFoundException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public List<Parroquia> buscarParroquia(String query) {
        try {
            parroquiaDao = new ParroquiaDao();
            return parroquiaDao.buscarParroquia(query);
        } catch (SQLException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public void buscarParroquia() throws SQLException, ClassNotFoundException{
        try{
            if(searchText != null && !searchText.isEmpty()){
                parroquias = parroquiaDao.buscarParroquia(searchText);
            } else{
                parroquias = parroquiaDao.getAll();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void actualizarParroquia() throws SQLException{
        direccionSeleccionada2 = parroquia.getDireccion();
        if (direccionSeleccionada2 != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada2);
                parroquia.setIdDireccion(idDireccion);
                 
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            parroquiaDao.update(parroquia);
        }
    }
    
    public void crearParroquia() throws SQLException{
        Parroquia nuevaParroquia = new Parroquia();
        nuevaParroquia.setNombreParroquia(nuevoNombre);
        
        if (direccionSeleccionada != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                nuevaParroquia.setIdDireccion(idDireccion);
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            parroquiaDao.insert(nuevaParroquia);
        }
    }
    
    public void eliminarParroquia(int id){
        try{
            parroquiaDao.delete(idParroquia);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/parroquia/parroquia.xhtml");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarParroquia(){
        parroquia = parroquiaDao.getById(idParroquia);
    }
    
    public void redireccionarEditar(int id){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try{
            externalContext.redirect("editarParroquia.xhtml?faces-redirect=true&id=" + id);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "crearParroquia.xhtml?faces-redirect=true";
    }
    
    public void redirigirAParroquia() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/parroquia/parroquia.xhtml");
    }
    
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
