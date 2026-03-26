package factory;

import core.LogRecord;

public class WarningRecord implements LogRecord {
    private String name;
    private Number severity;

    protected WarningRecord() {
    }

    @Override
    public String getName() { return this.name; }

    @Override
    public Number getSeverity() { return this.severity; }

    @Override
    public void setName(String name) { if (name != null) this.name = name; }

    @Override
    public void setSeverity(Number severity) { if (severity != null) this.severity = severity; }
}
