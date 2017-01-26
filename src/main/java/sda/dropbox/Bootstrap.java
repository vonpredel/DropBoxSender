package sda.dropbox;

import sda.dropbox.listener.DropBoxSender;
import sda.dropbox.listener.FileDispatcher;
import sda.dropbox.stats.Stats;
import sda.dropbox.stats.StatsService;

public class Bootstrap {



    public static void main(String[] args) {
        int threads = Integer.parseInt(args[0]);
        String dropBoxApiKey = args[1];
        String directory = "C:\\DropBoxInput";
        Stats s = new Stats();

        DropBoxSender ds = new DropBoxSender(dropBoxApiKey);

        new StatsService(s).displayStats();
        new FileDispatcher(threads, s, ds).listen(directory);
    }
}

