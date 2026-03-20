package config;

import java.util.ArrayList;
import java.util.List;

public class LogConfig{
    private static LogConfig instancia;

    // Configurações
    private List<String> levels;
    private String destinationPath;
    private String messageFormat;
    private boolean active;

    private LogConfig(){
        this.levels = new ArrayList<>();
        this.levels.add("INFO");
        this.destinationPath = "app.log";
        this.messageFormat = "[%t] [%l] - %m";
        this.active = true;
    }

     public static synchronized LogConfig getInstance() {
        if (instancia == null) {
            instancia = new LogConfig();
        }
        return instancia;
    }

    // --- GETTERS ---
    public List<String> getLevels() { return levels; }
    public String getDestinationPath() { return destinationPath; }
    public String getMessageFormat() { return messageFormat; }
    public boolean isActive() { return active; }

    // --- SETTERS ---
    public void setDestinationPath(String destinationPath) { this.destinationPath = destinationPath; }
    public void setMessageFormat(String messageFormat) { this.messageFormat = messageFormat; }
    public void setActive(boolean active) { this.active = active; }
    
    public void addLevel(String type) {
        if (!this.levels.contains(type)) {
            this.levels.add(type);
        }
    }

    public void removeLevel(String type) {
        this.levels.remove(type);
    }
}