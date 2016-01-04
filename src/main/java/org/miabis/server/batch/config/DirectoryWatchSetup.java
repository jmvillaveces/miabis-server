package org.miabis.server.batch.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.miabis.server.directorywatch.DirectoryWatchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class DirectoryWatchSetup {
	
	
	
	/*@Bean(name = "directoryWatch")
    public DirectoryWatch getDirectoryWatch() throws IOException{
        
		File dir = new File("new folder");
		dir.mkdir();
		
		DirectoryWatch directoryWatch = new DirectoryWatch(Paths.get("new folder"));
		directoryWatch.watch();
		
        return directoryWatch;
    }*/
}
