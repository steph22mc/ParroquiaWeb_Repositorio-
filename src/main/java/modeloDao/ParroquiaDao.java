/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import config.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Parroquia;

/**
 *
 * @author DELL
 */
public class ParroquiaDao {
    private Connection connection;

    public ParroquiaDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Parroquia parroquia) {
        String query = "{CALL InsertarParroquia(?, ?)}";
        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, parroquia.getIdDireccion());
            statement.setString(2, parroquia.getNombreParroquia());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Parroquia parroquia) {
        String query = "{CALL ActualizarParroquia(?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(query)) {
            statement.setInt(1, parroquia.getIdParroquia());
            statement.setInt(2, parroquia.getIdDireccion());
            statement.setString(3, parroquia.getNombreParroquia());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int idParroquia) {
        String query = "DELETE FROM TB_Parroquia WHERE Id_Parroquia = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idParroquia);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Parroquia getById(int idParroquia) {
        String query = "SELECT * FROM V_Parroquia WHERE Id_Parroquia = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idParroquia);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Parroquia parroquia = new Parroquia();
                parroquia.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                parroquia.setIdDireccion(resultSet.getInt("Id_Direccion"));
                parroquia.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                parroquia.setDireccion(resultSet.getString("Direccion"));
                return parroquia;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Parroquia> getAll() {
        String query = "SELECT * FROM V_Parroquia";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Parroquia> parroquias = new ArrayList<>();
            while (resultSet.next()) {
                Parroquia parroquia = new Parroquia();
                parroquia.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                parroquia.setIdDireccion(resultSet.getInt("Id_Direccion"));
                parroquia.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                parroquia.setDireccion(resultSet.getString("Direccion"));
                parroquias.add(parroquia);
            }
            return parroquias;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Parroquia> buscarParroquia(String searchText) throws SQLException{
        List<Parroquia> parroquiasFiltradas = new ArrayList<>();
        
        String query = "SELECT * FROM V_Parroquia WHERE Nombre_Parroquia LIKE ?";
        
        try(PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            statement.setString(1, searchTerm);
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Parroquia parroquia = new Parroquia();
                    parroquia.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                    parroquia.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    parroquia.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                    parroquia.setDireccion(resultSet.getString("Direccion"));
                    
                    parroquiasFiltradas.add(parroquia);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return parroquiasFiltradas;
        }
        
    }
    
    public int obtenerIdParroquiaPorNombre(String nombreParroquia) throws SQLException{
        String query = "SELECT Id_Parroquia FROM V_Parroquia WHERE Nombre_Parroquia = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, nombreParroquia);
            
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("Id_Parroquia");
                }
            }
        }
        return -1;
    }
}
