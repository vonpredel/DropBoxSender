package sda.dropbox.listener;

import sda.dropbox.stats.Stats;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileDispatcher {

    private ExecutorService executor;
    private Stats s;
    private DropBoxSender dsss;

    public FileDispatcher(int threads, Stats s, DropBoxSender ds) {
        this.executor = Executors.newFixedThreadPool(threads);
        this.s = s;
        this.dsss = ds;
    }

    public void listen(String dir) {
        Path path = Paths.get(dir);
        FileSystem fs = path.getFileSystem();

        try (WatchService service = fs.newWatchService()) {
            path.register(service, ENTRY_CREATE);
            WatchKey key;
            while (true) {
                key = service.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {

                    Path newPath = ((WatchEvent<Path>) watchEvent).context();
                    executor.submit(new FileSender(dir, newPath.toString(), s, dsss));
                }

                if (!key.reset()) break;
            }

        } catch (IOException | InterruptedException ioe) {
            ioe.printStackTrace();
        }
    }
}
