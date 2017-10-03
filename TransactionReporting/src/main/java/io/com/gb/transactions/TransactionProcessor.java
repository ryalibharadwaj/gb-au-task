/**********************************Transaction Processor CLASS*********************************************
 * Transaction Processor class is designed to interact with model and service layers to generate report. 
 * Following are the features provided
 * 1. Initialize pending, reporting and archive directories based on environment variable TRANSACTION_PROCESSING
 * 2. Read Pending files from respective directory
 * 3. Process and Generate the Report along with respective naming conventions
 * 4. Archive the processed file
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.com.gb.transactions.account.CustomerAccountService;
import io.com.gb.transactions.addons.FileHandler;
import io.com.gb.transactions.model.TransactionReport;
import io.com.gb.transactions.parser.TransactionFileParser;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Configuration
@RequiredArgsConstructor
public class TransactionProcessor {
	private static final Logger log = LoggerFactory.getLogger(TransactionProcessor.class);
	
	private final CustomerAccountService custAcctService;
	
	@Value("${transinfo.reportsDir}")
	private String reportsDir;
	
	@Value("${transinfo.pendingDir}")
	private String pendingDir;
	
	@Value("${transinfo.archiveDir}")
	private String archiveDir;
	
	public void execute() {
		List<TransactionReport> transactionFiles = readPendingFiles();
		for ( TransactionReport file : transactionFiles) {
			custAcctService.applyTransactions(file);
			generateReport(file);
			archiveFile(file);
		}
	}
	
	private List<TransactionReport> readPendingFiles() {
		log.info("Loading Pending Transactions");
		List<TransactionReport> transactionFiles = new ArrayList<>();
		
		for ( Path path : FileHandler.listFiles(Paths.get(pendingDir))) {
			transactionFiles.add(TransactionFileParser.fromPath(path));
		}
		
		return transactionFiles;
	}
	
	private void generateReport(TransactionReport rep) {
		String fileName = rep.getPath().getFileName().toString();
		String datetime = fileName.replace("finance_customer_transactions-", "").replace(".csv", "");
		Path path = Paths.get(reportsDir, "finance_customer_transactions_report-" + datetime + ".txt");
		
		log.info("Writing report to {}", path);
		FileHandler.writeFile(path, rep.generateTransactionReport());
	}
	
	private void archiveFile(TransactionReport rep) {
		log.info("Archiving transaction file {}", rep.getPath().toString());
		FileHandler.moveFile(rep.getPath(), Paths.get(archiveDir,rep.getPath().getFileName().toString()));
	}
	
}
