/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
/**
 * Clase que maneja la conexión a una base de datos.
 */
public class Conexion {
    private static final String BD = "DB_Parroquia"; // Nombre de la base de datos
    private static final String PUERTO = "3306"; // Puerto de la base de datos
    private static final String URL = "jdbc:mysql://localhost:" + PUERTO + "/" + BD; // URL de conexión a la base de datos
    private static final Logger logger = Logger.getLogger(Conexion.class.getName());

    private static Map<String, Credenciales> credencialesMap = new HashMap<>();

    static {
        // Agregar las credenciales del sacerdote
        credencialesMap.put("Sacerdote", new Credenciales("usuario_sacerdote", "3mmanu3lD105"));

        // Agregar las credenciales del secretario
        credencialesMap.put("Secretario", new Credenciales("usuario_secretario", "J35ucr1$t0s"));
    }

    private Conexion() {
    } // Constructor privado para evitar instanciación de la clase

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @param tipoUsuario el tipo de usuario para el cual se requiere la conexión
     * @return la conexión establecida
     * @throws SQLException si ocurre un error al establecer la conexión
     */
    public static Connection getConnection(String tipoUsuario) throws SQLException {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuario no puede ser nulo");
        }

        Credenciales credenciales = obtenerCredenciales(tipoUsuario);
        String username = credenciales.getUsername();
        String password = credenciales.getPassword();

        Connection connection = null;
        try {
            // Cargar el controlador de la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, username, password);
            logger.log(Level.INFO, "Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            // Si ocurre un error, registrar el error y lanzar una excepción
            logger.log(Level.SEVERE, "Error al establecer la conexión a la base de datos", e);
            throw new SQLException("Error al establecer la conexión a la base de datos");
        }

        return connection;
    }

    /**
     * Cierra una conexión a la base de datos.
     *
     * @param connection la conexión a cerrar
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                // Cerrar la conexión
                connection.close();
                logger.log(Level.INFO, "Conexión cerrada correctamente.");
            } catch (SQLException e) {
                // Si ocurre un error al cerrar la conexión, registrar el error
                logger.log(Level.SEVERE, "Error al cerrar la conexión a la base de datos", e);
            }
        } else {
            logger.log(Level.WARNING, "No hay una conexión abierta para cerrar.");
        }
    }

    private static Credenciales obtenerCredenciales(String tipoUsuario) {
        Credenciales credenciales = credencialesMap.get(tipoUsuario);
        if (credenciales == null) {
            throw new IllegalArgumentException("Tipo de usuario no válido");
        }
        return credenciales;
    }

    /**
     * Clase interna que representa las credenciales de un usuario.
     */
    private static class Credenciales {
        private String username;
        private String password;

        public Credenciales(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
