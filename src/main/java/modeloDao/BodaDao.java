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
import modelo.Boda;

/**
 *
 * @author DELL
 */
public class BodaDao {
    private Connection connection;

    public BodaDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Boda> getAll() {
        List<Boda> bodas = new ArrayList<>();
    
    try {
        
        // Crea la consulta SQL
        String query = "CALL LISTAR_BODA()";
        
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            Boda boda = new Boda();
            boda.setIdBoda(resultSet.getInt("Id_Boda"));
            boda.setIdEsposo(resultSet.getString("Id_Esposo"));
            boda.setEsposo(resultSet.getString("Esposo"));
            boda.setIdEsposa(resultSet.getString("Id_Esposa"));
            boda.setEsposa(resultSet.getString("Esposa"));
            boda.setFechaBoda(resultSet.getDate("Fecha_Boda"));
            boda.setIdDireccion(resultSet.getInt("Id_Direccion"));
            boda.setDireccion(resultSet.getString("Direccion"));
            boda.setIdParroquia(resultSet.getInt("Id_Parroquia"));
            boda.setParroquia(resultSet.getString("Nombre_Parroquia"));
            boda.setIdPadrino(resultSet.getString("Id_Padrino"));
            boda.setMadrinaPadrino(resultSet.getString("Madrina_Padrino"));
            boda.setIdMadreEsposo(resultSet.getString("Id_Madre_Esposo"));
            boda.setMadreEsposo(resultSet.getString("Madre_Esposo"));
            boda.setIdPadreEsposo(resultSet.getString("Id_Padre_Esposo"));
            boda.setPadreEsposo(resultSet.getString("Padre_Esposo"));
            boda.setIdMadreEsposa(resultSet.getString("Id_Madre_Esposa"));
            boda.setMadreEsposa(resultSet.getString("Madre_Esposa"));
            boda.setIdPadreEsposa(resultSet.getString("Id_Padre_Esposa"));
            boda.setPadreEsposa(resultSet.getString("Padre_Esposa"));
            boda.setPersona(resultSet.getString("Persona"));
            boda.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
            boda.setSacerdote(resultSet.getString("Sacerdote"));
            
            bodas.add(boda);
        }
        
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return bodas;
    }

