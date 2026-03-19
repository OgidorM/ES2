package destination;

public class ConsoleDestination implements LogDestination {
    public ConsoleDestination() {
    }

    @Override
    public void write(String message) {
        System.out.println("Console: " + message);
    }
}
