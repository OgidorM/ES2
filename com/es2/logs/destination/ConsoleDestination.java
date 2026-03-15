package destination;

public class ConsoleDestination implements LogDestination {
    
    @Override
    public void write(String message) {
        System.out.println("Console: " + message);
    }
}
