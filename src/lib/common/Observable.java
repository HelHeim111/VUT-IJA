package ija.ija2022.homework2.common.lib.common;

/**
 * The interface Observable.
 */
public interface Observable {
    /**
     * Add observer.
     *
     * @param var1 the var 1
     */
    void addObserver(Observer var1);

    /**
     * Remove observer.
     *
     * @param var1 the var 1
     */
    void removeObserver(Observer var1);

    /**
     * Notify observers.
     */
    void notifyObservers();

    /**
     * The interface Observer.
     */
    public interface Observer {
        /**
         * Update.
         *
         * @param var1 the var 1
         */
        void update(Observable var1);
    }
}
