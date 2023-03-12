import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

interface WriterReader {
    String getMessage();
    void setMessage(String message);
    void readSomething();
    void writeSomething();
}

class ConsoleReaderWriter implements WriterReader {

    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void readSomething() {
        Scanner scanner = new Scanner(System.in);
        message = scanner.nextLine();
    }

    @Override
    public void writeSomething() {
        System.out.print(this.message);
    }
}

class FileReaderWriter implements WriterReader {

    private String message;
    private final File readFile;
    private final File writeFile;

    public FileReaderWriter(String pathToReadFile, String pathToWriteFile) {
        this.readFile = new File(pathToReadFile);
        this.writeFile = new File(pathToWriteFile);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void readSomething() {
        try (Scanner scanner = new Scanner(readFile)) {
            message = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeSomething() {
        try (PrintWriter writer = new PrintWriter(writeFile)) {
            writer.println(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class WriterReaderContext {

    private final WriterReader writerReader;

    public WriterReaderContext(WriterReader writerReader) {
        this.writerReader = writerReader;
    }

    public void executeWriterReader(int key, String choosingCipher, String alg) {

        writerReader.readSomething();
        CipherContext cipherContext = new CipherContext();

        cipherContext.setCipher("unicode".equals(alg) ? new UnicodeCipher() : new ShiftCipher());
        writerReader.setMessage(cipherContext.executeCipher(writerReader.getMessage(), key, choosingCipher));

        writerReader.writeSomething();

    }
}
