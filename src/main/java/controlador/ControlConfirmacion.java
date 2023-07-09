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
import modelo.Confirmacion;
import modelo.Direccion;
import modelo.Parroquia;
import modelo.Persona;
import modelo.Sacerdote;
import modeloDao.ConfirmacionDao;
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
public class ControlConfirmacion implements Serializable{
    private String busqueda;
    private List<Confirmacion> registros;
    private boolean mostrandoTabla;
    private String estado;
    private ConfirmacionDao confirmacionDao;
    private Confirmacion confirmacion = new Confirmacion();
    private  int idConfirmacion;
    
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
    private String testigoSeleccionado;
    private String testigoSeleccionado2;
    
    private ParroquiaDao parroquiaDao;
    private Parroquia parroquia;
    private String parroquiaSeleccionada;
    private String parroquiaSeleccionada2;
    
    private SacerdoteDao sacerdoteDao;
    private Sacerdote sacerdote;
    private String sacerdoteSeleccionado;
    private String sacerdoteSeleccionado2;
    
    private Date nuevaFecha;
    
    private List<Confirmacion> cantidad;

    public ControlConfirmacion() {
        confirmacionDao = new ConfirmacionDao();
        direccionDao = new DireccionDao();
        personaDao = new PersonaDao();
        parroquiaDao = new ParroquiaDao();
        sacerdoteDao = new SacerdoteDao();
        
        cantidad = confirmacionDao.getAll();
        registros = new ArrayList<>();
        mostrandoTabla = false;
        if(cantidad.isEmpty()){
            estado = "";
        }else{
            estado = "buscando";
        }
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Confirmacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Confirmacion> registros) {
        this.registros = registros;
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

    public Confirmacion getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Confirmacion confirmacion) {
        this.confirmacion = confirmacion;
    }

    public int getIdConfirmacion() {
        return idConfirmacion;
    }

    public void setIdConfirmacion(int idConfirmacion) {
        this.idConfirmacion = idConfirmacion;
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

    public String getTestigoSeleccionado() {
        return testigoSeleccionado;
    }

    public void setTestigoSeleccionado(String testigoSeleccionado) {
        this.testigoSeleccionado = testigoSeleccionado;
    }

    public String getTestigoSeleccionado2() {
        return testigoSeleccionado2;
    }

    public void setTestigoSeleccionado2(String testigoSeleccionado2) {
        this.testigoSeleccionado2 = testigoSeleccionado2;
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

    public Date getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public List<Confirmacion> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Confirmacion> cantidad) {
        this.cantidad = cantidad;
    }
    
    public void buscar(){
        try {
            registros = confirmacionDao.buscarConfirmacion(busqueda);
        } catch (SQLException e) {
            e.printStackTrace();
            registros = new ArrayList<>();
        }
        
        estado = "mostrandoTabla";
        if(!registros.isEmpty()){
            mostrandoTabla = true;
            estado = "mostrandoTabla";
        }else{
            mostrandoTabla = false;
            estado = "buscando";
        }
    }
    
    public void actualizar() throws SQLException, IOException{
        direccionSeleccionada2 = confirmacion.getDireccion();
        personaSeleccionada2 = confirmacion.getPersona();
        mujerSeleccionada2 = confirmacion.getMadre();
        hombreSeleccionado2 = confirmacion.getPadre();
        testigoSeleccionado2 = confirmacion.getPadrinoMadrina();
        parroquiaSeleccionada2 = confirmacion.getNombre_parroquia();
        sacerdoteSeleccionado2 = confirmacion.getSacerdote();
        
        if (direccionSeleccionada2 != null || personaSeleccionada2 != null || mujerSeleccionada2 != null
                || hombreSeleccionado2 != null || parroquiaSeleccionada2 != null || sacerdoteSeleccionado2 != null || testigoSeleccionado2 != null){
            try{
                int idConfirmacion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada2);
                confirmacion.setIdDireccion(idConfirmacion);

                String idPersona = personaDao.obtenerCedulaPorNombre(personaSeleccionada2);
                confirmacion.setCedulaPersona(idPersona);
                String idMadre = personaDao.obtenerCedulaPorNombre(mujerSeleccionada2);
                confirmacion.setIdMadre(idMadre);
                String idPadre = personaDao.obtenerCedulaPorNombre(hombreSeleccionado2);
                confirmacion.setIdPadre(idPadre);
                String idTestigo = personaDao.obtenerCedulaPorNombre(testigoSeleccionado2);
                confirmacion.setIdPadrinoMadrina(idTestigo); 

                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada2);
                confirmacion.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado2);
                confirmacion.setIdSacerdote(idSacerdote); 
            }catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            confirmacionDao.actualizar(confirmacion);
            
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/sacramento/confirmacion.xhtml");
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
            return personaDao.buscarConfirmacionPersona(query);
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
    
    public void cargarConfirmacion(){
        confirmacion = confirmacionDao.getById(idConfirmacion);
    }
    
    public void crearConfirmacion() throws SQLException{
        Confirmacion nuevaConfirmacion = new Confirmacion();
        nuevaConfirmacion.setFechaConfirmacion(nuevaFecha);
        
        if (direccionSeleccionada != null || personaSeleccionada != null || mujerSeleccionada != null
                || hombreSeleccionado != null || parroquiaSeleccionada != null || sacerdoteSeleccionado != null || testigoSeleccionado != null){
            try{
                int idConfirmacion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                confirmacion.setIdDireccion(idConfirmacion);

                String idPersona = personaDao.obtenerCedulaPorNombre(personaSeleccionada);
                confirmacion.setCedulaPersona(idPersona);
                String idMadre = personaDao.obtenerCedulaPorNombre(mujerSeleccionada);
                confirmacion.setIdMadre(idMadre);
                String idPadre = personaDao.obtenerCedulaPorNombre(hombreSeleccionado);
                confirmacion.setIdPadre(idPadre);
                String idTestigo = personaDao.obtenerCedulaPorNombre(testigoSeleccionado);
                confirmacion.setIdPadrinoMadrina(idTestigo); 

                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada);
                confirmacion.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado);
                confirmacion.setIdSacerdote(idSacerdote); 
            }catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            confirmacionDao.insert(nuevaConfirmacion);
        }
    }

    public void retroceder() {
        // Mostrar el buscador nuevamente
        mostrandoTabla = false;
        estado = "buscando";
        registros = new ArrayList<>();
    }
    
    public void redirigirEditar(int idConfirmacion) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("confirmacionEdit.xhtml?faces-redirect=true&id=" + idConfirmacion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "confirmacionCrear.xhtml?faces-redirect=true";
    }

    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
