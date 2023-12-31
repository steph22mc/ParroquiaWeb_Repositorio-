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
import modelo.Sacerdote;

/**
 *
 * @author DELL
 */
public class SacerdoteDao {
    private Connection connection;

    public SacerdoteDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarSacerdote(String rango, String cedulaPersona) {
        String query = "CALL InsertarSacerdote(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, rango);
            statement.setString(2, cedulaPersona);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarSacerdote(int idSacerdote, String rango, String cedulaPersona) {
        String query = "SELECT ActualizarSacerdote(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSacerdote);
            statement.setString(2, rango);
            statement.setString(3, cedulaPersona);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarSacerdote(int idSacerdote) {
        String query = "DELETE FROM TB_Sacerdote WHERE Id_Sacerdote = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSacerdote);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sacerdote> listarSacerdotes() {
        List<Sacerdote> sacerdotes = new ArrayList<>();
        String query = "SELECT * FROM V_Sacerdote_Lista";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Sacerdote sacerdote = new Sacerdote();
                sacerdote.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                sacerdote.setPrimerNombre(resultSet.getString("Primer_Nombre"));
                sacerdote.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                sacerdote.setRango(resultSet.getString("Rango"));
                sacerdote.setCedulaPersona(resultSet.getString("Cedula_Persona"));

                sacerdotes.add(sacerdote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sacerdotes;
    }
}
