
interface Cipher {
    public String doCipher(String message, int key);
}

class ShiftCipher implements Cipher{
    @Override
    public String doCipher(String message, int key) {
        char[] arrayMessage = message.toCharArray();
        boolean upper;
        int def = 'Z' - 'A' + 1;
        for(int i = 0; i < arrayMessage.length; i++) {
            if(arrayMessage[i] >= 'A' && arrayMessage[i] <= 'Z') { upper = true; }
            else if(arrayMessage[i] >= 'a' && arrayMessage[i] <= 'z') { upper = false; }
            else { continue; }

            arrayMessage[i] += key;

            if (upper) {
                if(arrayMessage[i] > 'Z') { arrayMessage[i] -= def; }
                if(arrayMessage[i] < 'A') { arrayMessage[i] += def; }
            } else {
                if(arrayMessage[i] > 'z') { arrayMessage[i] -= def; }
                if(arrayMessage[i] < 'a') { arrayMessage[i] += def; }
            }
        }

        return new String(arrayMessage);
    }
}

class UnicodeCipher implements Cipher{

    @Override
    public String doCipher(String message, int key) {
        char[] temp = message.toCharArray();

        for (int i = 0; i < temp.length; i++) {
            temp[i] = (char) (temp[i] + key);
        }
        return new String(temp);
    }
}

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
