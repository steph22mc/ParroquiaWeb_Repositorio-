/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import config.Conexion;
import static java.lang.System.out;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Direccion;

/**
 *
 * @author DELL
 */
public class DireccionDao {
    private Connection connection;

    public DireccionDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insert(Direccion direccion) {
        String query = "{CALL InsertarDireccion(?, ?, ?)}";
    try (CallableStatement statement = connection.prepareCall(query)) {
        statement.setString(1, direccion.getNombreLugar());
        statement.setString(2, direccion.getProvincia());
        statement.setString(3, direccion.getDistrito());
        statement.execute();  // Cambiamos executeUpdate() a execute()

        // Opcional: Obtener el resultado del procedimiento almacenado si devuelve algún valor
        // ResultSet resultSet = statement.getResultSet();
        // ... procesar el resultado si es necesario
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public void update(Direccion direccion) {
        String query = "SELECT ActualizarDireccion(?, ?, ?, ?) AS result";
try (PreparedStatement statement = connection.prepareStatement(query)) {
    statement.setInt(1, direccion.getIdDireccion());
    statement.setString(2, direccion.getNombreLugar());
    statement.setString(3, direccion.getProvincia());
    statement.setString(4, direccion.getDistrito());
    ResultSet resultSet = statement.executeQuery();

    if (resultSet.next()) {
        int rowsAffected = resultSet.getInt("result");
        // Verificar el número de filas afectadas y realizar las acciones necesarias
    }
} catch (SQLException e) {
    e.printStackTrace();
}
    }
    
    public void delete(int idDireccion) {
        String query = "DELETE FROM TB_Direccion WHERE Id_Direccion = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idDireccion);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Direccion> getAll() {
        String query = "SELECT * FROM V_Direccion";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Direccion> direcciones = new ArrayList<>();
            while (resultSet.next()) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                direccion.setNombreLugar(resultSet.getString("Nombre_Lugar"));
                direccion.setDistrito(resultSet.getString("Distrito"));
                direccion.setProvincia(resultSet.getString("Provincia"));
                direccion.setDireccion(resultSet.getString("Direccion"));
                direcciones.add(direccion);
            }
            return direcciones;
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        return null;
    }

    public Direccion getById(int idDireccion) {
        String query = "SELECT * FROM V_Direccion WHERE Id_Direccion = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idDireccion);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                direccion.setNombreLugar(resultSet.getString("Nombre_Lugar"));
                direccion.setDistrito(resultSet.getString("Distrito"));
                direccion.setProvincia(resultSet.getString("Provincia"));
                direccion.setDireccion(resultSet.getString("Direccion"));
                return direccion;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Direccion> buscarDirecciones(String searchText) throws SQLException, ClassNotFoundException {
        List<Direccion> direccionesFiltradas = new ArrayList<>();

        String query = "SELECT * FROM V_Direccion WHERE Nombre_Lugar LIKE ? OR Provincia LIKE ? OR Distrito LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String searchTerm = "%" + searchText + "%";
            statement.setString(1, searchTerm);
            statement.setString(2, searchTerm);
            statement.setString(3, searchTerm);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Direccion direccion = new Direccion();
                    direccion.setIdDireccion(resultSet.getInt("Id_Direccion"));
                    direccion.setDireccion(resultSet.getString("Direccion")); 
                    direccion.setNombreLugar(resultSet.getString("Nombre_Lugar"));
                    direccion.setProvincia(resultSet.getString("Provincia"));
                    direccion.setDistrito(resultSet.getString("Distrito"));

                    direccionesFiltradas.add(direccion);
                }
            }
        }

        return direccionesFiltradas;
    }
    
    public int obtenerIdDireccionPorNombre(String nombreDireccion) throws SQLException, ClassNotFoundException {
        String query = "SELECT Id_Direccion FROM V_Direccion WHERE Direccion = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombreDireccion);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("Id_Direccion");
                }
            }
        }
        return -1; // O cualquier otro valor que indique que no se encontró la dirección
    }

}
