package memento;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MementoState {

    private final List<String> levels;
    private final List<String> destinations;
    private final List<String> filters;
    private final boolean isActive;
    private final Date timestamp;

    public MementoState(List<String> levels, List<String> destinations,
                        List<String> filters, boolean isActive) {
        this.levels = Collections.unmodifiableList(levels);
        this.destinations = Collections.unmodifiableList(destinations);
        this.filters = Collections.unmodifiableList(filters);
        this.isActive = isActive;
        this.timestamp = new Date();
    }

    public List<String> getLevels() { return levels; }
    public List<String> getDestinations() { return destinations; }
    public List<String> getFilters() { return filters; }
    public boolean isActive() { return isActive; }
    public Date getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "MementoState{" +
                "levels=" + levels +
                ", destinations=" + destinations +
                ", filters=" + filters +
                ", isActive=" + isActive +
                ", timestamp=" + timestamp +
                '}';
    }
}
