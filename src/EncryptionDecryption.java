import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EncryptionDecryption {

    private static String mode = null;
    private static int key = 0;
    private static String message = "";
    private static String alg = "";

    private static String pathToReadingFile = null;
    private static String pathToWritingFile = null;
    private static File readFile = null;
    private static File writeFile = null;

    public static void main(String[] args) {
        setValue(args);
        try {
            makeMagic();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setValue(String[] args) {

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    message = args[i + 1];
                    break;
                case "-in":
                    pathToReadingFile = args[i + 1];
                    readFile = new File(pathToReadingFile);
                    break;
                case "-out":
                    pathToWritingFile = args[i + 1];
                    writeFile = new File(pathToWritingFile);
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }
    }

    public static String takeMessage(File readFile) {
        try (Scanner scanner = new Scanner(readFile)) {
            message = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static void writeMessage(File writeFile, String message) {
        try (PrintWriter writer = new PrintWriter(writeFile)) {
            writer.println(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void makeMagic() throws FileNotFoundException {

        CipherContext cipher = new CipherContext();
        if (mode == null) mode = "enc";
        if (message.equals("") && readFile != null) {
            message = takeMessage(readFile);
        }
        if (writeFile == null) {
            if (alg == "unicode") {
                cipher.setCipher(new UnicodeCipher());
                System.out.println(cipher.executeCipher(message, key, mode));
            } else {
                cipher.setCipher(new ShiftCipher());
                System.out.println(cipher.executeCipher(message, key, mode));
            }
        } else {
            if (alg == "unicode") {
                cipher.setCipher(new UnicodeCipher());
                writeMessage(writeFile, cipher.executeCipher(message, key, mode));
            } else {
                writeMessage(writeFile, cipher.executeCipher(message, key, mode));
            }
        }
    }
}

