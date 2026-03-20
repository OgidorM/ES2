package factory;

import core.LogRecord;

public abstract class LogFactory {

    public LogFactory() {
    }

    public static LogRecord makeLogRecord(String type) throws UndefinedLogTypeException {
        if (type == null) {
            throw new UndefinedLogTypeException("Type cannot be null");
        }

        LogRecord record;
        String t = type.trim().toUpperCase();

        switch (t) {
            case "INFO":
                record = new InfoRecord();
                break;
            case "WARNING":
                record = new WarningRecord();
                break;
            case "ERROR":
                record = new ErrorRecord();
                break;
            //case: "NOVO_TIPO"
            //record = new NewRecord();
            //break;
            default:
                throw new UndefinedLogTypeException("Undefined log type: " + type);
        }

        record.setName(t);
        return record;
    }
}
