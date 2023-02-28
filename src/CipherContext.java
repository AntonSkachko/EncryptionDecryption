
public class CipherContext {
    private Cipher cipher;

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public String executeCipher(String message, int key, String choosingCipher) {
        if ("dec".equals(choosingCipher)) {
            return cipher.doCipher(message, -key);
        } else {
            return cipher.doCipher(message, key);
        }
    }
}
