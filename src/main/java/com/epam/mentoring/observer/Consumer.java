package com.epam.mentoring.observer;

import com.epam.mentoring.concurrency.EntityNumber;
import org.apache.log4j.Logger;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class Consumer implements Observer, Runnable {
    private static final Logger LOG = Logger.getLogger(Consumer.class);
    private final ProducerConsumerObserver producerConsumer;
    private final int delay;
    private final String threadName;

    public Consumer(ProducerConsumerObserver producerConsumer, String threadName, int delay) {
        Thread.currentThread().setName(threadName);
        LOG.info("Consumer->" + threadName + " delay->" + delay + " is start");
        this.producerConsumer = producerConsumer;
        this.delay = delay;
        this.producerConsumer.registerObserver(this);
        this.threadName = threadName;
    }

    public void getEntity() throws InterruptedException {
        Thread.sleep(delay);
        new Thread(this).start();
    }

    public void run() {
        EntityNumber entity = producerConsumer.getQueue().poll();
        if (entity != null) {
            entity.setValueName(entity.getValue() + " - number was handled");
            LOG.info("get observer is empty=" + entity + " queue.size=" + producerConsumer.getQueue().size());
            producerConsumer.getQueueHandled().add(entity);
        }
    }
}
