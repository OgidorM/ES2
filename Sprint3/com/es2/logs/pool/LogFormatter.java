package pool;

import core.LogRecord;

public class LogFormatter implements IPoolable {

    private String pattern;
    private boolean inUse;

    public LogFormatter() {
        this.pattern = "[%l] [severity=%s] - %m";
        this.inUse = false;
    }

    public String getPattern() { return pattern; }
    public void setPattern(String pattern) { this.pattern = pattern; }

    public String format(LogRecord entry) {
        return pattern
                .replace("%l", entry.getName() != null ? entry.getName() : "UNKNOWN")
                .replace("%s", entry.getSeverity() != null ? entry.getSeverity().toString() : "0")
                .replace("%m", entry.getName() != null ? entry.getName() : "");
    }

    @Override
    public void reset() {
        this.pattern = "[%l] [severity=%s] - %m";
        this.inUse = false;
    }

    @Override
    public boolean isInUse() { return inUse; }

    @Override
    public void markInUse(boolean v) { this.inUse = v; }
}
