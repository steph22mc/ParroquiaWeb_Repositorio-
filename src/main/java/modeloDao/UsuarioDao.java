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
            
            statement.execute();
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
    
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "SELECT ActualizarUsuario(?, ?, ?, ?, ?, ?, ?, ?) AS result";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usuario.getIdUsuario());
            statement.setString(2, usuario.getNombre());
            statement.setString(3, usuario.getPrimerApellido());
            statement.setString(4, usuario.getSegundoApellido());
            statement.setString(5, usuario.getUsuario());
            statement.setString(6, usuario.getCorreoElectronico());
            statement.setString(7, usuario.getContrasena());
            statement.setString(8, usuario.getTipoUsuario());

            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int rowsAffected = resultSet.getInt("result");
                // Verificar el número de filas afectadas y realizar las acciones necesarias
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

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
    
    public List<Usuario> listaUsuarios(int id) {
        List<Usuario> usuarios = new ArrayList<>();

        try (CallableStatement stmt = connection.prepareCall("{CALL LISTA_USUARIOS(?)}")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("Id_Usuario"));
                usuario.setNombreCompleto(rs.getString("Nombre_Completo"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setCorreoElectronico(rs.getString("CorreoElectronico"));
                usuario.setTipoUsuario(rs.getString("TipoUsuario"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    
    public int obtenerIdPorUsuario(String nombreUsuario) {
        int idUsuario = 0;

        try (PreparedStatement statement = connection.prepareStatement("SELECT Id_Usuario FROM TB_Usuario WHERE Usuario = ?")) {
            statement.setString(1, nombreUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idUsuario = resultSet.getInt("Id_Usuario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idUsuario;
    }
    
    public String obtenerNombrePorUsuario(String nombreUsuario) {
        String nombreCompleto = "";

        try (PreparedStatement statement = connection.prepareStatement("SELECT Nombre_Completo FROM V_LISTA_USUARIOS WHERE Usuario = ?")) {
            statement.setString(1, nombreUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nombreCompleto = resultSet.getString("Nombre_Completo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCompleto;
    }
    
    public List<Usuario> buscarUsuario(String searchText, int id)throws SQLException, ClassNotFoundException{
        List<Usuario> usuariosFiltrados = new ArrayList<>();
        
        String query = "SELECT * FROM V_LISTA_USUARIOS WHERE Nombre_Completo LIKE ? AND Id_Usuario <> ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)){
            String searchTerm = "%" + searchText + "%";
            statement.setString(1, searchTerm);
            statement.setInt(2, id);
            
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("Id_Usuario"));
                    usuario.setNombreCompleto(resultSet.getString("Nombre_Completo"));
                    usuario.setUsuario(resultSet.getString("Usuario"));
                    usuario.setCorreoElectronico(resultSet.getString("CorreoElectronico"));
                    usuario.setTipoUsuario(resultSet.getString("TipoUsuario"));
                    
                    usuariosFiltrados.add(usuario);
                }
            }
        }
        
        return usuariosFiltrados;
    }
    
    public void delete(int idUser) {
        String query = "DELETE FROM TB_Usuario WHERE Id_Usuario = ?";
        try (Connection connection2 = Conexion.getConnection("Sacerdote");
                PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Usuario getById(int idUsuario) {
        Usuario usuario = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM TB_Usuario WHERE Id_Usuario = ?")) {
            statement.setInt(1, idUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("Id_Usuario"));
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setPrimerApellido(resultSet.getString("Primer_Apellido"));
                    usuario.setSegundoApellido(resultSet.getString("Segundo_Apellido"));
                    usuario.setUsuario(resultSet.getString("Usuario"));
                    usuario.setCorreoElectronico(resultSet.getString("CorreoElectronico"));
                    usuario.setContrasena(resultSet.getString("Contrasena"));
                    usuario.setTipoUsuario(resultSet.getString("TipoUsuario"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
}
