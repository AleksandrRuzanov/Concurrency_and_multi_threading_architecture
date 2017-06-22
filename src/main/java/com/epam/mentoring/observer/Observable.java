package com.epam.mentoring.observer;

/**
 * Created by Aleksandr_Ruzanov on 22.06.2017.
 */
public interface Observable {
    void registerObserver(Observer observer);
    void notifyObservers() throws InterruptedException;
}
