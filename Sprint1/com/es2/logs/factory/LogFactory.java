package factory;

import config.LogConfig;
import core.LogRecord;

public abstract class LogFactory {

    public LogFactory() {
    }

    public static LogRecord makeLogRecord(String type) throws UndefinedLogTypeException, LogSystemInactiveException {
        if (type == null) {
            throw new UndefinedLogTypeException("Type cannot be null");
        }
        LogConfig config = LogConfig.INSTANCE;
        LogRecord record;
        String t = type.trim().toUpperCase();
        Number severity;

        if (!config.isActive()) {
            throw new LogSystemInactiveException("O sistema de logs não está ativo.");
        }

        if (!config.getLevels().contains(t)) {
            throw new UndefinedLogTypeException("O nível " + t + " não está ativo nas configurações.");
        }

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
