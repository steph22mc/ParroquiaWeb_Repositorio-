/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_bcrypt;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author DELL
 */
public class PasswordEncrypt {
    /**
    * Encripta una contraseña utilizando el algoritmo BCrypt.
    * @param password La contraseña a encriptar.
    * @return La contraseña encriptada.
    * 
    * El salt es un valor aleatorio único que se agrega a las contraseñas antes 
    * de encriptarlas para aumentar la seguridad y dificultar los ataques a 
    * contraseñas comunes o precalculadas.
    */

    // Método para encriptar una contraseña
//    Este es el método encriptarContrasena. Toma una contraseña sin encriptar como 
//    parámetro y devuelve la contraseña encriptada. Utiliza el método BCrypt.hashpw() 
//    de la librería BCrypt para realizar la encriptación. El BCrypt.gensalt() se utiliza 
//    para generar un salt aleatorio que se agrega a la contraseña antes de encriptarla.
    public String encriptarContrasena(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }
    
    /**
     * 
     * Verifica si una contraseña coincide con su versión encriptada.
     * @param password La contraseña sin encriptar.
     * @param hashedPassword La versión encriptada de la contraseña.
     * @return true si la contraseña coincide, false de lo contrario.
     */
    // Método para verificar una contraseña
    
//    Este es el método verificarContrasena. Toma una contraseña sin encriptar (password) 
//    y una versión encriptada de la contraseña (hashedPassword) como parámetros. Utiliza 
//    el método BCrypt.checkpw() de la librería BCrypt para verificar si la contraseña sin 
//    encriptar coincide con la versión encriptada. El método devuelve true si la 
//    contraseña coincide y false en caso contrario.
    public boolean verificarContrasena(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
