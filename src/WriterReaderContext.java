import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

interface WriterReader {
    public String getMessage();
    public void setMessage(String message);
    public void readSomething();
    public void writeSomething();
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
//TODO
public class WriterReaderContext {
    private WriterReader writerReader;

    public WriterReaderContext(WriterReader writerReader) {
        this.writerReader = writerReader;
    }

    public void executeWriterReader(String pathToFile) {
        writerReader.readSomething();

        if ("".equals(pathToFile)) {
            System.out.println(writerReader.getMessage());
        } else {
            writerReader.writeSomething();
        }
    }
}
