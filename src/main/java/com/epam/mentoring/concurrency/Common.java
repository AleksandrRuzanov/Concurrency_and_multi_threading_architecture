package com.epam.mentoring.concurrency;

import java.util.List;

/**
 * Created by Aleksandr_Ruzanov on 21.06.2017.
 */
public class Common {
    private final String threadName;
    private List<EntityNumber> queue;
    private final int delay;

    public Common(List<EntityNumber> queue, String threadName, int delay) {
        this.queue = queue;
        this.delay = delay;
        this.threadName = threadName;
    }

    public List<EntityNumber> getQueue() {
        return queue;
    }

    public int getDelay() {
        return delay;
    }

    public String getThreadName() {
        return threadName;
    }

    protected void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
