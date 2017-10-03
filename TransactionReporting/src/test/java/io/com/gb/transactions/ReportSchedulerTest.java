package io.com.gb.transactions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = Main.class,
    properties = {
        "processing.baseDir=/",
        "processing.pendingDir=/test"
    }
)
public class ReportSchedulerTest {

	@Autowired
	private ReportScheduler scheduler;

	@Test
	public void schedulerRuns() {
	  scheduler.startTransactionProcessing();
	}

}
