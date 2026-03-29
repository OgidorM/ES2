package config;

import java.util.ArrayList;
import java.util.List;

public enum LogConfig {
    INSTANCE;

    private List<String> levels;
    private String destinationPath;
    private String messageFormat;
    private boolean active;
    private List<String> filters;

    LogConfig() {
        this.levels = new ArrayList<>();
        this.levels.add("INFO");
        this.destinationPath = "app.log";
        this.messageFormat = "[%t] [%l] - %m";
        this.active = true;
        this.filters = new ArrayList<>();
    }

    // --- GETTERS ---
    public List<String> getLevels()        { return levels; }
    public String getDestinationPath()     { return destinationPath; }
    public String getMessageFormat()       { return messageFormat; }
    public boolean isActive()              { return active; }
    public List<String> getFilters()       { return filters; }

    // --- SETTERS ---
    public void setDestinationPath(String destinationPath) { this.destinationPath = destinationPath; }
    public void setMessageFormat(String messageFormat)     { this.messageFormat = messageFormat; }
    public void setActive(boolean active)                  { this.active = active; }
    public void setLevels(List<String> levels)             { this.levels = new ArrayList<>(levels); }
    public void setFilters(List<String> filters)           { this.filters = new ArrayList<>(filters); }

    public void addLevel(String type) {
        if (!this.levels.contains(type)) {
            this.levels.add(type);
        }
    }

    public void removeLevel(String type) {
        this.levels.remove(type);
    }

    public void addFilter(String filter) {
        if (!this.filters.contains(filter)) {
            this.filters.add(filter);
        }
    }

    public void removeFilter(String filter) {
        this.filters.remove(filter);
    }
}
