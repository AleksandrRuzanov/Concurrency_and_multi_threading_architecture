package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.Random;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class Producer extends Common implements Runnable {
    private static final Logger LOG = Logger.getLogger(Producer.class);
    private final Random random;

    public Producer(Queue<EntityNumber> queue, String threadName, int delay, Random random) {
        super(queue, threadName, delay);
        this.random = random;
    }

    public void run() {
        Thread.currentThread().setName(getThreadName());
        LOG.info("Producer->" + getThreadName() + " delay->" + getDelay() + " is start");
        sleep(1000);
        while (true) {
            try {
                putEntity(new EntityNumber(new Integer(random.nextInt(10000))));
                sleep(getDelay());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void putEntity(EntityNumber entity) throws InterruptedException {
        while (getQueue().size() >= ProducerConsumer.SIZE_ARRAY) {
            LOG.info("!!!queue is busy");
            sleep(getDelay());
        }
        LOG.info("add queue queue.size=" + getQueue().size());
        getQueue().add(entity);

    }
}
