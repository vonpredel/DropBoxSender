package sda.dropbox;


import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.users.FullAccount;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    private static final String ACCESS_TOKEN = "PwPNORY5qcAAAAAAAAAAwKcUx3kF61neFpKhcFSiUkjtDo2CwZxQeGp2_KVwQGxE";

    public static void main(String args[]) throws DbxException {
        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        try (InputStream in = new FileInputStream("C:\\DropBoxInput\\test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/chuj.txt")
                    .uploadAndFinish(in);
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
