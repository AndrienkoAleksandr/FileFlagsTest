package FileFlagsTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 *
 */
public class TestFileAttributes {

    public static void main( String[] args ) throws Exception {
        boolean perms;

        File file = new File("src/main/resources/identity");
        file.createNewFile();
        perms = file.setReadOnly();
        //set perm to ----------
        perms &= file.setReadable(false, false);
        //set perm to -r--------
        perms &= file.setReadable(true, true);
        //set perm to -rw-------
        perms &= file.setWritable(true, true);

        System.out.println("expected false: " + !perms);

        Set<PosixFilePermission> permssions =
                PosixFilePermissions.fromString("rw-------");

        Files.setPosixFilePermissions(file.toPath(), permssions);
    }
}
