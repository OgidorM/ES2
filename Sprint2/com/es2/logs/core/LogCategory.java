package core;

import java.util.ArrayList;
import java.util.List;


public class LogCategory implements LogRecord {
    
    private String categoryName;

    // 1. Guarda uma lista da própria interface
    private List<LogRecord> children = new ArrayList<>();

    public LogCategory(String name) {
        this.categoryName = name;
    }

    //Metodos para gerir o grupo
    public void add(LogRecord log) { this.children.add(log); }
    public void remove(LogRecord log) { this.children.remove(log); }
    public List<LogRecord> getChildren() { return this.children; }

    //3.Implementa os metodos da interface LogRecord
    @Override
    public void setName(String name) {
        this.categoryName = name;
    }

    @Override
    public String getName() {
        return "Categoria: " + this.categoryName; 
    }

    @Override
    public void setSeverity(Number severity) {
    }

    @Override
    public Number getSeverity() {
        //A severidade do grupo é a mais alta dos logs que estão dentro
        int maxSeverity = 0;
        for (LogRecord log : children) {
            if (log.getSeverity().intValue() > maxSeverity) {
                maxSeverity = log.getSeverity().intValue();
            }
        }
        return maxSeverity;
    }

}

