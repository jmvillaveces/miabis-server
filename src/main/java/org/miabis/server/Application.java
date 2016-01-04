package org.miabis.server;

import java.io.File;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.miabis.server.batch.IndexFilesJob;
import org.miabis.server.directorywatch.DirectoryWatchService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	DirectoryWatchService dws;
	
	@Override
	public void run(String... arg0) throws Exception {
		File dir = new File("new folder");
		dir.mkdir();
		
		dws.setPath(Paths.get("new folder"));
		dws.watch();
	}
	
	
	
    public static void main(String[] args) throws BeansException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        //SpringApplication.run(Application.class, args);
    	
    	
    	ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    	
    	
		
	    
	    /*JobLauncher jobLauncher = (JobLauncher) ctx.getBean(JobLauncher.class);
	    
	    JobParametersBuilder pb = new JobParametersBuilder();
		//pb.addString("pathToFile", "/Users/jvillaveces/Miabis/miabis-server/derby.log");
	    pb.addString("pathToFile", "partner-import.csv");
	    
	    //try catch removed for readability
	    jobLauncher.run(ctx.getBean(Job.class), pb.toJobParameters());*/
    }

	
}
