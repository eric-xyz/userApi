package encryptUtil;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static String algorithm = "AES";
	private static byte[] keyValue=new byte[] {'0','2','3','4','5','6','7','8','9','1','2','3','4','5','6','7'};// your key
    
	public static String[] decrypt(byte[] encryptedText) throws Exception 
    {
            // generate key 
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decValue = chiper.doFinal(encryptedText);
        String decryptedValue = new String(decValue);
        return getValues(decryptedValue);
    }
	public static String[] getValues(String decryptedValue){
		String[] res = new String[2];
		return res;
	}

//generateKey() is used to generate a secret key for AES algorithm
    private static Key generateKey() throws Exception 
    {
            Key key = new SecretKeySpec(keyValue, algorithm);
            return key;
    }

}
