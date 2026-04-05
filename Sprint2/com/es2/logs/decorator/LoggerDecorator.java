package decorator;

import core.Logger;
import exceptions.LogSystemInactiveException;
import exceptions.UndefinedLogTypeException;

public abstract class LoggerDecorator extends Logger {
    protected Logger logger;

    public LoggerDecorator(Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    public void log(String type, String message) throws LogSystemInactiveException, UndefinedLogTypeException {
        // Delega a chamada para o logger Matrioshka
        this.logger.log(type, message);
    }

    @Override
    protected void dispatchLog(String formattedMessage) {
        // Quem vai executar o dispatchLog real é o BasicLogger lá no finalzinho.
    }
}