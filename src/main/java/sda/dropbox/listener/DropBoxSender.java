package sda.dropbox.listener;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DropBoxSender {

    private final String clientAppKeyy;
    private DbxClientV2 client;


    public DropBoxSender(String clientAppKeyy) {

        this.clientAppKeyy = clientAppKeyy;
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        client = new DbxClientV2(config, clientAppKeyy);

    }

    public void send(String dir, String FileName) throws DbxException {
        try (InputStream in = new FileInputStream(dir + "/" + FileName)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + FileName)
                    .uploadAndFinish(in);
        } catch (IOException e) {
            e.printStackTrace();
//        }
    }

}}