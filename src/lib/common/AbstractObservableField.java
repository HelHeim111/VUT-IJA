package ija.ija2022.homework2.common.lib.common;


import java.util.HashSet;
import java.util.Set;

/**
 * The type Abstract observable field.
 */
public abstract class AbstractObservableField implements CommonField {
    private final Set<Observable.Observer> observers = new HashSet();

    /**
     * Instantiates a new Abstract observable field.
     */
    public AbstractObservableField() {
    }

    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        this.observers.forEach((o) -> {
            o.update(this);
        });
    }
}
