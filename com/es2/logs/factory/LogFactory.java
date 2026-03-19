package factory;

import core.LogRecord;
import exceptions.UndefinedLogTypeException;

public abstract class LogFactory {

    public LogFactory() {
    }

    public static LogRecord makeLogRecord(String type) throws UndefinedLogTypeException {
        if (type == null) {
            throw new UndefinedLogTypeException("Type cannot be null");
        }
        LogRecord record;
        String t = type.trim().toUpperCase();
        Number severity;

        switch (t) {
            case "INFO":
                record = new InfoRecord();
                severity = 1;
                break;
            case "WARNING":
                record = new WarningRecord();
                severity = 2;
                break;
            case "ERROR":
                record = new ErrorRecord();
                severity = 3;
                break;
            //case: "NOVO_TIPO"
            //record = new NewRecord();
            //severity = 4;
            //break;
            default:
                throw new UndefinedLogTypeException("Undefined log type: " + type);
        }
        record.setName(t);
        record.setSeverity(severity);
        return record;
    }
}
