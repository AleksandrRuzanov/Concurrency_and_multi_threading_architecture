package com.epam.mentoring.observer;

import com.epam.mentoring.concurrency.EntityNumber;

/**
 * Created by Aleksandr_Ruzanov on 22.06.2017.
 */
public interface Observer {
    void getEntity() throws InterruptedException;
}
