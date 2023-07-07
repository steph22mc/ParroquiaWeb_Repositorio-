/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Confirmacion;

/**
 *
 * @author DELL
 */
public class ConfirmacionDao {
    private Connection connection;

    public ConfirmacionDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Confirmacion> getAll() {
        String query = "CALL LISTAR_CONFIRMACION()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Confirmacion> confirmaciones = new ArrayList<>();
            while (resultSet.next()) {
                Confirmacion confirmacion = new Confirmacion();
                confirmacion.setIdConfirmacion(resultSet.getInt("Id_Confirmacion"));
                confirmacion.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                confirmacion.setNombre_parroquia(resultSet.getString("Nombre_Parroquia"));
                confirmacion.setIdPadrinoMadrina(resultSet.getString("Id_Padrino_Madrina"));
                confirmacion.setIdPadrinoMadrina(resultSet.getString("Madrina_Padrino"));
                confirmacion.setFechaConfirmacion(resultSet.getDate("Fecha_Confirmacion"));
                confirmacion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                confirmacion.setDireccion(resultSet.getString("Direccion"));
                confirmacion.setIdPadre(resultSet.getString("Id_Padre"));
                confirmacion.setPadre(resultSet.getString("Padre"));
                confirmacion.setIdMadre(resultSet.getString("Id_Madre"));
                confirmacion.setMadre(resultSet.getString("Madre"));
                confirmacion.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                confirmacion.setPersona(resultSet.getString("Persona"));
                confirmacion.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                confirmacion.setSacerdote(resultSet.getString("Sacerdote"));
                confirmaciones.add(confirmacion);
            }
            return confirmaciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Confirmacion confirmacion) {
        String query = "CALL InsertarConfirmacion(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, confirmacion.getIdParroquia());
            statement.setString(2, confirmacion.getIdPadrinoMadrina());
            statement.setDate(3, new java.sql.Date(confirmacion.getFechaConfirmacion().getTime()));
            statement.setInt(4, confirmacion.getIdDireccion());
            statement.setString(5, confirmacion.getIdPadre());
            statement.setString(6, confirmacion.getIdMadre());
            statement.setString(7, confirmacion.getCedulaPersona());
            statement.setInt(8, confirmacion.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int actualizar(Confirmacion confirmacion) {
        String query = "SELECT ActualizarConfirmacion(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, confirmacion.getIdConfirmacion());
            statement.setInt(2, confirmacion.getIdParroquia());
            statement.setString(3, confirmacion.getIdPadrinoMadrina());
            statement.setDate(4, new java.sql.Date(confirmacion.getFechaConfirmacion().getTime()));
            statement.setInt(5, confirmacion.getIdDireccion());
            statement.setString(6, confirmacion.getIdPadre());
            statement.setString(7, confirmacion.getIdMadre());
            statement.setString(8, confirmacion.getCedulaPersona());
            statement.setInt(9, confirmacion.getIdSacerdote());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminar(int idConfirmacion) {
        String query = "DELETE FROM TB_Confirmacion WHERE Id_Confirmacion = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idConfirmacion);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
