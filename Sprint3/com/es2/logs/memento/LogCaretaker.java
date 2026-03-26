package memento;

import java.util.ArrayList;
import java.util.List;

public class LogCaretaker {

    private final LogSystemState originator;
    private final List<LogMemento> history;
    private final int maxHistory;

    public LogCaretaker(LogSystemState originator, int maxHistory) {
        this.originator = originator;
        this.maxHistory = maxHistory;
        this.history = new ArrayList<>();
    }

    public void saveState() {
        if (history.size() >= maxHistory) {
            history.remove(0); // descarta o mais antigo
        }
        history.add(originator.save());
    }

    public void restoreState(int index) {
        if (history.isEmpty()) {
            throw new IllegalStateException("Histórico vazio. Não há estado para restaurar.");
        }
        if (index < 0 || index >= history.size()) {
            throw new IllegalStateException(
                "Índice inválido: " + index + ". Histórico tem " + history.size() + " entradas."
            );
        }
        originator.restore(history.get(index));
    }

    public int getHistoryCount() { return history.size(); }

    public void clearHistory() { history.clear(); }
}
