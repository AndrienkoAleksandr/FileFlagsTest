package FileFlagsTest;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.attribute.AclEntryPermission.READ_DATA;
import static java.nio.file.attribute.AclEntryPermission.WRITE_DATA;
import static java.nio.file.attribute.AclEntryPermission.APPEND_DATA;
import static java.nio.file.attribute.AclEntryPermission.READ_NAMED_ATTRS;
import static java.nio.file.attribute.AclEntryPermission.WRITE_NAMED_ATTRS;
import static java.nio.file.attribute.AclEntryPermission.READ_ATTRIBUTES;
import static java.nio.file.attribute.AclEntryPermission.WRITE_ATTRIBUTES;
import static java.nio.file.attribute.AclEntryPermission.DELETE;
import static java.nio.file.attribute.AclEntryPermission.READ_ACL;
import static java.nio.file.attribute.AclEntryPermission.WRITE_ACL;
import static java.nio.file.attribute.AclEntryPermission.SYNCHRONIZE;

public class TestFileAttributesWindows {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/t2.txt");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.createFile(path);

        FileWriter fileWriter = new FileWriter(path.toFile());
        fileWriter.write("some text");
        fileWriter.close();

        AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);

        AclEntry.Builder builder = AclEntry.newBuilder();
        builder.setType(AclEntryType.ALLOW);

        String userName = System.getProperty("user.name");
        UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(userName);

        builder.setPrincipal(userPrincipal);
        builder.setPermissions(
                READ_DATA,
                WRITE_DATA,
                APPEND_DATA,
                READ_NAMED_ATTRS,
                WRITE_NAMED_ATTRS,
                READ_ATTRIBUTES,
                WRITE_ATTRIBUTES,
                DELETE,
                READ_ACL,
                WRITE_ACL,
                SYNCHRONIZE
        );

        AclEntry entry = builder.build();
        List<AclEntry> aclEntryList = new ArrayList<AclEntry>();
        aclEntryList.add(0, entry);
        view.setAcl(aclEntryList);

        FileReader fileReader = new FileReader(path.toFile());

        int i;
        while ((i = fileReader.read()) != -1) {
            System.out.print((char)i);
        }

        fileReader.close();

    }
}
