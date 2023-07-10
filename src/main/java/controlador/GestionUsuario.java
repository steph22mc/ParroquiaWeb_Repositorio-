/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hash_bcrypt.PasswordEncrypt;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modeloDao.UsuarioDao;

/**
 *
 * @author DELL
 */
@Named
@RequestScoped
public class GestionUsuario {
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private List<Usuario> usuarios;
    private int idUsuario;
    
    private PasswordEncrypt crypt;
    
    private String nuevoNombre;
    private String nuevoApellido;
    private String nuevoSegundoApellido;
    private String nuevoUser;
    private String nuevaContrasena;
    private String nuevoCorreo;
    private String nuevoTipo;
    
    private String contrasenaUpdate;
    private String confirmarContrasena;
    
    private String searchText;
    
    private String userEliminar;
    
    private int idUserLogin;

    public GestionUsuario() {
        usuarioDao = new UsuarioDao();
        
        
        // Obtener el tipo de usuario desde la base de datos utilizando el UsuarioDao
        idUserLogin = usuarioLogin();
        usuarios = usuarioDao.listaUsuarios(idUserLogin);
        
        crypt = new PasswordEncrypt();
    }
    
    public int usuarioLogin(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        
        // Obtener el Id del usuario de la sesión
        String nombreUser =  (String) session.getAttribute("usuario");
        
        return usuarioDao.obtenerIdPorUsuario(nombreUser);
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getNuevoApellido() {
        return nuevoApellido;
    }

    public void setNuevoApellido(String nuevoApellido) {
        this.nuevoApellido = nuevoApellido;
    }

    public String getNuevoUser() {
        return nuevoUser;
    }

    public void setNuevoUser(String nuevoUser) {
        this.nuevoUser = nuevoUser;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(String nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public String getNuevoSegundoApellido() {
        return nuevoSegundoApellido;
    }

    public void setNuevoSegundoApellido(String nuevoSegundoApellido) {
        this.nuevoSegundoApellido = nuevoSegundoApellido;
    }
    
    public String getNuevoTipo() {
        return nuevoTipo;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }

    public void setNuevoTipo(String nuevoTipo) {
        this.nuevoTipo = nuevoTipo;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getUserEliminar() {
        return userEliminar;
    }

    public void setUserEliminar(String userEliminar) {
        this.userEliminar = userEliminar;
    }

    public int getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(int idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public String getContrasenaUpdate() {
        return contrasenaUpdate;
    }

    public void setContrasenaUpdate(String contrasenaUpdate) {
        this.contrasenaUpdate = contrasenaUpdate;
    }
    
    public void buscarUsuarios(){
        try {
            idUserLogin = usuarioLogin();
            if (searchText != null && !searchText.isEmpty()) {
                usuarios = usuarioDao.buscarUsuario(searchText, idUserLogin);
            } else {
                usuarios = usuarioDao.listaUsuarios(idUserLogin); 
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void crearUsuario() throws SQLException, ClassNotFoundException{
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nuevoNombre.trim());
        nuevoUsuario.setPrimerApellido(nuevoApellido.trim());
        nuevoUsuario.setSegundoApellido(nuevoSegundoApellido.trim());
        
        nuevoUsuario.setUsuario(nuevoUser.trim());
        nuevoUsuario.setCorreoElectronico(nuevoCorreo.trim());
        
        nuevoUsuario.setContrasena(crypt.encriptarContrasena(nuevaContrasena.trim()));
        
        nuevoUsuario.setTipoUsuario(nuevoTipo);
        
        usuarioDao.insertarUsuario(nuevoUsuario); 
        
        
    }
    
    public void cargarUsuario(){
        usuario = usuarioDao.getById(idUsuario);
    }
    
    public void actualizarUsuario() throws SQLException, ClassNotFoundException{
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (contrasenaUpdate == null || contrasenaUpdate.trim().isEmpty() || confirmarContrasena == null || confirmarContrasena.trim().isEmpty()) {
            // Si las contraseñas están vacías, mostrar mensaje de error
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Las contraseñas no pueden estar vacías", "Las contraseñas no pueden estar vacías");
            facesContext.addMessage(null, message);
        } else if (!contrasenaUpdate.equals(confirmarContrasena)) {
            // Si las contraseñas no coinciden, mostrar mensaje de error
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden");
            facesContext.addMessage(null, message);
        }else{
            usuario.setContrasena(crypt.encriptarContrasena(contrasenaUpdate.trim())); 
            usuarioDao.actualizarUsuario(usuario);
            
            try{
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpSession session = (HttpSession) externalContext.getSession(false);

                // Obtener el nombre de usuario de la sesión
                String nombreUsuario = (String) session.getAttribute("usuario");

                // Obtener el tipo de usuario desde la base de datos utilizando el UsuarioDao
                String tipoUsuario = usuarioDao.obtenerTipoUsuario(nombreUsuario);

                if(tipoUsuario.equals("Sacerdote")){
                    redirigir("/sesion/pagina_principal_sacerdote.xhtml");
                }
                else{
                    redirigir("/sesion/pagina_principal_secretario.xhtml");
                }

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void eliminarUsuario(int idUser){
        try {
            usuarioDao.delete(idUser);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            redirigir("/usuario/usuario.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para obtener el nombre completo del usuario actualmente autenticado
    public String obtenerNombreCompletoUsuario() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            String user = (String) session.getAttribute("usuario");
            String nombreCompleto = usuarioDao.obtenerNombrePorUsuario(user);
            return nombreCompleto;
        }
        return null;
    }
    
    public void redireccionarCrear() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/usuario/crearUsuario.xhtml");
    }
    
    public void redireccionarEditar(int idUsuario) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            redirigir("/usuario/editarUsuario.xhtml?faces-redirect=true&id=" + idUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void redirigirAUsuario() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        redirigir("/usuario/usuario.xhtml");
    }

    private void redirigir(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + url);
    }
    
}
