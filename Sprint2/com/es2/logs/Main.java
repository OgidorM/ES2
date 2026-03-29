import config.LogConfig;
import core.BasicLogger;
import core.Logger;
import core.LogCategory; // M4
import core.LogRecord;   // M4/M2 interface
import destination.ConsoleDestination;
import destination.FileDestination;
import factory.LogFactory;       // M2
import memento.LogCaretaker;     // M6

public class Main {
    public static void main(String[] args) {
        try {
            // M1: Configuração do Singleton
            LogConfig config = LogConfig.INSTANCE;
            config.setActive(true);
            config.addLevel("INFO");
            config.addLevel("WARNING");
            config.addLevel("ERROR");
            config.setDestinationPath("app.log");
            config.setMessageFormat("[%t] [%l] - %m");

            // M3: Configuração do Bridge
            Logger logger = new BasicLogger();
            logger.addDestination(new ConsoleDestination());
            logger.addDestination(new FileDestination(config.getDestinationPath()));

            // M2: Criar registos individuais via Factory
            LogRecord log1 = LogFactory.makeLogRecord("INFO");
            LogRecord log2 = LogFactory.makeLogRecord("ERROR");

            // M4: Criar Categorias (Composite)
            LogCategory moduloAuth = new LogCategory("Autenticação");
            moduloAuth.add(log1);
            moduloAuth.add(log2);

            // 1. Log Simples
            logger.log("INFO", "Início do processamento.");

            // 2. Log que usa a informação da Categoria (M4)
            logger.log("WARNING", "Problema no módulo: " + moduloAuth.getName());

            // 3. Demonstrar a severidade calculada do Composite
            System.out.println("Severidade do módulo Auth: " + moduloAuth.getSeverity());

            // -------------------------------------------------------------------
            // M6: Memento — guardar e restaurar estado do sistema de logs
            // -------------------------------------------------------------------
            LogCaretaker caretaker = new LogCaretaker(logger);

            // Snapshot do estado actual
            caretaker.backup();
            System.out.println("\n[M6] Estado guardado:"
                    + " levels="      + config.getLevels()
                    + " | filters="   + config.getFilters()
                    + " | destinos="  + logger.getDestinations().size());

            // Alterar o estado: novos níveis, filtro e destino extra
            config.addLevel("DEBUG");
            config.addFilter("auth-module");
            logger.addDestination(new ConsoleDestination()); // destino extra
            System.out.println("[M6] Estado alterado:"
                    + " levels="     + config.getLevels()
                    + " | filters="  + config.getFilters()
                    + " | destinos=" + logger.getDestinations().size());

            // Restaurar ao estado anterior via undo
            caretaker.undo();
            System.out.println("[M6] Estado restaurado:"
                    + " levels="     + config.getLevels()
                    + " | filters="  + config.getFilters()
                    + " | destinos=" + logger.getDestinations().size());

        } catch (Exception e) {
            System.err.println("Erro Crítico: " + e.getMessage());
        }
    }
}
