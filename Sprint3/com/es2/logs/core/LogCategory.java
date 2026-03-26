package core;

import java.util.ArrayList;
import java.util.List;

public class LogCategory implements LogRecord {

    private String categoryName;
    private List<LogRecord> children = new ArrayList<>();

    public LogCategory(String name) {
        this.categoryName = name;
    }

    public void add(LogRecord log) { this.children.add(log); }
    public void remove(LogRecord log) { this.children.remove(log); }
    public List<LogRecord> getChildren() { return this.children; }

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
        int maxSeverity = 0;
        for (LogRecord log : children) {
            if (log.getSeverity().intValue() > maxSeverity) {
                maxSeverity = log.getSeverity().intValue();
            }
        }
        return maxSeverity;
    }
}
