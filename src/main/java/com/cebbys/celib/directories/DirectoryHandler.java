package com.cebbys.celib.directories;

import java.io.File;
import java.nio.file.Path;

public class DirectoryHandler {

    public static void initDirectories( Path[] directories ) {
        for( Path p : directories ) {
            initDirectory( p );
        }
    }
    public static void initDirectories( File[] directories ) {
        for( File f : directories ) {
            initDirectory( f );
        }
    }

    public static void initDirectory( File directory ) {
        if( !directory.exists() ) directory.mkdirs();
    }
    public static void initDirectory( Path directory ) { if( !directory.toFile().exists() ) directory.toFile().mkdirs(); }

    public static Path appendToPath( File path, String appendable ) {
        return appendToPath( path.toPath(), appendable );
    }
    public static Path appendToPath( Path path, String appendable ) {
        return ( new File( String.format( "%s\\%s", path, appendable ) ) ).toPath();
    }

}
