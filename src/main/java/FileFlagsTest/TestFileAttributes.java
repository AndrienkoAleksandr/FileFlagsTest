package FileFlagsTest;

import java.io.File;

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
    }
}
