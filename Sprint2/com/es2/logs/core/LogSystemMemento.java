package core;

import destination.LogDestination;

import java.util.ArrayList;
import java.util.List;

public class LogSystemMemento {

    private final List<LogDestination> savedDestinations;
    private final List<String> savedLevels;
    private final List<String> savedFilters;

    // ~ package-private: só Logger (mesmo pacote) pode criar um Memento
    LogSystemMemento(List<LogDestination> destinations, List<String> levels, List<String> filters) {
        this.savedDestinations = new ArrayList<>(destinations);
        this.savedLevels       = new ArrayList<>(levels);
        this.savedFilters      = new ArrayList<>(filters);
    }

    // ~ package-private: só Logger (mesmo pacote) pode ler o estado guardado
    List<LogDestination> getSavedDestinations() { return new ArrayList<>(savedDestinations); }
    List<String> getSavedLevels()               { return new ArrayList<>(savedLevels); }
    List<String> getSavedFilters()              { return new ArrayList<>(savedFilters); }
}
