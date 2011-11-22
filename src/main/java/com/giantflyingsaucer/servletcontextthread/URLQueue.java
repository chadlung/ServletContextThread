package com.giantflyingsaucer.servletcontextthread;

import java.util.LinkedList;


public class URLQueue {
    LinkedList<String> queue = new LinkedList<String>();

    // Add work to the work queue
    public synchronized void addURL(String url) {
        queue.addLast(url);
        notify();
    }

    // Retrieve work from the work queue; block if the queue is empty
    public synchronized String getURL() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.removeFirst();
    }
}
