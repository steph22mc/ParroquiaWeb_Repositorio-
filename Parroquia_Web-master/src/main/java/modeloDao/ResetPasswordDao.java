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
import java.sql.Timestamp;
import java.util.Date;
import modelo.ResetPassword;

/**
 *
 * @author DELL
 */
public class ResetPasswordDao {
    private Connection connection;
    
    public ResetPasswordDao() {
        try {
            connection = Conexion.getConnection("Secretario"); // Tipo de usuario (Sacerdote, Secretario, etc.)
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertResetPassword(ResetPassword resetPassword) throws SQLException, ClassNotFoundException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Reset_Password (Id_Usuario, Hash_code, Exptime, Intime) VALUES (?, ?, ?, ?)"))
        {
            statement.setInt(1, resetPassword.getIdUsuario());
            statement.setString(2, resetPassword.getHashCode());
            statement.setTimestamp(3, new java.sql.Timestamp(resetPassword.getExptime().getTime()));
            statement.setTimestamp(4, new java.sql.Timestamp(resetPassword.getIntime().getTime()));
            
            statement.executeUpdate();
        }
    }
    
    public void saveResetPasswordHash(int profileId, String hash, Timestamp exptime, Timestamp intime) throws SQLException, ClassNotFoundException {
        ResetPassword resetPassword = new ResetPassword();
        resetPassword.setIdUsuario(profileId);
        resetPassword.setHashCode(hash);
        resetPassword.setExptime(new Date(exptime.getTime()));
        resetPassword.setIntime(new Date(intime.getTime()));

        ResetPasswordDao resetPasswordDao = new ResetPasswordDao();
        resetPasswordDao.insertResetPassword(resetPassword);
    }
    
    public boolean checkResetPasswordExpiration(String hash) throws SQLException{
        String query = "SELECT Id_Usuario, Exptime FROM Reset_Password WHERE Hash_Code = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hash);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("Id_Usuario");
                    Timestamp expirationTime = resultSet.getTimestamp("Exptime");

                    return isExpired(expirationTime);
                }
            }
        }
        return false; // Si no se encuentra ninguna coincidencia, se considera no expirado
    }
    
    private boolean isExpired(Timestamp dateTime){
        Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
        return dateTime.before(currentDateTime);
    }
}
