package pool;

public class DestinationConnection implements IPoolable {

    private String endpoint;
    private boolean inUse;

    public DestinationConnection() {
        this.endpoint = "default://localhost";
        this.inUse = false;
    }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public void write(String message) {
        System.out.println("DestinationConnection [" + endpoint + "] >> " + message);
    }

    @Override
    public void reset() {
        this.endpoint = "default://localhost";
        this.inUse = false;
    }

    @Override
    public boolean isInUse() { return inUse; }

    @Override
    public void markInUse(boolean v) { this.inUse = v; }
}
