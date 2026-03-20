import factory.*;
import core.*;

public class Main {
    public static void main(String[] args) {
        try {
            LogRecord tipo = LogFactory.makeLogRecord("WARNING");
            tipo.setSeverity(1);
            System.out.println(tipo.getSeverity());
            System.out.println(tipo.getName());

            LogRecord tipo2 = LogFactory.makeLogRecord("ERROR");
            tipo2.setSeverity(2);
            System.out.println(tipo2.getSeverity());
            System.out.println(tipo2.getName());
        } catch (UndefinedLogTypeException e) {
              System.out.println("Erro: " + e.getMessage());
        }
    }
}
