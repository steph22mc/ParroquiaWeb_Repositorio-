/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modelo.Direccion;
import modelo.Persona;
import modeloDao.DireccionDao;
import modeloDao.PersonaDao;
import modeloDao.UsuarioDao;
import user_manager.LoginManager;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class GestionPersonas implements Serializable {
    
    private Persona persona;
    private PersonaDao personaDao;
    private List<Persona> personas;
    private String cedula;
    
    private String nuevaCedula;
    private String nuevoPrimerNombre;
    private String nuevoSegundoNombre;
    private String nuevoPrimerApellido;
    private String nuevoSegundoApellido;
    private String nuevoGenero;
    private Date nuevaFecha;
    private String nuevoTelefono;
    private int nuevoIdDireccion;
    
    String direccionSeleccionada2;
    
    private String searchText;
    
    private boolean sacerdoteTrue;
    private boolean sacerdoteTrueInitialized = false;
    
    private UsuarioDao userDao = new UsuarioDao();
    private DireccionDao direccionDao;
    private Direccion direccion;
    
    private String direccionInput;
    private List<Direccion> direccionesFiltradas;
    private int idDireccionSeleccionada;
    private String direccionSeleccionada;

    public GestionPersonas() {
        personaDao = new PersonaDao();
        personas = personaDao.getAll();
        direccionDao = new DireccionDao();
    }

    public String getDireccionSeleccionada2() {
        return direccionSeleccionada2;
    }

    public void setDireccionSeleccionada2(String direccionSeleccionada2) {
        this.direccionSeleccionada2 = direccionSeleccionada2;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDireccionSeleccionada() {
        return direccionSeleccionada;
    }

    public void setDireccionSeleccionada(String direccionSeleccionada) {
        this.direccionSeleccionada = direccionSeleccionada;
    }

    public String getDireccionInput() {
        return direccionInput;
    }

    public void setDireccionInput(String direccionInput) {
        this.direccionInput = direccionInput;
    }

    public List<Direccion> getDireccionesFiltradas() {
        return direccionesFiltradas;
    }

    public void setDireccionesFiltradas(List<Direccion> direccionesFiltradas) {
        this.direccionesFiltradas = direccionesFiltradas;
    }

    public int getIdDireccionSeleccionada() {
        return idDireccionSeleccionada;
    }

    public void setIdDireccionSeleccionada(int idDireccionSeleccionada) {
        this.idDireccionSeleccionada = idDireccionSeleccionada;
    }
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getCedula() {
        return cedula;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getNuevaCedula() {
        return nuevaCedula;
    }

    public void setNuevaCedula(String nuevaCedula) {
        this.nuevaCedula = nuevaCedula;
    }

    public String getNuevoPrimerNombre() {
        return nuevoPrimerNombre;
    }

    public void setNuevoPrimerNombre(String nuevoPrimerNombre) {
        this.nuevoPrimerNombre = nuevoPrimerNombre;
    }

    public String getNuevoSegundoNombre() {
        return nuevoSegundoNombre;
    }

    public void setNuevoSegundoNombre(String nuevoSegundoNombre) {
        this.nuevoSegundoNombre = nuevoSegundoNombre;
    }

    public String getNuevoPrimerApellido() {
        return nuevoPrimerApellido;
    }

    public void setNuevoPrimerApellido(String nuevoPrimerApellido) {
        this.nuevoPrimerApellido = nuevoPrimerApellido;
    }

    public String getNuevoSegundoApellido() {
        return nuevoSegundoApellido;
    }

    public void setNuevoSegundoApellido(String nuevoSegundoApellido) {
        this.nuevoSegundoApellido = nuevoSegundoApellido;
    }

    public String getNuevoGenero() {
        return nuevoGenero;
    }

    public void setNuevoGenero(String nuevoGenero) {
        this.nuevoGenero = nuevoGenero;
    }

    public Date getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public String getNuevoTelefono() {
        return nuevoTelefono;
    }

    public void setNuevoTelefono(String nuevoTelefono) {
        this.nuevoTelefono = nuevoTelefono;
    }

    public int getNuevoIdDireccion() {
        return nuevoIdDireccion;
    }

    public void setNuevoIdDireccion(int nuevoIdDireccion) {
        this.nuevoIdDireccion = nuevoIdDireccion;
    }

    public boolean isSacerdoteTrueInitialized() {
        return sacerdoteTrueInitialized;
    }

    public void setSacerdoteTrueInitialized(boolean sacerdoteTrueInitialized) {
        this.sacerdoteTrueInitialized = sacerdoteTrueInitialized;
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
    
    public void buscarPersonas() throws SQLException, ClassNotFoundException{
        try{
            if(searchText != null && !searchText.isEmpty()){
                personas = personaDao.buscarPersonas(searchText);
            } else{
                personas = personaDao.getAll();
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
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
    
    public void redireccionarEditar(String cedula){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try{
            externalContext.redirect("editarPersona.xhtml?faces-redirect=true&cedula=" + cedula);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "crearPersona.xhtml?faces-redirect=true";
    }
    
    public void actualizarPersona() throws SQLException{
        direccionSeleccionada2 = persona.getDireccion();
        if (direccionSeleccionada2 != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(persona.getDireccion());
                persona.setId_Direccion(idDireccion);
                 
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            personaDao.actualizarPersona(persona);
        }
        
    }
    
    public void crearPersona() throws SQLException{
        Persona nuevaPersona = new Persona();
        nuevaPersona.setCedulaPersona(nuevaCedula);
        nuevaPersona.setPrimerNombre(nuevoPrimerNombre);
        nuevaPersona.setSegundoNombre(nuevoSegundoNombre);
        nuevaPersona.setPrimerApellido(nuevoPrimerApellido);
        nuevaPersona.setSegundoApellido(nuevoSegundoApellido);
        nuevaPersona.setGenero(nuevoGenero);
        nuevaPersona.setFechaNacimiento(nuevaFecha);
        nuevaPersona.setTelefono(nuevoTelefono);
        
        if (direccionSeleccionada != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                nuevaPersona.setId_Direccion(idDireccion);
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
        }
        
        personaDao.insertarPersona(nuevaPersona);
        
        
        nuevaCedula = null;
        nuevoPrimerNombre = null;
        nuevoSegundoNombre = null;
        nuevoPrimerApellido = null;
        nuevoSegundoApellido = null;
        nuevoGenero = null;
        nuevaFecha = null;
        nuevoTelefono = null;
        direccionSeleccionada = null;
    }
    
    public void eliminarPersona(String cedula){
        try{
            personaDao.deletePersona(cedula);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/personas/persona.xhtml");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarPersonas(){
        persona = personaDao.getByCedula(cedula);
    }
    
    public void redirigirAPersona() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/personas/persona.xhtml");
    }
    
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}