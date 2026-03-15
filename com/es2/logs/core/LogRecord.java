package core;

public interface LogRecord {
    String getName();
    Number getSeverity();

    void setName(String name);
    void setSeverity(Number severity);
}