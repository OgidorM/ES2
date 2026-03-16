import config.LogConfig;
import core.BasicLogger;
import core.Logger;
import core.LogCategory; // M4
import core.LogRecord;   // M4/M2 interface
import destination.ConsoleDestination;
import destination.FileDestination;
import factory.LogFactory; // M2

public class Main {
    public static void main(String[] args) {
        try {
            //M1: Configuração do Singleton
            LogConfig config = LogConfig.INSTANCE;
            config.setActive(true);
            config.addLevel("INFO");
            config.addLevel("WARNING");
            config.addLevel("ERROR");
            config.setDestinationPath("app.log");
            config.setMessageFormat("[%t] [%l] - %m");

            //M3: Configuração do Bridge
            Logger logger = new BasicLogger();
            logger.addDestination(new ConsoleDestination());
            // Passar o path do Singleton para o destino (Injeção de Dependência)
            logger.addDestination(new FileDestination(config.getDestinationPath()));

            //M2: Criar registos individuais via Factory
            LogRecord log1 = LogFactory.makeLogRecord("INFO");
            LogRecord log2 = LogFactory.makeLogRecord("ERROR");

            // M4: Criar Categorias (Composite)
            LogCategory moduloAuth = new LogCategory("Autenticação");
            moduloAuth.add(log1);
            moduloAuth.add(log2);

            // ------
            
            // 1. Log Simples
            logger.log("INFO", "Início do processamento.");

            // 2. Log que usa a informação da Categoria (M4)
            logger.log("WARNING", "Problema no módulo: " + moduloAuth.getName());
            
            // 3. Demonstrar a severidade calculada do Composite
            System.out.println("A severidade atual do módulo Auth é: " + moduloAuth.getSeverity());

        } catch (Exception e) {
            System.err.println("Erro Crítico: " + e.getMessage());
        }
    }
}