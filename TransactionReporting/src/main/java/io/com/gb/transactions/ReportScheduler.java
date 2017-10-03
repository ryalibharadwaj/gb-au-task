/**********************************Report Scheduler CLASS***********************************************
 * Report Scheduler class is designed to run the application twice a day and generate respective reports
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import lombok.RequiredArgsConstructor;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class ReportScheduler {
	private static final Logger log	= LoggerFactory.getLogger(ReportScheduler.class);
	private final TransactionProcessor transactionProcessor;
	
	@Schedules({
		@Scheduled(cron = "0 4 6 * * ?"),
		@Scheduled(cron = "0 4 21 * * ?")
	})
	//@Scheduled(cron = "0 33 16 * * ?")
	public void startTransactionProcessing() {
		log.info("Schduled transaction Started at {}", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX").withZone(ZoneOffset.UTC).format(Instant.now()));
		
		try {
			transactionProcessor.execute();
		}
		catch (RuntimeException ex) {
			log.error("Error occured during transaction processing: {}: {}", ex.getMessage(), ex.getCause());
		}
	}
}
