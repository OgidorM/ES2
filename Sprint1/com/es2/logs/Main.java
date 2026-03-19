import config.LogConfig;
import factory.LogFactory;
import factory.UndefinedLogTypeException;
import factory.LogSystemInactiveException;
import core.LogRecord;

public class Main {
    public static void main(String[] args) {
        
        LogConfig config = LogConfig.INSTANCE;
        
        // Add um tipo novo
        config.addLevel("ERROR");
        config.addLevel("WARNING");
        
        System.out.println("O sistema de logs está ativo? " + config.isActive());
        System.out.println("Níveis Ativos: " + config.getLevels());
        System.out.println("Formato: " + config.getMessageFormat());
        System.out.println("-------------------------------------------------");
        
        //1. Simular a tentativa de logar um ERROR
        String tipoDesejado1 = "ERROR";
        String mensagem1 = "Ocorreu um erro crítico no sistema.";

        //Verificações
        if (config.isActive() && config.getLevels().contains(tipoDesejado1.toUpperCase())) {
            try {
                LogRecord record = LogFactory.makeLogRecord(tipoDesejado1);
                
                String output = config.getMessageFormat()
                        .replace("%l", record.getName())
                        .replace("%m", mensagem1)
                        .replace("%t", java.time.LocalDateTime.now().toString());
                
                System.out.println(output);
            } catch (UndefinedLogTypeException | LogSystemInactiveException e) {
                System.out.println("Erro: Tipo não está registado na fábrica - " + e.getMessage());
            }
        } else {
            System.out.println("Logs do tipo " + tipoDesejado1 + " não foram processados (desativados ou não incluídos nos levels).");
        }

        //2. Simular a tentativa de logar um DEBUG (que não está ativado no config)
        String tipoDesejado2 = "DEBUG";
        String mensagem2 = "Variável X tem o valor 10.";

        //Verificações
        if (config.isActive() && config.getLevels().contains(tipoDesejado2.toUpperCase())) {
            try {
                LogRecord record = LogFactory.makeLogRecord(tipoDesejado2);
                String output = config.getMessageFormat().replace("%l", record.getName()).replace("%m", mensagem2);
                System.out.println(output);
            } catch (UndefinedLogTypeException | LogSystemInactiveException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Logs do tipo " + tipoDesejado2 + " não foram processados (desativados ou não incluídos nos levels).");
        }
    }
}
