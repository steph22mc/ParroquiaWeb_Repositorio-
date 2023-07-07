/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Comunion;

/**
 *
 * @author DELL
 */
public class ComunionDao {
    private Connection connection;

    public ComunionDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Comunion> getAll() {
        String query = "CALL LISTA_COMUNION()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Comunion> comuniones = new ArrayList<>();
            while (resultSet.next()) {
                Comunion comunion = new Comunion();
                comunion.setIdComunion(resultSet.getInt("Id_Primera_Comunion"));
                comunion.setIdPadre(resultSet.getString("Id_Padre"));
                comunion.setPadre(resultSet.getString("Padre"));
                comunion.setIdMadre(resultSet.getString("Id_Madre"));
                comunion.setMadre(resultSet.getString("Madre"));
                comunion.setFechaComunion(resultSet.getDate("Fecha_Primera_Comunion"));
                comunion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                comunion.setDireccion(resultSet.getString("Direccion"));
                comunion.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                comunion.setParroquia(resultSet.getString("Nombre_Parroquia"));
                comunion.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                comunion.setPersona(resultSet.getString("Persona"));
                comunion.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                comunion.setSacerdote(resultSet.getString("Sacerdote")); 
                comuniones.add(comunion);
            }
            return comuniones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Comunion comunion) {
        String query = "CALL InsertarPrimeraComunion(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, comunion.getIdPadre());
            statement.setString(2, comunion.getIdMadre());
            statement.setDate(3, (Date) comunion.getFechaComunion());
            statement.setInt(4, comunion.getIdDireccion());
            statement.setInt(5, comunion.getIdParroquia());
            statement.setString(6, comunion.getCedulaPersona());
            statement.setInt(7, comunion.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar(Comunion comunion) {
        String query = "CALL ActualizarPrimeraComunion(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, comunion.getIdComunion());
            statement.setString(2, comunion.getIdPadre());
            statement.setString(3, comunion.getIdMadre());
            statement.setDate(4, (Date) comunion.getFechaComunion());
            statement.setInt(5, comunion.getIdDireccion());
            statement.setInt(6, comunion.getIdParroquia());
            statement.setString(7, comunion.getCedulaPersona());
            statement.setInt(8, comunion.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int idComunion) {
        String query = "DELETE FROM TB_Primera_Comunion WHERE Id_Primera_Comunion = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idComunion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
