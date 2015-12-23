package org.miabis.server.batch;

import org.springframework.batch.item.ItemProcessor;

public class Pros implements ItemProcessor<String, String> {

	@Override
	public String process(String arg0) throws Exception {
		
		System.out.println(arg0);
		
		return arg0;
	}

}
