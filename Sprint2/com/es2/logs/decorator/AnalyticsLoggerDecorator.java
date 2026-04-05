package decorator;

import core.Logger;
import exceptions.LogSystemInactiveException;
import exceptions.UndefinedLogTypeException;
import java.util.HashMap;
import java.util.Map;

public class AnalyticsLoggerDecorator extends LoggerDecorator {
    private Map<String, Integer> errorCount;

    public AnalyticsLoggerDecorator(Logger logger) {
        super(logger);
        this.errorCount = new HashMap<>();
    }

    @Override
    public void log(String type, String message) throws LogSystemInactiveException, UndefinedLogTypeException {
        super.log(type, message);

        // 2. Registra o Analytics (contabilizando os padrões de erro)
        errorCount.put(type, errorCount.getOrDefault(type, 0) + 1);
    }

    public Map<String, Integer> getStats() {
        return errorCount;
    }

    public void printStats() {
        System.out.println("Contagem de Logs por Tipo: " + errorCount);
    }
}