package org.miabis.server.directorywatch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public class DirectoryWatch {
	
	private static final Logger logger = LoggerFactory.getLogger(DirectoryWatch.class);
	private WatchService watcher;
	private Path path;
	
	/**
	 * Creates a WatchService
	 * @throws IOException
	 */
	public DirectoryWatch() throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
	}
	
	/**
	 * Creates a WatchService and registers the given directory
	 * @throws IOException
	 */
	public DirectoryWatch(Path path) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		setPath(path);
	}
	
	/**
	 * Register the given directory with the WatchService  
	 */
	public void setPath(Path path){ 
		this.path = path;
	}

	/**
	 * Start watching for path
	 * @throws IOException 
	 */
	@Async
	public void watch() throws IOException {
		
		logger.info("Monitoring folder " + path);
		
		path.register(watcher,
				StandardWatchEventKinds.ENTRY_CREATE,
				//StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		
		for (;;) {
			
			// wait for key to be signaled
		    WatchKey key;
		    try {
		    	key = watcher.take();
		    } catch (InterruptedException e) {
		        return;
		    }
		    
		    for (WatchEvent<?> event: key.pollEvents()) {
		    	
		    	WatchEvent.Kind<?> kind = event.kind();
		    	
		    	// The filename is the context of the event.
		        WatchEvent<Path> ev = (WatchEvent<Path>)event;
		        Path filename = ev.context();
		    	
		    	// OVERFLOW event
		        if (kind == StandardWatchEventKinds.OVERFLOW) {
		        	logger.warn("OVERFLOW event");
		            continue;
		        }else if (kind == StandardWatchEventKinds.ENTRY_CREATE){
		        	logger.info("Created file "+filename);
		        }else if (kind == StandardWatchEventKinds.ENTRY_MODIFY){
		        	logger.info("Modified file "+filename);
		        }
		        
		        boolean valid = key.reset();
		        if (!valid) {
		            break;
		        }
		    }
		}
	}
}
