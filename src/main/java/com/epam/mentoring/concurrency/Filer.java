package com.epam.mentoring.concurrency;

import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Aleksandr_Ruzanov on 21.06.2017.
 */
public class Filer extends Common implements Runnable {

    private static final Logger LOG = Logger.getLogger(Filer.class);

    public Filer(List<EntityNumber> queue, String threadName, int delay) {
        super(queue, threadName, delay);
    }


    public void run() {
        Thread.currentThread().setName(getThreadName());
        LOG.info("Filer->" + getThreadName() + " delay->" + getDelay() + " is start");
        sleep(1000);
        while (true) {
            sleep(getDelay());
            saveQueue();
        }
    }

    private void saveQueue() {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("output.txt"));
            PrintWriter pwUn = new PrintWriter(new FileOutputStream("outputUn.txt"));
            List<EntityNumber> saveList;

            synchronized (getQueue()) {
                saveList = new ArrayList<EntityNumber>(getQueue());
            }

            LOG.info("saveQueue queue queue.size=" + getQueue().size());
            for (EntityNumber entityNumber : saveList)
                pwUn.println(entityNumber.getValueName());

            Collections.sort(saveList, new Comparator<EntityNumber>() {
                public int compare(EntityNumber o1, EntityNumber o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            for (EntityNumber entityNumber : saveList) {
                pw.println(entityNumber.getValueName());
            }

            saveList = null;
            pw.close();
            pwUn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
