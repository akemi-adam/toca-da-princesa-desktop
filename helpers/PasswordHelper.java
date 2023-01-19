package helpers;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import java.util.Base64;

public abstract class PasswordHelper
{
    private static final int ITERATIONS = 10000;

    private static final int KEY_LENGTH = 256;

    /**
     * Returns the password hash with salt
     * 
     * @param password
     * @throws NoSuchAlgorithmException
     * 
     * @return String
     */
    public static String getSaltedHash(String password) throws NoSuchAlgorithmException
    {
        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(KEY_LENGTH / 8);

        return Base64.getEncoder().encodeToString(salt) + "$" + hash(password, salt);
    }

    /**
     * Returns the password hash
     * 
     * @param password
     * @param salt
     * @throws NoSuchAlgorithmException
     * 
     * @return String
     */
    private static String hash(String password, byte[] salt) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        
        md.update(salt);
        
        byte[] hashedPassword = md.digest(password.getBytes());
        
        for (int i = 0; i < ITERATIONS; i++)
        {
            md.reset();
            
            hashedPassword = md.digest(hashedPassword);
        }

        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    /**
     * Password cheking
     * 
     * @param password
     * @param stored
     * @throws NoSuchAlgorithmException
     * 
     * @return boolean
     */
    public static boolean check(String password, String stored) throws NoSuchAlgorithmException
    {
        String[] saltAndHash = stored.split("\\$");
    
        byte[] salt = Base64.getDecoder().decode(saltAndHash[0]);
    
        String hashOfInput = hash(password, salt);
    
        return hashOfInput.equals(saltAndHash[1]);
    }
}
