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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Direccion;
import modelo.Persona;

/**
 *
 * @author DELL
 */
public class PersonaDao {
    private Connection connection;

    public PersonaDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Persona> getAll() {
        String query = "CALL P_Lista_Personas()";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            List<Persona> personas = new ArrayList<>();
            while (resultSet.next()) {
                Persona persona = new Persona();
                persona.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                persona.setNombre(resultSet.getString("Nombre"));
                persona.setPrimerNombre(resultSet.getString("Primer_Nombre"));
                persona.setSegundoNombre(resultSet.getString("Segundo_Nombre"));
                persona.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                persona.setSegundoApellido(resultSet.getString("Segundo_Apellido"));
                persona.setGenero(resultSet.getString("Genero"));
                persona.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento"));
                persona.setTelefono(resultSet.getString("Telefono"));
                persona.setDireccion(resultSet.getString("Dirección"));
                persona.setId_Direccion(resultSet.getInt("Id_Direccion")); 
                personas.add(persona);
            }
            return personas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int actualizarPersona(Persona persona) {
        String query = "SELECT ActualizarPersona(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, persona.getCedulaPersona());
            statement.setString(2, persona.getPrimerNombre());
            statement.setString(3, persona.getSegundoNombre());
            statement.setString(4, persona.getPrimerApellido());
            statement.setString(5, persona.getSegundoApellido());
            statement.setString(6, persona.getGenero());
            // Convertir la fecha util.Date a sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimientoStr = sdf.format(persona.getFechaNacimiento());
            statement.setString(7, fechaNacimientoStr);
            
            statement.setString(8, persona.getTelefono());
            statement.setInt(9, persona.getId_Direccion());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertarPersona(Persona persona) {
        String query = "CALL InsertarPersona(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, persona.getCedulaPersona());
            statement.setString(2, persona.getPrimerNombre());
            statement.setString(3, persona.getSegundoNombre());
            statement.setString(4, persona.getPrimerApellido());
            statement.setString(5, persona.getSegundoApellido());
            statement.setString(6, persona.getGenero());
            
            // Convertir la fecha util.Date a sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimientoStr = sdf.format(persona.getFechaNacimiento());
            statement.setString(7, fechaNacimientoStr);
            
            statement.setString(8, persona.getTelefono());
            statement.setInt(9, persona.getId_Direccion());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePersona(String cedulaPersona) {
        String query = "DELETE FROM TB_Persona WHERE Cedula_Persona = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setString(1, cedulaPersona);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Persona> buscarPersonas(String searchText) throws SQLException, ClassNotFoundException {
        List<Persona> personasFiltradas = new ArrayList<>();

        String query = "SELECT p.Cedula_Persona, np.Nombre, p.Genero, p.Fecha_Nacimiento, p.Telefono, p.Id_Direccion, d.Direccion " +
                       "FROM TB_Persona p " +
                       "INNER JOIN V_Nombre_Persona np ON p.Cedula_Persona = np.Cedula_Persona " +
                       "INNER JOIN V_Direccion d ON p.Id_Direccion = d.Id_Direccion " +
                       "WHERE p.Cedula_Persona LIKE ? OR np.Nombre LIKE ? OR p.Genero LIKE ? OR p.Fecha_Nacimiento LIKE ? OR p.Telefono LIKE ? OR d.Direccion LIKE ? ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String searchTerm = "%" + searchText + "%";
            for (int i = 1; i <= 6; i++) {
                statement.setString(i, searchTerm);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Persona persona = new Persona();
                    persona.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    persona.setNombre(resultSet.getString("Nombre"));
                    persona.setGenero(resultSet.getString("Genero"));
                    persona.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento"));
                    persona.setTelefono(resultSet.getString("Telefono"));
                    persona.setId_Direccion(resultSet.getInt("Id_Direccion"));
                    persona.setDireccion(resultSet.getString("Direccion"));

                    personasFiltradas.add(persona);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personasFiltradas;
    }
    
    public Persona getByCedula(String cedula) {
        String query = "SELECT p.*, d.Direccion " +
                       "FROM TB_Persona p " +
                       "INNER JOIN V_Direccion d ON p.Id_Direccion = d.Id_Direccion " + 
                       "WHERE p.Cedula_Persona = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Persona persona = new Persona();
                persona.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                persona.setPrimerNombre(resultSet.getString("Primer_Nombre"));
                persona.setSegundoNombre(resultSet.getString("Segundo_Nombre"));
                persona.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                persona.setSegundoApellido(resultSet.getString("Segundo_Apellido"));
                persona.setGenero(resultSet.getString("Genero"));
                persona.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento"));
                persona.setTelefono(resultSet.getString("Telefono"));
                persona.setId_Direccion(resultSet.getInt("Id_Direccion"));
                persona.setDireccion(resultSet.getString("Direccion"));

                return persona;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    
    public List<Persona> buscarMujeres(String searchText) throws SQLException, ClassNotFoundException {
        List<Persona> personasFiltradas = new ArrayList<>();

        String query = "SELECT p.Cedula_Persona, np.Nombre, p.Genero, p.Fecha_Nacimiento, p.Telefono, p.Id_Direccion, d.Direccion " +
                       "FROM TB_Persona p " +
                       "INNER JOIN V_Nombre_Persona np ON p.Cedula_Persona = np.Cedula_Persona " +
                       "INNER JOIN V_Direccion d ON p.Id_Direccion = d.Id_Direccion " +
                       "WHERE np.Nombre LIKE ? AND p.Genero = 'Femenino' ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String searchTerm = "%" + searchText + "%";
            
            statement.setString(1, searchTerm);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Persona persona = new Persona();
                    persona.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    persona.setNombre(resultSet.getString("Nombre"));
                    persona.setGenero(resultSet.getString("Genero"));
                    persona.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento"));
                    persona.setTelefono(resultSet.getString("Telefono"));
                    persona.setId_Direccion(resultSet.getInt("Id_Direccion"));
                    persona.setDireccion(resultSet.getString("Direccion"));

                    personasFiltradas.add(persona);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personasFiltradas;
    }
    
    public List<Persona> buscarHombres(String searchText) throws SQLException, ClassNotFoundException {
        List<Persona> personasFiltradas = new ArrayList<>();

        String query = "SELECT p.Cedula_Persona, np.Nombre, p.Genero, p.Fecha_Nacimiento, p.Telefono, p.Id_Direccion, d.Direccion " +
                       "FROM TB_Persona p " +
                       "INNER JOIN V_Nombre_Persona np ON p.Cedula_Persona = np.Cedula_Persona " +
                       "INNER JOIN V_Direccion d ON p.Id_Direccion = d.Id_Direccion " +
                       "WHERE np.Nombre LIKE ? AND p.Genero = 'Masculino' ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String searchTerm = "%" + searchText + "%";
            
            statement.setString(1, searchTerm);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Persona persona = new Persona();
                    persona.setCedulaPersona(resultSet.getString("Cedula_Persona"));
                    persona.setNombre(resultSet.getString("Nombre"));
                    persona.setGenero(resultSet.getString("Genero"));
                    persona.setFechaNacimiento(resultSet.getDate("Fecha_Nacimiento"));
                    persona.setTelefono(resultSet.getString("Telefono"));
                    persona.setId_Direccion(resultSet.getInt("Id_Direccion"));
                    persona.setDireccion(resultSet.getString("Direccion"));

                    personasFiltradas.add(persona);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personasFiltradas;
    }
    
        public String obtenerCedulaPorNombre(String nombre) throws SQLException{
            String query = "SELECT Cedula_Persona FROM v_nombre_persona WHERE Nombre = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, nombre);

                try (ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next()){
                        return resultSet.getString("Cedula_Persona");
                    }
                }
            }
            return null;
        }

}
