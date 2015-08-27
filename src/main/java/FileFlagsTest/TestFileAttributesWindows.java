package FileFlagsTest;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.List;

public class TestFileAttributesWindows {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/main/resources/a.txt");
        Files.createFile(path);
        AclFileAttributeView view = Files.getFileAttributeView(path, AclFileAttributeView.class);

        AclEntry.Builder builder = AclEntry.newBuilder();
        builder.setType(AclEntryType.ALLOW);

        String userName = System.getProperty("user.name");
        UserPrincipal userPrincipal = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName(userName);

        builder.setPrincipal(userPrincipal);
        builder.setPermissions(AclEntryPermission.READ_ACL, AclEntryPermission.WRITE_ACL, AclEntryPermission.DELETE);

        AclEntry entry = builder.build();
        List<AclEntry> aclEntryList = new ArrayList<AclEntry>();
        aclEntryList.add(0, entry);
        view.setAcl(aclEntryList);
    }
}
