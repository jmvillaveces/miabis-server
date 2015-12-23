package org.miabis.server.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
public class IndexFilesJob {
	
	private static final String OVERRIDDEN_BY_EXPRESSION = null;
	
	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;
	
	@Bean
	public Job flatfileToDbSkipJob() throws Exception{
		return jobs.get("flatfileToDbSkipJob")
				.start(step1())
				.build();
	}
	
	@Bean
	protected Step step1() throws Exception {
		return this.steps.get("step1").<String,String>chunk(100)
				.reader(reader(OVERRIDDEN_BY_EXPRESSION))
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	@StepScope
    public ItemProcessor<String, String> processor() {
        return new Pros();
    }
	
	@Bean
	@StepScope
	public ItemReader<String> reader(@Value("#{jobParameters[pathToFile]}") String pathToFile){
		
		System.out.println(pathToFile);
		
		FlatFileItemReader<String> itemReader = new FlatFileItemReader<String>();
		itemReader.setLineMapper(new PassThroughLineMapper());
		itemReader.setResource(new FileSystemResource(pathToFile));
		itemReader.setStrict(true);
		return itemReader;
	}
	
	@Bean
	@StepScope
	public ItemWriter<String> writer(){
		FlatFileItemWriter<String> itemWriter = new FlatFileItemWriter<String>();
		itemWriter.setResource(new FileSystemResource("/Users/jvillaveces/Miabis/miabis-server/derby-two.log"));
		itemWriter.setShouldDeleteIfExists(true);
		itemWriter.setLineAggregator(new PassThroughLineAggregator<String>());
		return itemWriter;
	}

	public static void main(String[] args) throws Exception {
		// System.exit is common for Batch applications since the exit code can be used to
		// drive a workflow
		//System.exit(SpringApplication.exit(SpringApplication.run(IndexFilesJob.class, args)));
		
		
	}
	
}
