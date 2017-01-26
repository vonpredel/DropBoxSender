package sda.dropbox.stats;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class StatsService {
    private static final int STATS_INTERVAL = 5;

    private ExecutorService executor = newSingleThreadExecutor();
    private Stats s;

    public StatsService(Stats s) {
        this.s = s;
    }

    public void displayStats() {
        executor.submit(() -> {
            while(true) {
                try {
                    Thread.sleep(STATS_INTERVAL * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("files per second: " + (double) s.getAndClear() / STATS_INTERVAL);
            }
        });
    }
}
