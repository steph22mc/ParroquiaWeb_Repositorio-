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
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Defuncion;
import modelo.Direccion;
import modelo.Parroquia;
import modelo.Persona;
import modelo.Sacerdote;
import modeloDao.DefuncionDao;
import modeloDao.DireccionDao;
import modeloDao.ParroquiaDao;
import modeloDao.PersonaDao;
import modeloDao.SacerdoteDao;

/**
 *
 * @author DELL
 */
@ManagedBean
@ViewScoped
public class ControlDefuncion implements Serializable{
    
    private String busqueda;
    private List<Defuncion> defunciones;
    private boolean mostrandoTabla;
    private String estado;
    private DefuncionDao defuncionDao;
    private Defuncion defuncion = new Defuncion();
    private int idDefuncion;
    
    private List<Direccion> direccionesFiltradas;
    private DireccionDao direccionDao;
    private Direccion direccion;
    private String direccionSeleccionada;
    private String direccionSeleccionada2;
    
    private PersonaDao personaDao;
    private Persona persona;
    private String personaSeleccionada;
    private String personaSeleccionada2;
    private String mujerSeleccionada;
    private String hombreSeleccionado;
    private String mujerSeleccionada2;
    private String hombreSeleccionado2;
    
    private ParroquiaDao parroquiaDao;
    private Parroquia parroquia;
    private String parroquiaSeleccionada;
    private String parroquiaSeleccionada2;
    
    private SacerdoteDao sacerdoteDao;
    private Sacerdote sacerdote;
    private String sacerdoteSeleccionado;
    private String sacerdoteSeleccionado2;
    
    private String nuevoEstatus;
    private Date nuevaFecha;
    
    private List<Defuncion> cantidad;
    
    public ControlDefuncion() {
        defuncionDao = new DefuncionDao();
        direccionDao = new DireccionDao();
        personaDao = new PersonaDao();
        parroquiaDao = new ParroquiaDao();
        sacerdoteDao = new SacerdoteDao();
        
        // Inicializar los registros con datos de la base de datos
        cantidad = defuncionDao.getAll();
        defunciones = new ArrayList<>();
        mostrandoTabla = false;
        
        if(cantidad.isEmpty()){
            estado = "";
        }
        else{
            estado = "buscando";
        }
    }
    
    // Getters y setters

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Defuncion> getDefunciones() {
        return defunciones;
    }

    public void setDefunciones(List<Defuncion> defunciones) {
        this.defunciones = defunciones;
    }

    public boolean isMostrandoTabla() {
        return mostrandoTabla;
    }

