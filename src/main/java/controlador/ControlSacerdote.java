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
import modelo.Persona;
import modelo.Sacerdote;
import modeloDao.PersonaDao;
import modeloDao.SacerdoteDao;
import modeloDao.UsuarioDao;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ControlSacerdote implements Serializable{
    private Sacerdote sacerdote;
    private SacerdoteDao sacerdoteDao;
    private List<Sacerdote> sacerdotes;
    private int idSacerdote;
    
    private String nuevoRango;
    private String personaSeleccionada;
    private String personaSeleccionada2;
    
    private PersonaDao personaDao;
    private Persona persona;
    
    private String searchText;
    
    private boolean sacerdoteTrue;
    private boolean sacerdoteTrueInitialized = false;
    
    private UsuarioDao userDao = new UsuarioDao();
    
    public ControlSacerdote() {
        sacerdoteDao = new SacerdoteDao();
        sacerdotes = sacerdoteDao.listarSacerdotes();
        personaDao = new PersonaDao();
    }

    public int getIdSacerdote() {
        return idSacerdote;
    }

    public void setIdSacerdote(int idSacerdote) {
        this.idSacerdote = idSacerdote;
    }

    public String getNuevoRango() {
        return nuevoRango;
    }

    public void setNuevoRango(String nuevoRango) {
        this.nuevoRango = nuevoRango;
    }

    public String getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(String personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Sacerdote> getSacerdotes() {
        return sacerdotes;
    }

    public void setSacerdotes(List<Sacerdote> sacerdotes) {
        this.sacerdotes = sacerdotes;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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

    public Sacerdote getSacerdote() {
        return sacerdote;
    }

    public void setSacerdote(Sacerdote sacerdote) {
        this.sacerdote = sacerdote;
    }

    public String getPersonaSeleccionada2() {
        return personaSeleccionada2;
    }

    public void setPersonaSeleccionada2(String personaSeleccionada2) {
        this.personaSeleccionada2 = personaSeleccionada2;
    }
    
    public List<Persona> buscarPersonas(String query){
        try{
            personaDao = new PersonaDao();
            return personaDao.buscarHombres(query);
        }catch(SQLException | ClassNotFoundException e){
            
        }
        return null;
    }
    
    public void buscarSacerdotes() throws SQLException, ClassNotFoundException{
        try{
            if(searchText != null && !searchText.isEmpty()){
                sacerdotes = sacerdoteDao.buscarSacerdote(searchText);
            }else{
                sacerdotes = sacerdoteDao.listarSacerdotes();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void actualizar() throws SQLException, IOException{
        personaSeleccionada2 = sacerdote.getSacerdote();
        
        if(personaSeleccionada2 != null){
            String cedula = personaDao.obtenerCedulaPorNombre(personaSeleccionada2);
            sacerdote.setCedulaPersona(cedula);
            
            sacerdoteDao.actualizarSacerdote(sacerdote);
        }
    }
    
    public void crearSacerdote() throws SQLException{
        Sacerdote nuevoSacerdote = new Sacerdote();
        nuevoSacerdote.setRango(nuevoRango);
        
        if(personaSeleccionada != null){
            String cedula = personaDao.obtenerCedulaPorNombre(personaSeleccionada);
            nuevoSacerdote.setCedulaPersona(cedula);
            
            sacerdoteDao.insertarSacerdote(nuevoSacerdote);
        }
    }
    
    public void eliminarSacerdote(int id){
        try{
            sacerdoteDao.eliminarSacerdote(id);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/sacerdote/sacerdote.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarSacerdotes(){
        sacerdote = sacerdoteDao.getById(idSacerdote);
    }
    public void redireccionarEditar(int id){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try{
            externalContext.redirect("editarSacerdote.xhtml?faces-redirect=true&id=" + id);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "crearSacerdote.xhtml?faces-redirect=true";
    }
    
    public void redirigirASacerdote() throws IOException{
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/sacerdote/sacerdote.xhtml");
    }
    
    /**
     * Método para redirigir a una página específica.
     * 
     * @param url la URL de la página a la que se desea redirigir.
     * @throws IOException si ocurre un error al redirigir la página.
     */
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
