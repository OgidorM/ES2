import config.LogConfig;
import core.BasicLogger;
import core.Logger;
import destination.ConsoleDestination;
import destination.FileDestination;

public class Main {
    public static void main(String[] args) {
        try {
            LogConfig config = LogConfig.INSTANCE;
            config.setActive(true);
            config.addLevel("INFO");
            config.addLevel("WARNING");
            config.addLevel("ERROR");
            config.setDestinationPath("app.log");
            config.setMessageFormat("[%t] [%l] - %m");

            // 1. Criar a Abstração Refinada
            Logger logger = new BasicLogger();

            // 2. Registar os serviços/destinos
            logger.addDestination(new ConsoleDestination());
            logger.addDestination(new FileDestination("app.log"));

            // 3. Usar o sistema
            logger.log("INFO", "A aplicação iniciou com múltiplos destinos!");

        } catch (Exception e) {
            System.err.println("Erro Crítico: " + e.getMessage());
        }
    }
}
