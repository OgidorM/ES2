package core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import config.LogConfig;
import destination.LogDestination;
import exceptions.LogSystemInactiveException;
import exceptions.UndefinedLogTypeException;
import factory.LogFactory;

public abstract class Logger {
    protected List<LogDestination> destinations;

    protected Logger() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(LogDestination dest) {
        this.destinations.add(dest);
    }

    public void log(String type, String message) throws LogSystemInactiveException, UndefinedLogTypeException {
        LogConfig config = LogConfig.INSTANCE;

        if (!config.isActive()) {
            throw new LogSystemInactiveException("O sistema de logs não está ativo.");
        }

        if (!config.getLevels().contains(type.toUpperCase())) {
            throw new UndefinedLogTypeException("O nível " + type + " não está ativo nas configurações.");
        }

        try {
            LogRecord record = LogFactory.makeLogRecord(type);

            String formattedMessage = config.getMessageFormat()
                    .replace("%l", record.getName())
                    .replace("%m", message)
                    .replace("%t", LocalDateTime.now().toString());

            dispatchLog(formattedMessage);
        } catch (Exception e) {
            System.err.println("Erro ao gerar log (" + type + "): " + e.getMessage());
        }
    }

    protected abstract void dispatchLog(String formattedMessage);
}
