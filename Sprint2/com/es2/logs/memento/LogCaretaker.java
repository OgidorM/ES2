package memento;

import core.Logger;
import core.LogSystemMemento;

import java.util.Stack;

public class LogCaretaker {

    private final Logger logger;
    private final Stack<LogSystemMemento> history;

    public LogCaretaker(Logger logger) {
        this.logger  = logger;
        this.history = new Stack<>();
    }

    // Guarda o estado atual do Logger num snapshot opaco
    public void backup() {
        history.push(logger.saveState());
    }

    // Restaura o estado anterior (sem nunca aceder ao interior do Memento)
    public void undo() {
        if (!history.isEmpty()) {
            logger.restoreState(history.pop());
        }
    }
}
