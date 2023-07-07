/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_manager;

import config.Conexion;
import hash_bcrypt.PasswordEncrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class LoginManager {
    private PasswordEncrypt passwordEncrypt; // Objeto para cifrar contraseñas
    public String error;

    public LoginManager() {
        this.passwordEncrypt = new PasswordEncrypt(); // Inicialización del objeto PasswordEncrypt
    }
    
    /**
     * Autentica un usuario en el sistema.
     * @param usuario  es el Nombre de usuario
     * @param contrasena es la Contraseña del usuario
     * @return retorna true si la autenticación fue exitosa, false en caso de que no lo fue
     */
    public boolean autenticarUsuario(String usuario, String contrasena) throws ClassNotFoundException {
        String sql = "SELECT * FROM Vw_Usuarios WHERE Usuario = ? LIMIT 1";// Consulta SQL para obtener información del usuario
        try{ Connection connection = Conexion.getConnection(getTipoUsuario(usuario)); // Establecer conexión a la base de datos
            if (connection != null) { // Verificar si se obtuvo una conexión válida
                PreparedStatement statement = connection.prepareStatement(sql); // Preparar la consulta SQL
                statement.setString(1, usuario); // Establece el valor del parámetro en la consulta
                
                ResultSet resultSet = statement.executeQuery(); // Ejecutar la consulta y obtener los resultados
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("Contrasena");
                    // Verificar si la contraseña proporcionada coincide con la almacenada
                    if(passwordEncrypt.verificarContrasena(contrasena, hashedPassword)){
                        return true;
                    }
     
                }
            } else {
                System.out.println("No se pudo obtener una conexión válida.");
            }
           
        } catch (SQLException e) { // Capturar excepciones de SQL
            e.printStackTrace(); // Imprimir la excepción
        }
        return false; // Autenticación fallida
    }
    
    /**
     * Obtiene el tipo de usuario de acuerdo al nombre de usuario pasado por parametro.
     * @param usuario es el Nombre de usuario
     * @return  retorna el Tipo de usuario o retorna null si no se encuentra
     */
    public String getTipoUsuario(String usuario) throws ClassNotFoundException {
        String query = "SELECT TipoUsuario FROM Vw_Usuarios WHERE Usuario = ?";
        try (Connection connection = Conexion.getConnection("Secretario")) {
            if (connection != null) { // Verificar si se obtuvo una conexión válida
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, usuario);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getString("TipoUsuario");
                } else {
                    // El usuario no se encontró en la tabla
                    throw new IllegalArgumentException("Usuario no válido");
                }
            } else {
                throw new IllegalArgumentException("No se pudo obtener una conexión válida.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la excepción en caso de error de base de datos
        }
        return null;
    }
}