    public void insert(Boda boda) {
        String query = "CALL InsertarBoda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, boda.getIdEsposo());
            statement.setString(2, boda.getIdEsposa());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(boda.getFechaBoda());
            statement.setString(3, fechaStr);
            statement.setInt(4, boda.getIdDireccion());
            statement.setInt(5, boda.getIdParroquia());
            statement.setString(6, boda.getIdPadrino());
            statement.setString(7, boda.getIdMadreEsposo());
            statement.setString(8, boda.getIdPadreEsposo());
            statement.setString(9, boda.getIdMadreEsposa());
            statement.setString(10, boda.getIdPadreEsposa());
            statement.setString(11, boda.getCedulaPersona());
            statement.setInt(12, boda.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar(Boda boda) {
        String query = "CALL ActualizarBoda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, boda.getIdBoda());
            statement.setString(2, boda.getIdEsposo());
            statement.setString(3, boda.getIdEsposa());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaStr = sdf.format(boda.getFechaBoda());
            statement.setString(4, fechaStr);
            statement.setInt(5, boda.getIdDireccion());
            statement.setInt(6, boda.getIdParroquia());
            statement.setString(7, boda.getIdPadrino());
            statement.setString(8, boda.getIdMadreEsposo());
            statement.setString(9, boda.getIdPadreEsposo());
            statement.setString(10, boda.getIdMadreEsposa());
            statement.setString(11, boda.getIdPadreEsposa());
            statement.setString(12, boda.getCedulaPersona());
            statement.setInt(13, boda.getIdSacerdote());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int idBoda) {
        String query = "DELETE FROM TB_Boda WHERE Id_Boda = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idBoda);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Boda> buscarBoda(String searchText) throws SQLException{
        List<Boda> bodasFiltradas = new ArrayList<>();
        String query = "SELECT * FROM V_Boda WHERE Cedula_Persona LIKE ? OR Persona LIKE ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            for (int i = 1; i <= 2; i++) {
                statement.setString(i, searchTerm);
            }
            
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()){
                    Boda boda = new Boda();
                    boda.setIdBoda(resultSet.getInt("Id_Boda"));
                    boda.setIdEsposo(resultSet.getString("Id_Esposo"));
                    boda.setEsposo(resultSet.getString("Esposo"));
                    boda.setIdEsposa(resultSet.getString("Id_Esposa"));
                    boda.setEsposa(resultSet.getString("Esposa"));
                    boda.setFechaBoda(resultSet.getDate("Fecha_Boda"));
                    boda.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    boda.setDireccion(resultSet.getString("Direccion")); 
                    boda.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                    boda.setParroquia(resultSet.getString("Nombre_Parroquia"));
                    boda.setIdPadrino(resultSet.getString("Id_Padrino"));
                    boda.setMadrinaPadrino(resultSet.getString("Madrina_Padrino"));
                    boda.setIdMadreEsposo(resultSet.getString("Id_Madre_Esposo"));
                    boda.setMadreEsposo(resultSet.getString("Madre_Esposo")); 
                    boda.setIdPadreEsposo(resultSet.getString("Id_Padre_Esposo"));
                    boda.setPadreEsposo(resultSet.getString("Padre_Esposo")); 
                    boda.setIdMadreEsposa(resultSet.getString("Id_Madre_Esposa"));
                    boda.setMadreEsposa(resultSet.getString("Madre_Esposa"));
                    boda.setIdPadreEsposa(resultSet.getString("Id_Padre_Esposa"));
                    boda.setPadreEsposa(resultSet.getString("Padre_Esposa"));
                    boda.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    boda.setPersona(resultSet.getString("Persona"));
                    boda.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                    boda.setSacerdote(resultSet.getString("Sacerdote")); 
                    
                    bodasFiltradas.add(boda);
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
            
            return bodasFiltradas;
        }
    }
    
    public Boda getById(int idBoda){
        String query = "SELECT * FROM V_Boda WHERRE Id_Boda = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, idBoda);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                Boda boda = new Boda();
                    boda.setIdBoda(resultSet.getInt("Id_Boda"));
                    boda.setIdEsposo(resultSet.getString("Id_Esposo"));
                    boda.setEsposo(resultSet.getString("Esposo"));
                    boda.setIdEsposa(resultSet.getString("Id_Esposa"));
                    boda.setEsposa(resultSet.getString("Esposa"));
                    boda.setFechaBoda(resultSet.getDate("Fecha_Boda"));
                    boda.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    boda.setDireccion(resultSet.getString("Direccion")); 
                    boda.setIdParroquia(resultSet.getInt("Id_Parroquia"));
                    boda.setParroquia(resultSet.getString("Nombre_Parroquia"));
                    boda.setIdPadrino(resultSet.getString("Id_Padrino"));
                    boda.setMadrinaPadrino(resultSet.getString("Madrina_Padrino"));
                    boda.setIdMadreEsposo(resultSet.getString("Id_Madre_Esposo"));
                    boda.setMadreEsposo(resultSet.getString("Madre_Esposo")); 
                    boda.setIdPadreEsposo(resultSet.getString("Id_Padre_Esposo"));
                    boda.setPadreEsposo(resultSet.getString("Padre_Esposo")); 
                    boda.setIdMadreEsposa(resultSet.getString("Id_Madre_Esposa"));
                    boda.setMadreEsposa(resultSet.getString("Madre_Esposa"));
                    boda.setIdPadreEsposa(resultSet.getString("Id_Padre_Esposa"));
                    boda.setPadreEsposa(resultSet.getString("Padre_Esposa"));
                    boda.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    boda.setPersona(resultSet.getString("Persona"));
                    boda.setIdSacerdote(resultSet.getInt("Id_Sacerdote"));
                    boda.setSacerdote(resultSet.getString("Sacerdote")); 
                    
                    return boda;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
