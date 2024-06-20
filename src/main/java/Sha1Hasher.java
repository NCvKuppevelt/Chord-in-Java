/*
Based on solution from https://www.boardinfinity.com/blog/sha-algorithm/
*/

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Main class
public class Sha1Hasher {
    // "The identifier length m must be large enough to make the probability of two nodes or keys hashing to the same
    // identifier negligible.", p3
    private static final int BITS = 32;

    public static int hash(String input) {
        // handling exceptions
        try {
            // getInstance() method is called with algorithm SHA-1
            // "using SHA-1 as a base hash function", p3
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called to calculate message digest of the input string
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hexadecimal value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it a 32 bit value
            while (hashtext.length() < BITS) {
                hashtext = STR."0\{hashtext}";
            }
            // return the HashText created
//            return hashtext;
            return no.intValue();
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}