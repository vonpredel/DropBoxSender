package sda.dropbox.listener;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;
import sda.dropbox.stats.Stats;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSender implements Runnable {

    private String dir;
    private String name;
    private Stats s;
    private DropBoxSender dropBox;
    public FileSender(String dir, String name, Stats s, DropBoxSender dropBox) {
        this.dir = dir;
        this.name = name;
        this.s = s;
        this.dropBox = dropBox;
    }



    @Override
    public void run() {

        try {
            dropBox.send(dir,name);
        } catch (DbxException e) {
            e.printStackTrace();
        }


//        System.out.println(String.format("Thread: %s, file: %s", Thread.currentThread().getId(), dir));

//        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
//        DbxClientV2 client = new DbxClientV2(config, dropBoxApiKey);
//
//        FullAccount account = null;
//        try {
//            account = client.users().getCurrentAccount();
//        } catch (DbxException e) {
//            e.printStackTrace();
//        }
//        System.out.println(account.getName().getDisplayName());
//
//        try (InputStream in = new FileInputStream(dir + "/" + name)) {
//            FileMetadata metadata = client.files().uploadBuilder("/" + name)
//                    .uploadAndFinish(in);
//        } catch (IOException | DbxException e) {
//            e.printStackTrace();
//        }
    }
}
