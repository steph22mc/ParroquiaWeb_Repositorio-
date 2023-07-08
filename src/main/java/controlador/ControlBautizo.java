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
import modelo.Bautizo;
import modelo.Direccion;
import modelo.Parroquia;
import modelo.Persona;
import modelo.Sacerdote;
import modeloDao.BautizoDao;
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
public class ControlBautizo implements Serializable{
    
    private String busqueda;
    private List<Bautizo> registros;
    private boolean mostrandoTabla;
    private String estado;
    private BautizoDao bautizoDao;
    private Bautizo bautizo = new Bautizo();
    private int idBautizo;
    
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

    private Date nuevaFecha;
    
    private List<Bautizo> cantidad;
    public ControlBautizo() {
        bautizoDao = new BautizoDao();
        direccionDao = new DireccionDao();
        personaDao = new PersonaDao();
        parroquiaDao = new ParroquiaDao();
        sacerdoteDao = new SacerdoteDao();
        
        // Inicializar los registros con datos de la base de datos
        cantidad = bautizoDao.getAll();
        registros = new ArrayList<>();
        mostrandoTabla = false;
        if(cantidad.isEmpty()){
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
        
        estado = "mostrandoTabla";
        if(!registros.isEmpty()){
            mostrandoTabla = true;
            estado = "mostrandoTabla";
        }else{
            mostrandoTabla = false;
            estado = "buscando";
        }
    }
    
    public void actualizar() throws SQLException, IOException {
        direccionSeleccionada2 = bautizo.getDireccion();
        personaSeleccionada2 = bautizo.getPersona();
        mujerSeleccionada2 = bautizo.getMadrina();
        hombreSeleccionado2 = bautizo.getPadrino();
        parroquiaSeleccionada2 = bautizo.getNombreParroquia();
        sacerdoteSeleccionado2 = bautizo.getSacerdote();
        
        if (direccionSeleccionada2 != null || personaSeleccionada2 != null || mujerSeleccionada2 != null
                || hombreSeleccionado2 != null || parroquiaSeleccionada2 != null || sacerdoteSeleccionado2 != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada2);
                bautizo.setIdDireccion(idDireccion);
                
                String idBautizado = personaDao.obtenerCedulaPorNombre(personaSeleccionada2);
                bautizo.setCedulaPersona(idBautizado);
                String idMadrina = personaDao.obtenerCedulaPorNombre(mujerSeleccionada2);
                bautizo.setIdMadrina(idMadrina);
                String idPadrino = personaDao.obtenerCedulaPorNombre(hombreSeleccionado2);
                bautizo.setIdPadrino(idPadrino);
                
                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada2);
                bautizo.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado2);
                bautizo.setIdSacerdote(idSacerdote); 
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            bautizoDao.update(bautizo);
            
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/sacramento/bautizo.xhtml");
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
            return personaDao.buscarPersonas(query);
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
    
    public List<Sacerdote> buscarSacerdote(String query){
        try{
            sacerdoteDao = new SacerdoteDao();
            return sacerdoteDao.buscarSacerdote(query);
        }catch (SQLException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public void cargarBautizos(){
        bautizo = bautizoDao.getById(idBautizo);
    }

    public void imprimir(Bautizo bautizo) {
        // Lógica para imprimir el bautizo
    }

    public void retroceder() {
        // Mostrar el buscador nuevamente
        mostrandoTabla = false;
        estado = "buscando";
        registros = new ArrayList<>();
    }
    
    public void crearBautizo() throws SQLException{
        Bautizo nuevoBautizo = new Bautizo();
        nuevoBautizo.setFechaBautizo(nuevaFecha);
        
        if (direccionSeleccionada != null || personaSeleccionada != null || mujerSeleccionada != null
                || hombreSeleccionado != null || parroquiaSeleccionada != null || sacerdoteSeleccionado != null) {
            try {
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                nuevoBautizo.setIdDireccion(idDireccion);
                
                String idBautizado = personaDao.obtenerCedulaPorNombre(personaSeleccionada);
                nuevoBautizo.setCedulaPersona(idBautizado);
                String idMadrina = personaDao.obtenerCedulaPorNombre(mujerSeleccionada);
                nuevoBautizo.setIdMadrina(idMadrina);
                String idPadrino = personaDao.obtenerCedulaPorNombre(hombreSeleccionado);
                nuevoBautizo.setIdPadrino(idPadrino);
                
                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada);
                nuevoBautizo.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado);
                nuevoBautizo.setIdSacerdote(idSacerdote); 
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
        }
        
        bautizoDao.insert(nuevoBautizo); 
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

    public int getIdBautizo() {
        return idBautizo;
    }

    public void setIdBautizo(int idBautizo) {
        this.idBautizo = idBautizo;
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

    public Bautizo getBautizo() {
        return bautizo;
    }

    public void setBautizo(Bautizo bautizo) {
        this.bautizo = bautizo;
    }

    public Date getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(Date nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
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

    public String getSacerdoteSeleccionado() {
        return sacerdoteSeleccionado;
    }

    public void setSacerdoteSeleccionado(String sacerdoteSeleccionado) {
        this.sacerdoteSeleccionado = sacerdoteSeleccionado;
    }

    public List<Bautizo> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Bautizo> cantidad) {
        this.cantidad = cantidad;
    }
    
    public void redirigirEditar(int idBautizo) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("bautizoEdit.xhtml?faces-redirect=true&id=" + idBautizo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "bautizoCrear.xhtml?faces-redirect=true";
    }

    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
