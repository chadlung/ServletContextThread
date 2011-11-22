package com.giantflyingsaucer.servletcontextthread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener, Runnable {

    private Thread singleWorkerThread;
    private URLQueue urlQueue;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        urlQueue = new URLQueue();
        sce.getServletContext().setAttribute("URLQueue", urlQueue);
        singleWorkerThread = new Thread(this);
        singleWorkerThread.start();
        System.out.println("ServletContextListener started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        singleWorkerThread.interrupt();
        System.out.println("ServletContextListener destroyed");
    }

    @Override
    public void run() {
        try {
            while (true) {
                //System.out.println("Thread working...");
                String url = urlQueue.getURL();
                // TODO: Do something with the URL other than writing it to console
                if (url != null) {
                    System.out.println(url);
                }
                //Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread stopped");
        }
    }
}
