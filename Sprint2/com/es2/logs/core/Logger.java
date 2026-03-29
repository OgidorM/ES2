package core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import config.LogConfig;
import destination.LogDestination;
import exceptions.LogSystemInactiveException;
import exceptions.UndefinedLogTypeException;
import com.es2.objectpool.ReusablePool;

public abstract class Logger {
    protected List<LogDestination> destinations;

    protected Logger() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(LogDestination dest) {
        this.destinations.add(dest);
    }

    public List<LogDestination> getDestinations() {
        return destinations;
    }

    public void log(String type, String message) throws LogSystemInactiveException, UndefinedLogTypeException {
        LogConfig config = LogConfig.INSTANCE;

        // Verificação se está ativo o Módulo de logs
        if (!config.isActive()) {
            throw new LogSystemInactiveException("O sistema de logs não está ativo.");
        }

        // Verificação se o tipo de log esta na lista permitida
        if (!config.getLevels().contains(type.toUpperCase())) {
            throw new UndefinedLogTypeException("O nível " + type + " não está ativo nas configurações.");
        }

        try {
            LogRecord record = ReusablePool.getInstance().acquire(type);

            String formattedMessage = config.getMessageFormat()
                    .replace("%l", record.getName())
                    .replace("%m", message)
                    .replace("%t", LocalDateTime.now().toString());

            // A bridge faz o trabalho delegando na classe filha
            dispatchLog(formattedMessage);

            ReusablePool.getInstance().release(record);
        } catch (Exception e) {
            System.err.println("Erro ao gerar log (" + type + "): " + e.getMessage());
        }
    }

    protected abstract void dispatchLog(String formattedMessage);

    // --- M6: Memento (Originator) ---

    public LogSystemMemento saveState() {
        LogConfig config = LogConfig.INSTANCE;
        return new LogSystemMemento(destinations, config.getLevels(), config.getFilters());
    }

    public void restoreState(LogSystemMemento memento) {
        this.destinations = memento.getSavedDestinations();
        LogConfig config = LogConfig.INSTANCE;
        config.setLevels(memento.getSavedLevels());
        config.setFilters(memento.getSavedFilters());
    }
}
