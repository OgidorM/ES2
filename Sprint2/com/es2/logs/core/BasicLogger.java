package core;

import destination.LogDestination;

public class BasicLogger extends Logger {

    public BasicLogger() {
        super();
    }

    @Override
    protected void dispatchLog(String formattedMessage) {
        for (LogDestination dest : this.destinations) {
            dest.write(formattedMessage);
        }
    }
}