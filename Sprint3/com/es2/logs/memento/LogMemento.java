package memento;

import java.util.List;

public class LogMemento {

    private final MementoState state;

    // Construtor package-private: só classes do mesmo pacote podem criar mementos
    LogMemento(List<String> levels, List<String> destinations,
               List<String> filters, boolean isActive) {
        this.state = new MementoState(levels, destinations, filters, isActive);
    }

    // Acesso package-private: o caretaker não consegue inspecionar o estado diretamente
    MementoState getState() {
        return state;
    }
}
