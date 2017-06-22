package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.util.Queue;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class Consumer extends Common implements Runnable {
    private static final Logger LOG = Logger.getLogger(Consumer.class);
    private Queue<EntityNumber> queueHandled;

    public Consumer(Queue<EntityNumber> queue, Queue<EntityNumber> queueHandled, String threadName, int delay) {
        super(queue, threadName, delay);
        this.queueHandled = queueHandled;

    }

    public void run() {
        Thread.currentThread().setName(getThreadName());
        LOG.info("Consumer->" + getThreadName() + " delay->" + getDelay() + " is start");
        sleep(1000);
        while (true) {
            try {
                getEntity();
                sleep(getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private EntityNumber getEntity() throws InterruptedException {
        while (getQueue().isEmpty()) {
            LOG.info("!!!queue is empty");
            sleep(getDelay());
        }
        EntityNumber entity = getQueue().poll();
        entity.setValueName(entity.getValue() + " - number was handled");
        LOG.info("get is empty=" + entity + " queue.size=" + getQueue().size());
        queueHandled.add(entity);
        return entity;

    }
}
