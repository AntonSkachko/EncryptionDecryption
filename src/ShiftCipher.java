
public class ShiftCipher implements Cipher{
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
