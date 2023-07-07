/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import hash_bcrypt.PasswordEncrypt;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Usuario;
import modeloDao.ResetPasswordDao;
import modeloDao.UsuarioDao;

/**
 *
 * @author DELL
 */

/**
 * Clase controladora para el restablecimiento de contraseñas.
 * 
 * @Named: Indica que esta clase es un bean administrado por CDI (Contexts and Dependency Injection). Permite que la clase sea reconocida y utilizada por el framework JSF.
 * 
 * @RequestScoped: Define el alcance del bean como el alcance de solicitud. Esto significa que el estado del bean se mantiene durante una sola solicitud HTTP. 
 * Cada nueva solicitud generará una nueva instancia del bean. Es útil cuando se desea que el estado del bean se reinicie en cada solicitud.
 */
@Named
@RequestScoped
public class ResetController {
    private String email;
    private String email2;
    
    private UsuarioDao userDAO; // Inyecta tu clase DAO para acceder a la base de datos
    private ResetPasswordDao resetDao;
    
    private String newPassword;
    private String confirmPassword;
    private boolean showResetForm;
    private boolean showExpiredMessage;
    private boolean showInvalidHashMessage;
    
            
    /**
     * Constructor de la clase. Inicializa las instancias de UsuarioDao y ResetPasswordDao.
     */
    public ResetController() {
        userDAO = new UsuarioDao(); // Inicializa la instancia de UsuarioDao
        resetDao = new ResetPasswordDao(); // Inicializa la instancia de ResetPasswordDao
    }
    
    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isShowInvalidHashMessage() {
        return showInvalidHashMessage;
    }

    public void setShowInvalidHashMessage(boolean showInvalidHashMessage) {
        this.showInvalidHashMessage = showInvalidHashMessage;
    }

    
    
    /**
     * Método para redirigir a la página de "forgotpassword.xhtml".
     * 
     * @throws IOException si ocurre un error al redirigir la página.
     */
    public void redirigirAForgotPassword() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/forgotpassword.xhtml");
    }
    
    /**
     * Método para enviar el enlace de restablecimiento de contraseña al correo electrónico proporcionado.
     * 
     * @throws IOException si ocurre un error al redirigir la página.
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     * @throws ClassNotFoundException si no se encuentra la clase necesaria para la base de datos.
     * @throws AddressException si ocurre un error en la dirección de correo electrónico.
     * @throws MessagingException si ocurre un error al enviar el correo electrónico.
     */
    public void sendPasswordResetLink() throws IOException, SQLException, ClassNotFoundException, AddressException, MessagingException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Verificar si el correo electrónico está presente y no está vacío
        if (email != null && !email.isEmpty()) {
            
            // Verificar si el correo electrónico es válido utilizando una expresión regular
            if (!email.matches("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,4}")) {
                // Correo electrónico no válido, mostrar mensaje de error y redirigir a la página de error
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/invalite_gmail.xhtml");
            }
            
            // Verificar si el correo electrónico existe en la base de datos y obtener el ID del usuario
            Usuario user = userDAO.getUserByEmail(email);

            if (user != null) {
                // Obtener el ID del usuario
                int profileId = user.getIdUsuario();

                // Generar el código y la fecha de expiración
                Timestamp intime = new Timestamp(Calendar.getInstance().getTime().getTime());
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(intime.getTime());
                cal.add(Calendar.MINUTE, 20);
                Timestamp exptime = new Timestamp(cal.getTime().getTime());

                int randNum = (int) (Math.random() * 1000000);
                String rand = Integer.toString(randNum);
                String finale = (rand + "" + intime);

                // Utilizar la clase PasswordEncrypt para cifrar el código hash
                PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
                String hash = passwordEncrypt.encriptarContrasena(finale);

                // Guardar el código de restablecimiento y la fecha de expiración en la base de datos
                resetDao.saveResetPasswordHash(profileId, hash, exptime, intime);

                // Enviar correo electrónico con el enlace de restablecimiento
                sendResetPasswordEmail(email, hash);

                // Redireccionar a la página de éxito
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/reset_password_success.xhtml?valor="+email);
            } else {
                // No existe un correo electrónico similar en la base de datos, mostrar mensaje de error y redirigir a la página de error
                ExternalContext externalContext = facesContext.getExternalContext();
                externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/gmail_notExit.xhtml?valor="+email);
            }
        } else {
            // Mostrar mensaje de error si el correo electrónico no es válido y redirigir a la página de error
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/invalite_gmail.xhtml");
            showErrorMessage("Error", "El correo electrónico no es válido");
        }
    }

    /**
     * Método para enviar el correo electrónico de restablecimiento de contraseña.
     * 
     * @param email el correo electrónico del destinatario.
     * @param hashCode el código de restablecimiento de contraseña.
     * @throws MessagingException si ocurre un error al enviar el correo electrónico.
     */
    private void sendResetPasswordEmail(String email, String hashCode) throws MessagingException {
        String link = "http://localhost:8080/Parroquia_Web-master/sesion/reset_password.xhtml?key=" + hashCode;

        // Configurar las propiedades para la conexión SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Crear la sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mcestefani22@gmail.com", "xtwmqifuiizbiyle");
            }
        });

        // Crear el mensaje de correo
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mcestefani22@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Restablecer Contraseña");
        message.setText("Haga clic en el siguiente enlace para restablecer su contraseña: " + link);

        // Enviar el mensaje de correo
        Transport.send(message);
    }
    
    
    public void redirigirAUpdate() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(externalContext.getRequestContextPath() + "/sesion/update_reset.xhtml");
    }
    
    

    private void showErrorMessage(String title, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }
}