    public void setMostrandoTabla(boolean mostrandoTabla) {
        this.mostrandoTabla = mostrandoTabla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Defuncion getDefuncion() {
        return defuncion;
    }

    public void setDefuncion(Defuncion defuncion) {
        this.defuncion = defuncion;
    }

    public int getIdDefuncion() {
        return idDefuncion;
    }

    public void setIdDefuncion(int idDefuncion) {
        this.idDefuncion = idDefuncion;
    }

    public String getDireccionSeleccionada() {
        return direccionSeleccionada;
    }

    public void setDireccionSeleccionada(String direccionSeleccionada) {
        this.direccionSeleccionada = direccionSeleccionada;
    }

    public String getDireccionSeleccionada2() {
        return direccionSeleccionada2;
    }

    public void setDireccionSeleccionada2(String direccionSeleccionada2) {
        this.direccionSeleccionada2 = direccionSeleccionada2;
    }

    public String getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(String personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public String getPersonaSeleccionada2() {
        return personaSeleccionada2;
    }

    public void setPersonaSeleccionada2(String personaSeleccionada2) {
        this.personaSeleccionada2 = personaSeleccionada2;
    }

    public String getMujerSeleccionada() {
        return mujerSeleccionada;
    }

    public void setMujerSeleccionada(String mujerSeleccionada) {
        this.mujerSeleccionada = mujerSeleccionada;
    }

    public String getHombreSeleccionado() {
        return hombreSeleccionado;
    }

    public void setHombreSeleccionado(String hombreSeleccionado) {
        this.hombreSeleccionado = hombreSeleccionado;
    }

    public String getMujerSeleccionada2() {
        return mujerSeleccionada2;
    }

    public void setMujerSeleccionada2(String mujerSeleccionada2) {
        this.mujerSeleccionada2 = mujerSeleccionada2;
    }

    public String getHombreSeleccionado2() {
        return hombreSeleccionado2;
    }

    public void setHombreSeleccionado2(String hombreSeleccionado2) {
        this.hombreSeleccionado2 = hombreSeleccionado2;
    }

    public String getParroquiaSeleccionada() {
        return parroquiaSeleccionada;
    }

    public void setParroquiaSeleccionada(String parroquiaSeleccionada) {
        this.parroquiaSeleccionada = parroquiaSeleccionada;
    }

    public String getParroquiaSeleccionada2() {
        return parroquiaSeleccionada2;
    }

    public void setParroquiaSeleccionada2(String parroquiaSeleccionada2) {
        this.parroquiaSeleccionada2 = parroquiaSeleccionada2;
    }

    public String getSacerdoteSeleccionado() {
        return sacerdoteSeleccionado;
    }

    public void setSacerdoteSeleccionado(String sacerdoteSeleccionado) {
        this.sacerdoteSeleccionado = sacerdoteSeleccionado;
    }

    public String getSacerdoteSeleccionado2() {
        return sacerdoteSeleccionado2;
    }

    public void setSacerdoteSeleccionado2(String sacerdoteSeleccionado2) {
        this.sacerdoteSeleccionado2 = sacerdoteSeleccionado2;
    }

    public String getNuevoEstatus() {
        return nuevoEstatus;
    }

    public void setNuevoEstatus(String nuevoEstatus) {
        this.nuevoEstatus = nuevoEstatus;
    }

    public Date getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public List<Defuncion> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Defuncion> cantidad) {
        this.cantidad = cantidad;
    }
    
    public void buscar(){
        try{
            defunciones = defuncionDao.buscarDefunciones(busqueda);
        }catch (SQLException e) {
            e.printStackTrace();
            defunciones = new ArrayList<>();
        }
        
        estado = "mostrandoTabla";
        if(!defunciones.isEmpty()){
            mostrandoTabla = true;
            estado = "mostrandoTabla";
        }else{
            mostrandoTabla = false;
            estado = "buscando";
        }
    }
    
    public void actualizar() throws SQLException, IOException{
        direccionSeleccionada2 = defuncion.getDireccion();
        personaSeleccionada2 = defuncion.getPersona();
        mujerSeleccionada2 = defuncion.getMadre();
        hombreSeleccionado2 = defuncion.getPadre();
        parroquiaSeleccionada2 = defuncion.getNombre_Parroquia();
        sacerdoteSeleccionado2 = defuncion.getSacerdote();
        
        if (direccionSeleccionada2 != null || personaSeleccionada2 != null || mujerSeleccionada2 != null
                || hombreSeleccionado2 != null || parroquiaSeleccionada2 != null || sacerdoteSeleccionado2 != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada2);
                defuncion.setIdDireccion(idDireccion);
                
                String idDifunto = personaDao.obtenerCedulaPorNombre(personaSeleccionada2);
                defuncion.setCedulaPersona(idDifunto);
                String idMadre = personaDao.obtenerCedulaPorNombre(mujerSeleccionada2);
                defuncion.setIdMadre(idMadre);
                String idPadre = personaDao.obtenerCedulaPorNombre(hombreSeleccionado2);
                defuncion.setIdPadre(idPadre);
                
                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada2);
                defuncion.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado2);
                defuncion.setIdSacerdote(idSacerdote); 
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            defuncionDao.actualizar(defuncion);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/sacramento/defuncion.xhtml");
        }
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
    
    public List<Persona> buscarHombres(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarHombres(query);
        } catch (SQLException | ClassNotFoundException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public List<Persona> buscarMujeres(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarMujeres(query);
        } catch (SQLException | ClassNotFoundException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public List<Persona> buscarPersonas(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarDefuncionPersona(query);
        } catch (SQLException e) {
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
    
    public List<Sacerdote> buscarSacerdote(String query){
        try{
            sacerdoteDao = new SacerdoteDao();
            return sacerdoteDao.buscarSacerdote(query);
        }catch (SQLException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public void cargarDefuncion(){
        defuncion= defuncionDao.getById(idDefuncion);
    }
    
    public void retroceder() {
        // Mostrar el buscador nuevamente
        mostrandoTabla = false;
        estado = "buscando";
        defunciones = new ArrayList<>();
    }
    
    public void crearDefuncion() throws SQLException{
        Defuncion nuevaDefuncion = new Defuncion();
        nuevaDefuncion.setFechaDefuncion(nuevaFecha);
        nuevaDefuncion.setEstatusCompromiso(nuevoEstatus);
        
        if (direccionSeleccionada != null || personaSeleccionada != null || mujerSeleccionada != null
                || hombreSeleccionado != null || parroquiaSeleccionada != null || sacerdoteSeleccionado != null){
            try{
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                defuncion.setIdDireccion(idDireccion);

                String idDifunto = personaDao.obtenerCedulaPorNombre(personaSeleccionada);
                defuncion.setCedulaPersona(idDifunto);
                String idMadre = personaDao.obtenerCedulaPorNombre(mujerSeleccionada);
                defuncion.setIdMadre(idMadre);
                String idPadre = personaDao.obtenerCedulaPorNombre(hombreSeleccionado);
                defuncion.setIdPadre(idPadre);

                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada);
                defuncion.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado);
                defuncion.setIdSacerdote(idSacerdote); 
            }catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            defuncionDao.insert(nuevaDefuncion); 
        }
    }
    
    public void redirigirEditar(int idDefuncion) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("defuncionEdit.xhtml?faces-redirect=true&id=" + idDefuncion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "defuncionCrear.xhtml?faces-redirect=true";
    }
    
    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
