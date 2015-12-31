package org.miabis.server.batch.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.miabis.server.directorywatch.DirectoryWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectoryWatchSetup {
	
	@Bean(name = "directoryWatch")
    public DirectoryWatch getDirectoryWatch() throws IOException{
        
		File dir = new File("new folder");
		dir.mkdir();
		
		DirectoryWatch directoryWatch = new DirectoryWatch(Paths.get("new folder"));
		directoryWatch.watch();
		
        return directoryWatch;
    }

}
