package sda.dropbox.stats;

import java.util.concurrent.atomic.AtomicInteger;

public class Stats {

    private AtomicInteger filesSend = new AtomicInteger(0);

    public void increment() {
        filesSend.incrementAndGet();
    }

    public Integer getAndClear() {
        return filesSend.getAndSet(0);
    }

}
