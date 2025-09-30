package edu.unizg.foi.nwtis.imilanovi20.vjezba_08_dz_3;


import java.util.Map;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

// TODO: Auto-generated Javadoc
/**
 * The Class NoPasswordHash.
 */
public class NoPasswordHash implements Pbkdf2PasswordHash {

  /**
   * Generate.
   *
   * @param password the password
   * @return the string
   */
  @Override
  public String generate(char[] password) {
    return password.toString();
  }

  /**
   * Verify.
   *
   * @param password the password
   * @param hashedPassword the hashed password
   * @return true, if successful
   */
  @Override
  public boolean verify(char[] password, String hashedPassword) {
    var npassword = new String(password);
    if (npassword.trim().compareTo(hashedPassword.trim()) == 0) {
      return true;
    }
    return false;
  }

  /**
   * Initialize.
   *
   * @param parameters the parameters
   */
  @Override
  public void initialize(Map<String, String> parameters) {}

}
