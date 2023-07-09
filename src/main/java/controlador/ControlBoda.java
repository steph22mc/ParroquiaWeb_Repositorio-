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
import modelo.Boda;
import modelo.Direccion;
import modelo.Parroquia;
import modelo.Persona;
import modelo.Sacerdote;
import modeloDao.BodaDao;
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
public class ControlBoda implements Serializable{
    private String busqueda;
    private List<Boda> registros;
    private boolean mostrandoTabla;
    private String estado;
    private BodaDao bodaDao;
    private Boda boda = new Boda();
    private int idBoda;
    
    private List<Direccion> direccionesFiltradas;
    private DireccionDao direccionDao;
    private Direccion direccion;
    private String direccionSeleccionada;
    private String direccionSeleccionada2;
    
    private PersonaDao personaDao;
    private Persona persona;
    private String personaSeleccionada;
    private String personaSeleccionada2;
    private String esposaSeleccionada;
    private String esposoSeleccionado;
    private String esposaSeleccionada2;
    private String esposoSeleccionado2;
    private String padrinoSeleccionado;
    private String padrinoSeleccionado2;
    private String madreEsposoSeleccionada;
    private String padreEsposoSeleccionado;
    private String madreEsposaSeleccionada;
    private String padreEsposaSeleccionada;
    private String madreEsposoSeleccionada2;
    private String padreEsposoSeleccionado2;
    private String madreEsposaSeleccionada2;
    private String padreEsposaSeleccionada2;
    
    private ParroquiaDao parroquiaDao;
    private Parroquia parroquia;
    private String parroquiaSeleccionada;
    private String parroquiaSeleccionada2;
    
    private SacerdoteDao sacerdoteDao;
    private Sacerdote sacerdote;
    private String sacerdoteSeleccionado;
    private String sacerdoteSeleccionado2;

    private Date nuevaFecha;
    
    private List<Boda> cantidad;

