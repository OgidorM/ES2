package decorator;

import core.Logger;
import exceptions.LogSystemInactiveException;
import exceptions.UndefinedLogTypeException;

public class AlertLoggerDecorator extends LoggerDecorator {

    public AlertLoggerDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String type, String message) throws LogSystemInactiveException, UndefinedLogTypeException {
        super.log(type, message);

        // 2. Se passou sem exceções, executa a funcionalidade extra
        if ("ERROR".equalsIgnoreCase(type)) {
            sendAlert(message);
        }
    }

    private void sendAlert(String message) {
        // Simulação do envio automático de alertas para administradores
        System.out.println("ALERTA DE ADMINISTRAÇÃO Erro detectado: \"" + message + "\" -> Disparando e-mail...");
    }
}