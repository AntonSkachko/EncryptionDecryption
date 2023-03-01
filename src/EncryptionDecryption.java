import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EncryptionDecryption {

    public static void main(String[] args) {


    }

    public void chooseEverything(String[] args) {

        String mode = "";
        int key = 0;
        String message = "";
        String pathToReadingFile = "";
        String pathToWritingFile = "";
        String alg = "";

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
                    break;
                case "-out":
                    pathToWritingFile = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }

        //TODO
        WriterReaderContext writerReaderContext = new WriterReaderContext("".equals(pathToReadingFile) ?
                new ConsoleReaderWriter() : new FileReaderWriter(pathToReadingFile, pathToWritingFile));

        writerReaderContext.executeWriterReader(pathToWritingFile);
    }

}

