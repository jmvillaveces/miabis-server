package org.miabis.server;

import javax.sql.DataSource;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.miabis.server.batch.IndexFilesJob;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes={IndexFilesJob.class})
public class CommandLineCli implements CommandLineRunner {

	@Autowired DataSource dataSource;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
		
		//ImmutableSettings.Builder settings=NodeBuilder.nodeBuilder().settings();
		//settings.put("path.conf","conf");
		
		//Node node = NodeBuilder.nodeBuilder().settings(settings).node();
		//Client client = node.client();
		
		
		/*File f = new File("elastic-search.yml");
		System.out.println(f.exists());
		
		 SettingsLoader settingsLoader = SettingsLoaderFactory.loaderFromResource("elastic-search.yml");
		 Map<String, String> loadedSettings = settingsLoader.load("elastic-search.yml");*/
         //put(loadedSettings);
		
		//Settings settings = ImmutableSettings.settingsBuilder().loadFromStream(arg0, arg1)
		//System.out.println(settings.names().iterator().next());
		
		 //Settings set = .build();
				
		//Node node = NodeBuilder.nodeBuilder().settings(NodeBuilder.nodeBuilder().settings().put(loadedSettings)).node();
		//Client client = node.client();
		
		//Node node = NodeBuilder.nodeBuilder().clusterName("yourclustername").node();
		//Client client = node.client();
	}

}
