package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class ProducerConsumer {
    private static final Logger LOG = Logger.getLogger(ProducerConsumer.class);
    private static final int M = 2;
    private static final int N = 2;

    private Queue<EntityNumber> queue;
    private Queue<EntityNumber> queueHandled;
    public static final int SIZE_ARRAY = 20;
    private Random random = new Random();

    public ProducerConsumer() {

        queue = new ConcurrentLinkedQueue<EntityNumber>();
        queueHandled = new ConcurrentLinkedQueue<EntityNumber>();

        for (int i = 1; i < M; i++) {
            new Thread(new Consumer(queue, queueHandled, "Consumer" + i, getRandomValue())).start();
        }

        for (int i = 1; i < N; i++) {
            new Thread(new Producer(queue, "Producer" + i, getRandomValue(), random)).start();
        }
        new Thread(new Filer(queueHandled, "Filer", 10000)).start();


        LOG.info("ProducerConsumerObserver is start");
    }

    private int getRandomValue() {
        return Math.round((random.nextInt(5000) + 1000));
    }

}
