package FileFlagsTest;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.EnumSet;
import java.util.Set;

import static java.nio.file.attribute.PosixFilePermission.OWNER_READ;
import static java.nio.file.attribute.PosixFilePermission.OWNER_WRITE;

/**
 *
 */
public class TestFileAttributesLinux {

    public static void main( String[] args ) throws Exception {
        boolean perms;

        File file = new File("src/main/resources/identity");
        file.createNewFile();
//        perms = file.setReadOnly();
//        //set perm to ----------
//        perms &= file.setReadable(false, false);
//        //set perm to -r--------
//        perms &= file.setReadable(true, true);
//        //set perm to -rw-------
//        perms &= file.setWritable(true, true);

//        System.out.println("expected false: " + !perms);

//        Set<PosixFilePermission> permssions =
//                PosixFilePermissions.fromString("rw-------");

        Set<PosixFilePermission> permssions = EnumSet.of(OWNER_READ, OWNER_WRITE);
        Files.setPosixFilePermissions(file.toPath(), permssions);
    }
}
