package com.alatamli.web.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronTasks {
	
	@Scheduled( fixedDelay = 5000 )
	
	public void reportCurrentTime() {
		// log.info("The time is now {}", dateFormat.format(new Date()));
		
		System.out.println(  "The time is now "+(new SimpleDateFormat("HH:mm:ss")).format(new Date())  );
	}

}
