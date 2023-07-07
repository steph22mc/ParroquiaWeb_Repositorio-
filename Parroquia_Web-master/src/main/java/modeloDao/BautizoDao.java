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
import modelo.Bautizo;

/**
 *
 * @author DELL
 */
public class BautizoDao {
    private Connection connection;

    public BautizoDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bautizo> getAll() {
        String query = "CALL Listar_Bautizo()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Bautizo> bautizos = new ArrayList<>();
            while (resultSet.next()) {
                Bautizo bautizo = new Bautizo();
                bautizo.setIdBautizo(resultSet.getInt("Id_Bautizo"));
                bautizo.setFechaBautizo(resultSet.getDate("Fecha_Bautizo"));
                bautizo.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                bautizo.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                bautizo.setIdDireccion(resultSet.getInt("Id_Direccion"));
                bautizo.setDireccion(resultSet.getString("Direccion"));
                bautizo.setIdMadrina(resultSet.getString("Id_Madrina"));
                bautizo.setMadrina(resultSet.getString("Madrina"));
                bautizo.setIdPadrino(resultSet.getString("Id_Padrino"));
                bautizo.setPadrino(resultSet.getString("Padrino"));
                bautizo.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                bautizo.setPersona(resultSet.getString("Persona"));
                bautizo.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                bautizo.setSacerdote(resultSet.getString("Sacerdote"));
                bautizos.add(bautizo);
            }
            return bautizos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Bautizo bautizo) {
        String query = "CALL InsertarBautizo(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, (Date) bautizo.getFechaBautizo());
            statement.setInt(2, bautizo.getIdParroquia());
            statement.setInt(3, bautizo.getIdDireccion());
            statement.setString(4, bautizo.getIdMadrina());
            statement.setString(5, bautizo.getIdPadrino());
            statement.setString(6, bautizo.getCedulaPersona());
            statement.setInt(7, bautizo.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int update(Bautizo bautizo) {
        String query = "CALL ActualizarBautizo(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bautizo.getIdBautizo());
            statement.setDate(2, (Date) bautizo.getFechaBautizo());
            statement.setInt(3, bautizo.getIdParroquia());
            statement.setInt(4, bautizo.getIdDireccion());
            statement.setString(5, bautizo.getIdMadrina());
            statement.setString(6, bautizo.getIdPadrino());
            statement.setString(7, bautizo.getCedulaPersona());
            statement.setInt(8, bautizo.getIdSacerdote());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void delete(int idBautizo) throws SQLException {
        PreparedStatement statement = null;

        try {
            // Establecer la conexión a la base de datos
            Connection connection2 = Conexion.getConnection("Sacerdote");

            // Preparar la sentencia SQL
            String sql = "DELETE FROM TB_Bautizo WHERE Id_Bautizo = ?";
            statement = connection2.prepareStatement(sql);
            statement.setInt(1, idBautizo);

            // Ejecutar la sentencia SQL
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Bautizo eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún bautizo con el ID proporcionado.");
            }
        } finally {
            // Cerrar el statement y la conexión
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public List<Bautizo> buscarBautizo(String searchText) throws SQLException{
        List<Bautizo> bautizosFiltrados = new ArrayList<>();
        
        String query = "SELECT * FROM VistaBautizos WHERE Cedula_Persona LIKE ? OR Persona LIKE ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            for (int i = 1; i <= 2; i++) {
                statement.setString(i, searchTerm);
            }
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Bautizo bautizo = new Bautizo();
                    bautizo.setIdBautizo(resultSet.getInt("Id_Bautizo"));
                    bautizo.setFechaBautizo(resultSet.getDate("Fecha_Bautizo"));
                    bautizo.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                    bautizo.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                    bautizo.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    bautizo.setDireccion(resultSet.getString("Direccion"));
                    bautizo.setIdMadrina(resultSet.getString("Id_Madrina"));
                    bautizo.setMadrina(resultSet.getString("Madrina"));
                    bautizo.setIdPadrino(resultSet.getString("Id_Padrino"));
                    bautizo.setPadrino(resultSet.getString("Padrino"));
                    bautizo.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    bautizo.setPersona(resultSet.getString("Persona"));
                    bautizo.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                    bautizo.setSacerdote(resultSet.getString("Sacerdote"));
                    
                    bautizosFiltrados.add(bautizo);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
             return bautizosFiltrados;
        }
    }
    
    public Bautizo getById(int idBautizo){
        String query = "SELECT * FROM VistaBautizos WHERE Id_Bautizo = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idBautizo);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                Bautizo bautizo = new Bautizo();
                bautizo.setIdBautizo(resultSet.getInt("Id_Bautizo"));
                bautizo.setFechaBautizo(resultSet.getDate("Fecha_Bautizo"));
                bautizo.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                bautizo.setNombreParroquia(resultSet.getString("Nombre_Parroquia"));
                bautizo.setIdDireccion(resultSet.getInt("Id_Direccion"));
                bautizo.setDireccion(resultSet.getString("Direccion"));
                bautizo.setIdMadrina(resultSet.getString("Id_Madrina"));
                bautizo.setMadrina(resultSet.getString("Madrina"));
                bautizo.setIdPadrino(resultSet.getString("Id_Padrino"));
                bautizo.setPadrino(resultSet.getString("Padrino"));
                bautizo.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                bautizo.setPersona(resultSet.getString("Persona"));
                bautizo.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                bautizo.setSacerdote(resultSet.getString("Sacerdote"));
                
                return bautizo;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
            return null;
    }
}
