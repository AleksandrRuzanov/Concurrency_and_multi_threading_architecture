package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class Consumer extends Common implements Runnable {
    private static final Logger LOG = Logger.getLogger(Consumer.class);
    private List<EntityNumber> queueHandled;

    public Consumer(List<EntityNumber> queue, List<EntityNumber> queueHandled, String threadName, int delay) {
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
            synchronized (getQueue()) {
                LOG.info("!!!queue is empty");
                getQueue().wait();
            }
        }
        EntityNumber entity = null;
        synchronized (getQueue()) {
            if (!getQueue().isEmpty())
                entity = getQueue().remove(0);
            if (entity != null) {
                entity.setValueName(entity.getValue() + " - number was handled");
                LOG.info("get is empty=" + entity + " queue.size=" + getQueue().size());
            } else
                LOG.info("get is empty is null");
            getQueue().notifyAll();
        }
        if (entity != null)
            synchronized (queueHandled) {
                queueHandled.add(entity);
            }
        return entity;

    }
}
