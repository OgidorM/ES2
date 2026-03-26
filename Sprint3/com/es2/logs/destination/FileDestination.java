package destination;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileDestination implements LogDestination {
    private String path;

    public FileDestination(String path) {
        this.path = path;
    }

    @Override
    public void write(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path, true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }
}
