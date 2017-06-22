package com.epam.mentoring.observer;

import com.epam.mentoring.concurrency.EntityNumber;
import com.epam.mentoring.concurrency.Filer;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class ProducerConsumerObserver implements Observable {
    private static final Logger LOG = Logger.getLogger(ProducerConsumerObserver.class);
    private static final int M = 20;
    private static final int N = 20;

    private Queue<EntityNumber> queue;
    private Queue<EntityNumber> queueHandled;

    private List<Observer> observers;

    public static final int SIZE_ARRAY = 20;
    private Random random = new Random();

    public ProducerConsumerObserver() {

        queue = new ConcurrentLinkedQueue<EntityNumber>();
        queueHandled = new ConcurrentLinkedQueue<EntityNumber>();

        for (int i = 1; i < M; i++) {
            new Consumer(this, "Consumer" + i, getRandomValue());
        }

        for (int i = 1; i < N; i++) {
            new Thread(new Producer(this, "Producer" + i, getRandomValue(), random)).start();
        }

        new Thread(new Filer(queueHandled, "Filer", 10000)).start();


        LOG.info("ProducerConsumerObserver is start");
    }

    private int getRandomValue() {
        return Math.round((random.nextInt(5000) + 1000));
    }


    public Queue<EntityNumber> getQueue() {
        return queue;
    }

    public Queue<EntityNumber> getQueueHandled() {
        return queueHandled;
    }

    public void registerObserver(Observer observer) {
        if (observers == null)
            observers = new LinkedList<Observer>();
        observers.add(observer);

    }

    public void notifyObservers() throws InterruptedException {
        for (Observer observer : observers) {
            observer.getEntity();
            if (getQueue().isEmpty()) {
                LOG.info("ProducerConsumerObserver notifyObservers getQueue().isEmpty - exit");
                return;
            }
        }

    }


}
