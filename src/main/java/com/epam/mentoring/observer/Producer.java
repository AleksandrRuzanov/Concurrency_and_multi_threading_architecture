package com.epam.mentoring.observer;

import com.epam.mentoring.concurrency.EntityNumber;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class Producer implements Runnable {
    private static final Logger LOG = Logger.getLogger(Producer.class);
    private final Random random;
    private final ProducerConsumerObserver producerConsumer;
    private final int delay;
    private final String threadName;

    public Producer(ProducerConsumerObserver producerConsumer, String threadName, int delay, Random random) {
        this.random = random;
        this.producerConsumer = producerConsumer;
        this.threadName = threadName;
        this.delay = delay;
    }

    public void run() {
        Thread.currentThread().setName(threadName);
        LOG.info("Producer->" + threadName + " delay->" + delay + " is start");
        while (true) {
            try {
                putEntity(new EntityNumber(new Integer(random.nextInt(10000))));
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void putEntity(EntityNumber entity) throws InterruptedException {
        while (producerConsumer.getQueue().size() >= ProducerConsumerObserver.SIZE_ARRAY) {
            LOG.info("!!!queue is busy!!!");
            Thread.sleep(delay);
        }
        producerConsumer.getQueue().add(entity);
        LOG.info("add queue queue.size=" + producerConsumer.getQueue().size());
        producerConsumer.notifyObservers();

    }
}
