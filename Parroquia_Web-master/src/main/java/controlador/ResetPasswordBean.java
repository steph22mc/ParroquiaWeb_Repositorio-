/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.Conexion;
import hash_bcrypt.PasswordEncrypt;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author DELL
 */

/**
 * Clase controladora para el restablecimiento de contraseñas.
 * 
 * @Named: Indica que esta clase es un bean administrado por CDI (Contexts and Dependency Injection). Permite que la clase sea reconocida y utilizada por el framework JSF.
 * 
 * @ViewScoped: Define el alcance del bean como el alcance de vista. Esto significa que el estado del bean se mantiene durante la vida útil de la vista actual.
 * La vista puede consistir en múltiples solicitudes HTTP. Es útil cuando se desea mantener el estado del bean mientras el usuario interactúa con la vista.
 */
@Named
@ViewScoped
public class ResetPasswordBean implements Serializable{
    
    private String hash;
    private int profileId;
    private boolean showResetForm;
    private boolean showExpiredMessage;
    private boolean showHashMismatchMessage;
    private String newPassword;
    private String confirmPassword;

    public boolean isShowResetForm() {
        return showResetForm;
    }

    public void setShowResetForm(boolean showResetForm) {
        this.showResetForm = showResetForm;
    }

    public boolean isShowExpiredMessage() {
        return showExpiredMessage;
    }

    public void setShowExpiredMessage(boolean showExpiredMessage) {
        this.showExpiredMessage = showExpiredMessage;
    }

    public boolean isShowHashMismatchMessage() {
        return showHashMismatchMessage;
    }

    public void setShowHashMismatchMessage(boolean showHashMismatchMessage) {
        this.showHashMismatchMessage = showHashMismatchMessage;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Constructor de la clase. Realiza la lógica de negocios para validar el código de restablecimiento.
     * 
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public ResetPasswordBean() throws SQLException {
        // Obtener parámetro de la solicitud
        hash = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("key");

        // Obtener marca de tiempo actual
        Calendar cal = Calendar.getInstance();
        java.util.Date curTime = cal.getTime();
        java.sql.Timestamp curTimestamp = new java.sql.Timestamp(curTime.getTime());

        try (Connection con = Conexion.getConnection("Secretario");
                PreparedStatement st = con.prepareStatement("SELECT Id_Usuario, Exptime FROM Reset_Password WHERE Hash_code = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            st.setString(1, hash);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.first()) {
                    profileId = rs.getInt("Id_Usuario");
                    java.sql.Timestamp expTime = rs.getTimestamp("Exptime");

                    // Comparar marcas de tiempo
                    if (curTimestamp.before(expTime)) {
                        showResetForm = true;
                    } else {
                        showExpiredMessage = true;
                    }
                } else {
                    showHashMismatchMessage = true;
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * Método para restablecer la contraseña del usuario.
     * 
     * @throws IOException si ocurre un error al redirigir la página.
     */
    public void resetPassword() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (showHashMismatchMessage) {
            // Si hay un error de hash, redirigir a la página de error
            ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/invalite_gmail.xhtml");
        }
        showResetForm = true;

        PasswordEncrypt md = new PasswordEncrypt();

        if (newPassword == null || newPassword.trim().isEmpty() || confirmPassword == null || confirmPassword.trim().isEmpty()) {
            // Si las contraseñas están vacías, mostrar mensaje de error
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Las contraseñas no pueden estar vacías", "Las contraseñas no pueden estar vacías");
            facesContext.addMessage(null, message);
        } else if (!newPassword.equals(confirmPassword)) {
            // Si las contraseñas no coinciden, mostrar mensaje de error
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden");
            facesContext.addMessage(null, message);
        } else {
            try {
                Connection con = Conexion.getConnection("Sacerdote");
                String hashedPassword = md.encriptarContrasena(newPassword);
                PreparedStatement st = con.prepareStatement("UPDATE TB_Usuario SET Contrasena=? WHERE Id_Usuario=?");
                st.setString(1, hashedPassword);
                st.setInt(2, profileId);
                st.executeUpdate();

                String sql = "DELETE FROM Reset_Password WHERE Id_Usuario = ?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, profileId);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Perfil de restablecimiento de contraseña eliminado correctamente.");
                } else {
                    System.out.println("No se encontró un perfil de restablecimiento de contraseña con el perfil ID especificado.");
                }

                // Establecer showResetForm en true después del restablecimiento de contraseña
                showResetForm = true;

                // Redireccionar a la página de inicio de sesión
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/update_reset.xhtml");
            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     
}
