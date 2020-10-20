package com.cebbys.celib.directories;

import java.io.File;
import java.nio.file.Path;

public class DirectoryHandler {

    public static void initDirectory( Path directory ) {
        directory.toFile().mkdirs();
    }

    public static Path appendToPath( Path path, String appendable ) {
        return ( new File( String.format( "%s\\%s", path, appendable ) ) ).toPath();
    }

}
