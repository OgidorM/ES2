package destination;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import config.LogConfig;

public class FileDestination implements LogDestination{
    
    @Override
    public void write(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LogConfig.INSTANCE.getDestinationPath(), true))) {
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }
}
