package io.com.gb.transactions.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.ClassPathResource;

import io.com.gb.transactions.model.TransactionReport;

@RunWith(JUnit4.class)
public class TransactionFileParserTest {
	  @Test
	  public void rejectedLinesAreSkipped() throws IOException {
	    Path path = new ClassPathResource("pending/finance_customer_transactions-rejections.csv").getFile().toPath();
	    TransactionReport rep = TransactionFileParser.fromPath(path);
	
	    assertEquals(3, rep.getTotalAccounts());
	    assertEquals(10, rep.getRejectedTransactions());
	  }
	
	  @Test
	  public void blankFileHandling() throws IOException {
	    Path path = new ClassPathResource("pending/finance_customer_transactions-blankfile.csv").getFile().toPath();
	    TransactionReport rep = TransactionFileParser.fromPath(path);
	
	    assertEquals(0, rep.getTotalAccounts());
	    assertEquals(0, rep.getRejectedTransactions());
	  }
	
	  @Test
	  public void headerOnlyFileHandling() throws IOException {
	    Path path = new ClassPathResource("pending/finance_customer_transactions-headeronly.csv").getFile().toPath();
	    TransactionReport rep = TransactionFileParser.fromPath(path);
	
	    assertEquals(0, rep.getTotalAccounts());
	    assertEquals(0, rep.getRejectedTransactions());
	  }
	
	  @Test
	  public void parseFullFile() throws IOException {
	    Path path = new ClassPathResource("pending/finance_customer_transactions-fullfile.csv").getFile().toPath();
	    TransactionReport rep = TransactionFileParser.fromPath(path);
	
	    assertEquals(12000, rep.getTotalAccounts());
	    assertEquals(0, rep.getRejectedTransactions());
	  }
}