    public ControlBoda() {
        bodaDao = new BodaDao();
        direccionDao = new DireccionDao();
        personaDao = new PersonaDao();
        parroquiaDao = new ParroquiaDao();
        sacerdoteDao = new SacerdoteDao();
        
        // Inicializar los registros con datos de la base de datos
        cantidad = bodaDao.getAll();
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

    public List<Boda> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Boda> registros) {
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

    public Boda getBoda() {
        return boda;
    }

    public void setBoda(Boda boda) {
        this.boda = boda;
    }

    public int getIdBoda() {
        return idBoda;
    }

    public void setIdBoda(int idBoda) {
        this.idBoda = idBoda;
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

    public String getEsposaSeleccionada() {
        return esposaSeleccionada;
    }

    public void setEsposaSeleccionada(String esposaSeleccionada) {
        this.esposaSeleccionada = esposaSeleccionada;
    }

    public String getEsposoSeleccionado() {
        return esposoSeleccionado;
    }

    public void setEsposoSeleccionado(String esposoSeleccionado) {
        this.esposoSeleccionado = esposoSeleccionado;
    }

    public String getEsposaSeleccionada2() {
        return esposaSeleccionada2;
    }

    public void setEsposaSeleccionada2(String esposaSeleccionada2) {
        this.esposaSeleccionada2 = esposaSeleccionada2;
    }

    public String getEsposoSeleccionado2() {
        return esposoSeleccionado2;
    }

    public void setEsposoSeleccionado2(String esposoSeleccionado2) {
        this.esposoSeleccionado2 = esposoSeleccionado2;
    }

    public String getPadrinoSeleccionado() {
        return padrinoSeleccionado;
    }

    public void setPadrinoSeleccionado(String padrinoSeleccionado) {
        this.padrinoSeleccionado = padrinoSeleccionado;
    }

    public String getPadrinoSeleccionado2() {
        return padrinoSeleccionado2;
    }

    public void setPadrinoSeleccionado2(String padrinoSeleccionado2) {
        this.padrinoSeleccionado2 = padrinoSeleccionado2;
    }

    public String getMadreEsposoSeleccionada() {
        return madreEsposoSeleccionada;
    }

    public void setMadreEsposoSeleccionada(String madreEsposoSeleccionada) {
        this.madreEsposoSeleccionada = madreEsposoSeleccionada;
    }

    public String getPadreEsposoSeleccionado() {
        return padreEsposoSeleccionado;
    }

    public void setPadreEsposoSeleccionado(String padreEsposoSeleccionado) {
        this.padreEsposoSeleccionado = padreEsposoSeleccionado;
    }

    public String getMadreEsposaSeleccionada() {
        return madreEsposaSeleccionada;
    }

    public void setMadreEsposaSeleccionada(String madreEsposaSeleccionada) {
        this.madreEsposaSeleccionada = madreEsposaSeleccionada;
    }

    public String getPadreEsposaSeleccionada() {
        return padreEsposaSeleccionada;
    }

    public void setPadreEsposaSeleccionada(String padreEsposaSeleccionada) {
        this.padreEsposaSeleccionada = padreEsposaSeleccionada;
    }

    public String getMadreEsposoSeleccionada2() {
        return madreEsposoSeleccionada2;
    }

    public void setMadreEsposoSeleccionada2(String madreEsposoSeleccionada2) {
        this.madreEsposoSeleccionada2 = madreEsposoSeleccionada2;
    }

    public String getPadreEsposoSeleccionado2() {
        return padreEsposoSeleccionado2;
    }

    public void setPadreEsposoSeleccionado2(String padreEsposoSeleccionado2) {
        this.padreEsposoSeleccionado2 = padreEsposoSeleccionado2;
    }

    public String getMadreEsposaSeleccionada2() {
        return madreEsposaSeleccionada2;
    }

    public void setMadreEsposaSeleccionada2(String madreEsposaSeleccionada2) {
        this.madreEsposaSeleccionada2 = madreEsposaSeleccionada2;
    }

    public String getPadreEsposaSeleccionada2() {
        return padreEsposaSeleccionada2;
    }

    public void setPadreEsposaSeleccionada2(String padreEsposaSeleccionada2) {
        this.padreEsposaSeleccionada2 = padreEsposaSeleccionada2;
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

    public List<Boda> getCantidad() {
        return cantidad;
    }

    public void setCantidad(List<Boda> cantidad) {
        this.cantidad = cantidad;
    }
    
    public void buscar(){
        try {
            registros = bodaDao.buscarBoda(busqueda);
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
        direccionSeleccionada2 = boda.getDireccion();
        personaSeleccionada2 = boda.getPersona();
        parroquiaSeleccionada2 = boda.getParroquia();
        sacerdoteSeleccionado2 = boda.getSacerdote();
        esposaSeleccionada2 = boda.getEsposa();
        esposoSeleccionado2 = boda.getEsposo();
        padrinoSeleccionado2 = boda.getMadrinaPadrino();
        madreEsposoSeleccionada2 = boda.getMadreEsposo();
        padreEsposoSeleccionado2 = boda.getPadreEsposo();
        madreEsposaSeleccionada2 = boda.getMadreEsposa();
        padreEsposaSeleccionada2 = boda.getPadreEsposa();
        
        if (direccionSeleccionada2 != null || personaSeleccionada2 != null || parroquiaSeleccionada2 != null || 
                sacerdoteSeleccionado2 != null || esposaSeleccionada2 != null || esposoSeleccionado2 !=null ||
                padrinoSeleccionado2 !=null || madreEsposoSeleccionada2 !=null || padreEsposoSeleccionado2 != null ||
                madreEsposaSeleccionada2 != null || padreEsposaSeleccionada2 != null){
            try{
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada2);
                boda.setIdDireccion(idDireccion);

                String idUnion = personaDao.obtenerCedulaPorNombre(personaSeleccionada2);
                boda.setCedulaPersona(idUnion);
                String idEsposa = personaDao.obtenerCedulaPorNombre(esposaSeleccionada2);
                boda.setIdEsposa(idEsposa);
                String idEsposo = personaDao.obtenerCedulaPorNombre(esposoSeleccionado2);
                boda.setIdEsposo(idEsposo);
                String idPadrino = personaDao.obtenerCedulaPorNombre(padrinoSeleccionado2);
                boda.setIdPadrino(idPadrino);
                String idMadreEsposo = personaDao.obtenerCedulaPorNombre(madreEsposoSeleccionada2);
                boda.setIdMadreEsposo(idMadreEsposo);
                String idPadreEsposo = personaDao.obtenerCedulaPorNombre(padreEsposoSeleccionado2);
                boda.setIdPadreEsposo(idPadreEsposo);
                String idMadreEsposa = personaDao.obtenerCedulaPorNombre(madreEsposaSeleccionada2);
                boda.setIdMadreEsposa(idMadreEsposa);
                String idPadreEsposa = personaDao.obtenerCedulaPorNombre(padreEsposaSeleccionada2);
                boda.setIdPadreEsposa(idPadreEsposa);

                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada2);
                boda.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado2);
                boda.setIdSacerdote(idSacerdote);
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            bodaDao.actualizar(boda); 
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/sacramento/boda.xhtml");
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
    
    public List<Persona> buscarPersonas(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarBodaPersona(query);
        } catch (SQLException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public List<Persona> buscarEsposo(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarEsposo(query);
        } catch (SQLException e) {
            // Manejar la excepción apropiadamente (mostrar un mensaje de error, por ejemplo)
        }
        return null;
    }
    
    public List<Persona> buscarEsposa(String query) {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarEsposa(query);
        } catch (SQLException e) {
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
    
    public List<Persona> buscarTestigo(String query) throws ClassNotFoundException {
        try {
            personaDao = new PersonaDao();
            return personaDao.buscarPersonas(query);
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
    
    public void cargarBoda(){
        boda = bodaDao.getById(idBoda);
    }
    
    public void retroceder() {
        // Mostrar el buscador nuevamente
        mostrandoTabla = false;
        estado = "buscando";
        registros = new ArrayList<>();
    }
    
    public void crearBoda() throws SQLException{
        Boda nuevaBoda = new Boda();
        nuevaBoda.setFechaBoda(nuevaFecha);
        
        if (direccionSeleccionada != null || personaSeleccionada != null || parroquiaSeleccionada != null || 
                sacerdoteSeleccionado != null || esposaSeleccionada != null || esposoSeleccionado !=null ||
                padrinoSeleccionado !=null || madreEsposoSeleccionada !=null || padreEsposoSeleccionado != null ||
                madreEsposaSeleccionada != null || padreEsposaSeleccionada != null){
            try{
                int idDireccion = direccionDao.obtenerIdDireccionPorNombre(direccionSeleccionada);
                boda.setIdDireccion(idDireccion);

                String idUnion = personaDao.obtenerCedulaPorNombre(personaSeleccionada);
                boda.setCedulaPersona(idUnion);
                String idEsposa = personaDao.obtenerCedulaPorNombre(esposaSeleccionada);
                boda.setIdEsposa(idEsposa);
                String idEsposo = personaDao.obtenerCedulaPorNombre(esposoSeleccionado);
                boda.setIdEsposo(idEsposo);
                String idPadrino = personaDao.obtenerCedulaPorNombre(padrinoSeleccionado);
                boda.setIdPadrino(idPadrino);
                String idMadreEsposo = personaDao.obtenerCedulaPorNombre(madreEsposoSeleccionada);
                boda.setIdMadreEsposo(idMadreEsposo);
                String idPadreEsposo = personaDao.obtenerCedulaPorNombre(padreEsposoSeleccionado);
                boda.setIdPadreEsposo(idPadreEsposo);
                String idMadreEsposa = personaDao.obtenerCedulaPorNombre(madreEsposaSeleccionada);
                boda.setIdMadreEsposa(idMadreEsposa);
                String idPadreEsposa = personaDao.obtenerCedulaPorNombre(padreEsposaSeleccionada);
                boda.setIdPadreEsposa(idPadreEsposa);

                int idParroquia = parroquiaDao.obtenerIdParroquiaPorNombre(parroquiaSeleccionada);
                boda.setIdParroquia(idParroquia);
                int idSacerdote = sacerdoteDao.obtenerIdSacerdote(sacerdoteSeleccionado2);
                boda.setIdSacerdote(idSacerdote);
            } catch (ClassNotFoundException e) {
                // Manejar la excepción apropiadamente
            }
            
            bodaDao.insert(boda); 
        }
    }
    
    public void redirigirEditar(int idBoda) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("bodaEdit.xhtml?faces-redirect=true&id=" + idBoda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear(){
        return "bodaCrear.xhtml?faces-redirect=true";
    }

    // Método privado para redirigir a una URL específica
    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
}
