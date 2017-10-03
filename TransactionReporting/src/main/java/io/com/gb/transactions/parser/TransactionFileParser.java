/**********************************Transaction File Parser CLASS****************************************
 * This class is designed with following features
 * 1. Parsing the file at the mentioned location
 * 2. Processing the lines of file
 * 3. Handling Skipped Records
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions.parser;

import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.com.gb.transactions.addons.FileHandler;
import io.com.gb.transactions.model.TransactionDetail;
import io.com.gb.transactions.model.TransactionReport;

public class TransactionFileParser {
	private static final Logger log = LoggerFactory.getLogger(TransactionFileParser.class);
	
	public static TransactionReport fromPath(Path path){
		TransactionReport transactionReport = new TransactionReport(path);
		
		double start = System.currentTimeMillis();
		List<String> lines = FileHandler.parseFile(path);
		if(lines.size() > 0 ) {
			lines.remove(0);
		}
		for(String line : lines){
			transactionReport.addTransaction(processLine(line));
		}
		log.info("{} transaction process in {} secs", lines.size(), (System.currentTimeMillis()-start)/3);
		return transactionReport;
	}
	
	private static TransactionDetail processLine(String line){
		String[] splitAcctAmt = line.replace(" ", "").split(",");
		
		if (splitAcctAmt.length != 2) {
			return null;
		}
		
		String custAcctNum = splitAcctAmt[0];
		if(!StringUtils.isNumeric(custAcctNum)){
			return null;
		}
		
		String transAmt = splitAcctAmt[1];
		if(!NumberUtils.isCreatable(transAmt)){
			return null;
		}
		
		return new TransactionDetail(Integer.parseInt(custAcctNum), Double.parseDouble(transAmt));
	}
}
