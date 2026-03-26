package memento;

import java.util.ArrayList;
import java.util.List;

// Originator
public class LogSystemState {

    private List<String> activeLevels;
    private List<String> activeDestinations;
    private List<String> activeFilters;
    private boolean isActive;

    public LogSystemState() {
        this.activeLevels = new ArrayList<>();
        this.activeDestinations = new ArrayList<>();
        this.activeFilters = new ArrayList<>();
        this.isActive = true;
    }

    // --- Mutators ---
    public void addLevel(String level) { activeLevels.add(level); }
    public void removeLevel(String level) { activeLevels.remove(level); }
    public void addDestination(String destination) { activeDestinations.add(destination); }
    public void addFilter(String filter) { activeFilters.add(filter); }
    public void setActive(boolean active) { this.isActive = active; }

    // --- Getters (para leitura no Main) ---
    public List<String> getActiveLevels() { return activeLevels; }
    public List<String> getActiveDestinations() { return activeDestinations; }
    public List<String> getActiveFilters() { return activeFilters; }
    public boolean isActive() { return isActive; }

    // --- Memento ---
    public LogMemento save() {
        return new LogMemento(
            new ArrayList<>(activeLevels),
            new ArrayList<>(activeDestinations),
            new ArrayList<>(activeFilters),
            isActive
        );
    }

    public void restore(LogMemento memento) {
        MementoState state = memento.getState();
        this.activeLevels = new ArrayList<>(state.getLevels());
        this.activeDestinations = new ArrayList<>(state.getDestinations());
        this.activeFilters = new ArrayList<>(state.getFilters());
        this.isActive = state.isActive();
    }

    @Override
    public String toString() {
        return "LogSystemState{" +
                "activeLevels=" + activeLevels +
                ", activeDestinations=" + activeDestinations +
                ", activeFilters=" + activeFilters +
                ", isActive=" + isActive +
                '}';
    }
}
