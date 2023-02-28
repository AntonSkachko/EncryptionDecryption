
public class UnicodeCipher implements Cipher{

    @Override
    public String doCipher(String message, int key) {
        char[] temp = message.toCharArray();

        for (int i = 0; i < temp.length; i++) {
            temp[i] = (char) (temp[i] + key);
        }
        return new String(temp);
    }
}
