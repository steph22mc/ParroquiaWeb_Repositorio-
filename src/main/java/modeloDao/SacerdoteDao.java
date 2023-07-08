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

    public void insertarSacerdote(Sacerdote sacerdote) {
        String query = "CALL InsertarSacerdote(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, sacerdote.getRango());
            statement.setString(2, sacerdote.getCedulaPersona());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarSacerdote(Sacerdote sacerdote) {
        String query = "SELECT ActualizarSacerdote(?, ?, ?) AS result";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sacerdote.getIdSacerdote());
            statement.setString(2, sacerdote.getRango());
            statement.setString(3, sacerdote.getCedulaPersona());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int rowsAffected = resultSet.getInt("result");
                // Verificar el número de filas afectadas y realizar las acciones necesarias
            }
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
    
    public List<Sacerdote> buscarSacerdote(String searchText) throws SQLException{
        List<Sacerdote> sacerdotesFiltrados = new ArrayList<>();
        
        String query = "SELECT * FROM Vista_Sacerdotes WHERE Sacerdote LIKE ? OR Primer_Nombre LIKE ? OR Primer_Apellido LIKE ? OR Rango LIKE ? OR Cedula_Persona LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            for (int i = 1; i <= 5; i++) {
                statement.setString(i, searchTerm);
            }
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Sacerdote sacerdote = new Sacerdote();
                    sacerdote.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                    sacerdote.setSacerdote(resultSet.getString("Sacerdote"));
                    sacerdote.setPrimerNombre(resultSet.getString("Primer_Nombre"));
                    sacerdote.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                    sacerdote.setRango(resultSet.getString("Rango"));
                    sacerdote.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    
                    sacerdotesFiltrados.add(sacerdote);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            
            return sacerdotesFiltrados;
        }
    }
    
    public int obtenerIdSacerdote(String sacerdote) throws SQLException{
        String query = "SELECT Id_Sacerdote FROM Vista_Sacerdotes WHERE Sacerdote = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, sacerdote);
            
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("Id_Sacerdote");
                }
            }
        }
        return -1;
    }
    
    public Sacerdote getById(int idSacerdote){
        String query = "SELECT * FROM Vista_Sacerdotes WHERE Id_Sacerdote = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idSacerdote);
            
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                Sacerdote sacerdote = new Sacerdote();
                sacerdote.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                sacerdote.setSacerdote(resultSet.getString("Sacerdote"));
                sacerdote.setPrimerNombre(resultSet.getString("Primer_Nombre"));
                sacerdote.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                sacerdote.setRango(resultSet.getString("Rango"));
                
                sacerdote.setCedulaPersona(resultSet.getString("Cedula_Persona"));


                
                return sacerdote;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
