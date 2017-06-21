package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Aleksandr_Ruzanov on 20.06.2017.
 */
public class ProducerConsumer {
    private static final Logger LOG = Logger.getLogger(ProducerConsumer.class);
    private static final int M = 10;
    private static final int N = 15;

    private List<EntityNumber> queue;
    private List<EntityNumber> queueHandled;
    public static final int SIZE_ARRAY = 20;
    private Random random = new Random();

    public ProducerConsumer() {

        queue = new ArrayList<EntityNumber>(SIZE_ARRAY);
        queueHandled = new ArrayList<EntityNumber>();

        for (int i = 1; i < M; i++) {
            new Thread(new Consumer(queue, queueHandled, "Consumer" + i, getRandomValue())).start();
        }

        for (int i = 1; i < N; i++) {
            new Thread(new Producer(queue, "Producer" + i, getRandomValue(), random)).start();
        }
        new Thread(new Filer(queueHandled, "Filer", 10000)).start();


        LOG.info("ProducerConsumer is start");
    }

    private int getRandomValue() {
        return Math.round((random.nextInt(5000) + 1000));
    }

}
