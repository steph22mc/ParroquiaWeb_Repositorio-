/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Direccion;
import modeloDao.DireccionDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;
import modeloDao.UsuarioDao;

/**
 *
 * @author DELL
 */

/**
 * Clase controladora para la gestión de direcciones.
 * 
 * @Named: Indica que esta clase es un bean administrado por CDI (Contexts and Dependency Injection). Permite que la clase sea reconocida y utilizada por el framework JSF.
 * 
 * @ViewScoped: Define el alcance del bean como el alcance de vista. Esto significa que el estado del bean se mantiene mientras el usuario interactúa con la vista en la que se encuentra el bean.
 */
@ManagedBean
@ViewScoped
public class DireccionBean implements Serializable{
    private Direccion direccion;
    private DireccionDao direccionDao;
    private List<Direccion> direcciones;
    private int idDireccion;
    
    private String nuevoNombreLugar;
    private String nuevaProvincia;
    private String nuevoDistrito;
    
    
    private String searchText;
    
    private boolean sacerdoteTrue;
    private boolean sacerdoteTrueInitialized = false;

    private String direccionEliminar;
    
    private UsuarioDao userDao = new UsuarioDao();


    /**
     * Constructor de la clase. Inicializa el objeto `DireccionDao` y carga todas las direcciones en la lista `direcciones`.
     */
    public DireccionBean() {
        direccionDao = new DireccionDao();
        direcciones = direccionDao.getAll();
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getNuevoNombreLugar() {
        return nuevoNombreLugar;
    }

    public void setNuevoNombreLugar(String nuevoNombreLugar) {
        this.nuevoNombreLugar = nuevoNombreLugar;
    }

    public String getNuevaProvincia() {
        return nuevaProvincia;
    }

    public void setNuevaProvincia(String nuevaProvincia) {
        this.nuevaProvincia = nuevaProvincia;
    }

    public String getNuevoDistrito() {
        return nuevoDistrito;
    }

    public void setNuevoDistrito(String nuevoDistrito) {
        this.nuevoDistrito = nuevoDistrito;
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

    public String getDireccionEliminar() {
        return direccionEliminar;
    }

    public void setDireccionEliminar(String direccionEliminar) {
        this.direccionEliminar = direccionEliminar;
    }
    
    /**
     * Método para cargar los datos de una dirección en el formulario de edición.
     */
    public void cargarDireccion() {
        direccion = direccionDao.getById(idDireccion);
    }

    /**
     * Método para actualizar los datos de una dirección en la base de datos.
     */
    public void actualizarDireccion() {
        // Realizar la actualización de los datos en la base de datos utilizando el objeto DireccionDao
        direccionDao.update(direccion);

        // Redireccionar a una página de éxito o a la página principal
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/direccion/direccion.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método para crear una nueva dirección y guardarla en la base de datos.
     */
    public void crearDireccion() {
        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setNombreLugar(nuevoNombreLugar);
        nuevaDireccion.setProvincia(nuevaProvincia);
        nuevaDireccion.setDistrito(nuevoDistrito);

        // Lógica para guardar la nueva dirección en la base de datos
        direccionDao.insert(nuevaDireccion);

        // Limpiar los campos después de crear el registro
        nuevoNombreLugar = null;
        nuevaProvincia = null;
        nuevoDistrito = null;
    }
    
    /**
     * Método para buscar direcciones en base a un texto de búsqueda.
     */
    public void buscarDirecciones() {
        try {
            if (searchText != null && !searchText.isEmpty()) {
                direcciones = direccionDao.buscarDirecciones(searchText);
            } else {
                direcciones = direccionDao.getAll();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar una dirección de la base de datos.
     * 
     * @param idDireccion el ID de la dirección a eliminar.
     */
    public void eliminarDireccion(int idDireccion) {
        try {
            direccionDao.delete(idDireccion);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/direccion/direccion.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public void loadDirecciones() {
        direcciones = direccionDao.getAll();
    }
    
    public void redireccionarEditar(int idDireccion) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        int dd = idDireccion;
        try {
            externalContext.redirect("editarDireccion.xhtml?faces-redirect=true&id=" + idDireccion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String redireccionarCrear() {
        return "crearDireccion.xhtml?faces-redirect=true";
    }
    
    public void redirigirADireccion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/direccion/direccion.xhtml");
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
