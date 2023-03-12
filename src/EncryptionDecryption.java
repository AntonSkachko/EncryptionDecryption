
public class EncryptionDecryption {

    public static void main(String[] args) {
        String mode = "";
        int key = 0;
        String data = "";
        String pathToReadingFile = "";
        String pathToWritingFile = "";
        String alg = "";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-in" -> pathToReadingFile = args[i + 1];
                case "-out" -> pathToWritingFile = args[i + 1];
                case "-alg" -> alg = args[i + 1];
            }
        }

        WriterReaderContext writerReaderContext = new WriterReaderContext("console".equals(data) ?
                new ConsoleReaderWriter() : new FileReaderWriter(pathToReadingFile, pathToWritingFile));
        writerReaderContext.executeWriterReader(key, mode, alg);

    }
}

