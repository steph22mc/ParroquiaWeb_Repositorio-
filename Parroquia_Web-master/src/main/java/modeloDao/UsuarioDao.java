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
import modelo.Usuario;

/**
 *
 * @author DELL
 */
public class UsuarioDao {
    private Connection connection;
    
    public UsuarioDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException{
        try(
                PreparedStatement statement = connection.prepareStatement("CALL InsertarUsuario(?, ?, ?, ?, ?, ?, ?)")){
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getPrimerApellido());
            statement.setString(3, usuario.getSegundoApellido());
            statement.setString(4, usuario.getUsuario());
            statement.setString(5, usuario.getCorreoElectronico());
            statement.setString(6, usuario.getContrasena());
            statement.setString(7, usuario.getTipoUsuario());
            
            statement.executeUpdate();
        }
    }
    
    public List<Usuario> obtenerUsuarios() throws SQLException, ClassNotFoundException {
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vw_Usuarios");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("Id_Usuario"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                usuario.setSegundoApellido(resultSet.getString("Segundo_Apellido"));
                usuario.setUsuario(resultSet.getString("Usuario"));
                usuario.setCorreoElectronico(resultSet.getString("CorreoElectronico"));
                usuario.setContrasena(resultSet.getString("Contrasena"));
                usuario.setTipoUsuario(resultSet.getString("TipoUsuario"));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }
    
    public Usuario getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        Usuario user = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vw_Usuarios WHERE CorreoElectronico = ?");
        ) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new Usuario();
                    user.setIdUsuario(resultSet.getInt("Id_Usuario"));
                    user.setNombre(resultSet.getString("Nombre"));
                    user.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                    user.setSegundoApellido(resultSet.getString("Segundo_Apellido"));
                    user.setUsuario(resultSet.getString("Usuario"));
                    user.setCorreoElectronico(resultSet.getString("CorreoElectronico"));
                    user.setContrasena(resultSet.getString("Contrasena"));
                    user.setTipoUsuario(resultSet.getString("TipoUsuario"));
                }
            }
        }

        return user;
    }
    
    public int actualizarUsuario(int idUsuario, String nombre, String primerApellido,
                                 String segundoApellido, String usuario, String correoElectronico,
                                 String contrasena, String tipoUsuario) throws SQLException {
        String query = "SELECT ActualizarUsuario(?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            statement.setString(2, nombre);
            statement.setString(3, primerApellido);
            statement.setString(4, segundoApellido);
            statement.setString(5, usuario);
            statement.setString(6, correoElectronico);
            statement.setString(7, contrasena);
            statement.setString(8, tipoUsuario);

            rowsAffected = statement.executeUpdate();
        }

        return rowsAffected;
    }
    
    public String obtenerTipoUsuario(String usuario) throws SQLException, ClassNotFoundException {
        String tipoUsuario = null;

        String query = "SELECT TipoUsuario FROM TB_Usuario WHERE Usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tipoUsuario = resultSet.getString("TipoUsuario");
                }
            }
        }

        return tipoUsuario;
    }

}
