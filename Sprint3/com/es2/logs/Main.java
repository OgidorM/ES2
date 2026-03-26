import config.LogConfig;
import core.BasicLogger;
import core.Logger;
import core.LogCategory;
import core.LogRecord;
import destination.ConsoleDestination;
import destination.FileDestination;
import factory.LogFactory;
import pool.DestinationConnection;
import pool.LogFormatter;
import pool.ObjectPool;
import pool.PoolManager;
import memento.LogCaretaker;
import memento.LogSystemState;

public class Main {
    public static void main(String[] args) {
        try {
            // ----------------------------------------------------------------
            // M1: Configuração do Singleton
            // ----------------------------------------------------------------
            LogConfig config = LogConfig.INSTANCE;
            config.setActive(true);
            config.addLevel("INFO");
            config.addLevel("WARNING");
            config.addLevel("ERROR");
            config.setDestinationPath("app.log");
            config.setMessageFormat("[%t] [%l] - %m");

            // ----------------------------------------------------------------
            // M3: Configuração do Bridge
            // ----------------------------------------------------------------
            Logger logger = new BasicLogger();
            logger.addDestination(new ConsoleDestination());
            logger.addDestination(new FileDestination(config.getDestinationPath()));

            // ----------------------------------------------------------------
            // M2: Criar registos via Factory
            // ----------------------------------------------------------------
            LogRecord log1 = LogFactory.makeLogRecord("INFO");
            LogRecord log2 = LogFactory.makeLogRecord("ERROR");

            // ----------------------------------------------------------------
            // M4: Criar Categorias (Composite)
            // ----------------------------------------------------------------
            LogCategory moduloAuth = new LogCategory("Autenticação");
            moduloAuth.add(log1);
            moduloAuth.add(log2);

            logger.log("INFO", "Início do processamento.");
            logger.log("WARNING", "Problema no módulo: " + moduloAuth.getName());
            System.out.println("Severidade atual do módulo Auth: " + moduloAuth.getSeverity());

            // ----------------------------------------------------------------
            // M5: Object Pool Pattern
            // ----------------------------------------------------------------
            System.out.println("\n=== M5: Object Pool Pattern ===");

            PoolManager poolManager = PoolManager.getInstance();
            ObjectPool<LogFormatter> formatterPool = poolManager.getPool(LogFormatter.class);

            System.out.println("Formatadores disponíveis antes do acquire: " + formatterPool.getAvailableCount());

            // Adquire um formatter do pool
            LogFormatter formatter = formatterPool.acquire();
            formatter.setPattern("[%l] (severity=%s)");

            // Formata um registo de log usando o formatter do pool
            LogRecord infoRecord = LogFactory.makeLogRecord("INFO");
            String formatted = formatter.format(infoRecord);
            System.out.println("Mensagem formatada: " + formatted);

            System.out.println("Formatadores disponíveis após acquire: " + formatterPool.getAvailableCount());
            System.out.println("Formatadores em uso: " + formatterPool.getInUseCount());

            // Liberta o formatter de volta ao pool
            formatterPool.release(formatter);
            System.out.println("Formatadores disponíveis após release: " + formatterPool.getAvailableCount());

            // Demonstração com DestinationConnection
            ObjectPool<DestinationConnection> connPool = poolManager.getPool(DestinationConnection.class);
            DestinationConnection conn = connPool.acquire();
            conn.setEndpoint("http://logs.servidor.pt/api");
            conn.write("Mensagem de teste para o endpoint remoto.");
            connPool.release(conn);

            // ----------------------------------------------------------------
            // M6: Memento Pattern
            // ----------------------------------------------------------------
            System.out.println("\n=== M6: Memento Pattern ===");

            LogSystemState logState = new LogSystemState();
            LogCaretaker caretaker = new LogCaretaker(logState, 10);

            // Configuração inicial
            logState.addLevel("INFO");
            logState.addLevel("WARNING");
            logState.addDestination("Console");
            logState.addFilter("MinLevel=INFO");

            // Guarda snapshot 0
            caretaker.saveState();
            System.out.println("Snapshot 0 guardado: " + logState);

            // Altera configuração
            logState.addLevel("ERROR");
            logState.addDestination("File");
            logState.addFilter("MaxSize=10MB");
            logState.setActive(false);

            // Guarda snapshot 1
            caretaker.saveState();
            System.out.println("Snapshot 1 guardado: " + logState);

            System.out.println("Número de snapshots no histórico: " + caretaker.getHistoryCount());

            // Restaura para snapshot 0
            caretaker.restoreState(0);
            System.out.println("\nEstado após restaurar snapshot 0:");
            System.out.println(logState);

        } catch (Exception e) {
            System.err.println("Erro Crítico: " + e.getMessage());
        }
    }
}
