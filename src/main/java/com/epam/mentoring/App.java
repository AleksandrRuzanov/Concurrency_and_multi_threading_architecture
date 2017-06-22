package com.epam.mentoring;

import com.epam.mentoring.concurrency.ProducerConsumer;
import com.epam.mentoring.observer.ProducerConsumerObserver;

public class App {
    public static void main(String[] args) {
        //ProducerConsumer pc = new ProducerConsumer();
        new ProducerConsumerObserver();
    }
}
