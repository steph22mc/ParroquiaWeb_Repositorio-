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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Defuncion;

/**
 *
 * @author DELL
 */
public class DefuncionDao {
    private Connection connection;

    public DefuncionDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Defuncion> getAll() {
        String query = "CALL LISTAR_DEFUNCION()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Defuncion> defunciones = new ArrayList<>();
            while (resultSet.next()) {
                Defuncion defuncion = new Defuncion();
                defuncion.setIdDefuncion(resultSet.getInt("Id_Defuncion"));
                defuncion.setEstatusCompromiso(resultSet.getString("Estatus_Compromiso"));
                defuncion.setFechaDefuncion(resultSet.getDate("Fecha_Defuncion"));
                defuncion.setIdPadre(resultSet.getString("Id_Padre"));
                defuncion.setPadre(resultSet.getString("Padre"));
                defuncion.setIdMadre(resultSet.getString("Id_Madre"));
                defuncion.setMadre(resultSet.getString("Madre"));
                defuncion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                defuncion.setDireccion(resultSet.getString("Direccion"));
                defuncion.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                defuncion.setNombre_Parroquia(resultSet.getString("Nombre_Parroquia"));
                defuncion.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                defuncion.setPersona(resultSet.getString("Persona"));
                defuncion.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                defuncion.setSacerdote(resultSet.getString("Sacerdote"));
                defunciones.add(defuncion);
            }
            return defunciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Defuncion defuncion) {
        String query = "CALL InsertarDefuncion(?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, defuncion.getEstatusCompromiso());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(defuncion.getFechaDefuncion());
            
            statement.setString(2, fechaStr);
            statement.setString(3, defuncion.getIdPadre());
            statement.setString(4, defuncion.getIdMadre());
            statement.setInt(5, defuncion.getIdDireccion());
            statement.setInt(6, defuncion.getIdParroquia());
            statement.setString(7, defuncion.getCedulaPersona());
            statement.setInt(8, defuncion.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int actualizar(Defuncion defuncion) {
        String query = "SELECT ActualizarDefuncion(?, ?, ?, ?, ?, ?, ?, ?, ?) AS result";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, defuncion.getIdDefuncion());
            statement.setString(2, defuncion.getEstatusCompromiso());
            
            // Convertir la fecha util.Date a sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(defuncion.getFechaDefuncion());
            
            statement.setString(3, fechaStr);
            statement.setString(4, defuncion.getIdPadre());
            statement.setString(5, defuncion.getIdMadre());
            statement.setInt(6, defuncion.getIdDireccion());
            statement.setInt(7, defuncion.getIdParroquia());
            statement.setString(8, defuncion.getCedulaPersona());
            statement.setInt(9, defuncion.getIdSacerdote());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void delete(int idDefuncion) {
        String query = "DELETE FROM TB_Defuncion WHERE Id_Defuncion = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idDefuncion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Defuncion> buscarDefunciones(String searchText) throws SQLException{
        List<Defuncion> defuncionesFiltradas = new ArrayList<>();
        
        String query = "SELECT * FROM V_Primera_Comunion WHERE Cedula_Persona LIKE ? OR Persona LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            for (int i = 1; i <= 2; i++) {
                statement.setString(i, searchTerm);
            }
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Defuncion defuncion = new Defuncion();
                    defuncion.setIdDefuncion(resultSet.getInt("Id_Defuncion"));
                    defuncion.setEstatusCompromiso(resultSet.getString("Estatus_Compromiso"));
                    defuncion.setFechaDefuncion(resultSet.getDate("Fecha_Defuncion"));
                    defuncion.setIdPadre(resultSet.getString("Id_Padre"));
                    defuncion.setPadre(resultSet.getString("Padre"));
                    defuncion.setIdMadre(resultSet.getString("Id_Madre"));
                    defuncion.setMadre(resultSet.getString("Madre"));
                    defuncion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    defuncion.setDireccion(resultSet.getString("Direccion"));
                    defuncion.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                    defuncion.setNombre_Parroquia(resultSet.getString("Nombre_Parroquia"));
                    defuncion.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    defuncion.setPersona(resultSet.getString("Persona"));
                    defuncion.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                    defuncion.setSacerdote(resultSet.getString("Sacerdote"));
                    
                    defuncionesFiltradas.add(defuncion);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return defuncionesFiltradas;
        }
    }
    
    public Defuncion getById(int idDefuncion){
        String query = "SELECT * FROM V_Primera_Comunion WHERE Id_Defuncion = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idDefuncion);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                Defuncion defuncion = new Defuncion();
                defuncion.setIdDefuncion(resultSet.getInt("Id_Defuncion"));
                defuncion.setEstatusCompromiso(resultSet.getString("Estatus_Compromiso"));
                defuncion.setFechaDefuncion(resultSet.getDate("Fecha_Defuncion"));
                defuncion.setIdPadre(resultSet.getString("Id_Padre"));
                defuncion.setPadre(resultSet.getString("Padre"));
                defuncion.setIdMadre(resultSet.getString("Id_Madre"));
                defuncion.setMadre(resultSet.getString("Madre"));
                defuncion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                defuncion.setDireccion(resultSet.getString("Direccion"));
                defuncion.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                defuncion.setNombre_Parroquia(resultSet.getString("Nombre_Parroquia"));
                defuncion.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                defuncion.setPersona(resultSet.getString("Persona"));
                defuncion.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                defuncion.setSacerdote(resultSet.getString("Sacerdote"));
                
                return defuncion;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
