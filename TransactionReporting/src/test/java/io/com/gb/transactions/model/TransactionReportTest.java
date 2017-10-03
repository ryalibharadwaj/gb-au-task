package io.com.gb.transactions.model;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class TransactionReportTest {

	private TransactionReport rep;

	  @Before
	  public void before() {
	    rep = new TransactionReport(Paths.get("test.csv"));
	  }

	  @Test
	  public void nullTransactionIsSkipped() {
	    rep.addTransaction(null);
	    assertEquals(1, rep.getRejectedTransactions());
	    assertEquals(0, rep.getTotalAccounts());
	  }

	  @Test
	  public void multipleCustomerTransactions() {
		rep.addTransaction(new TransactionDetail(123456789, 50.0));
		rep.addTransaction(new TransactionDetail(123456789, 100.0));
		rep.addTransaction(new TransactionDetail(123456789, -50.0));

	    assertEquals(1, rep.getTotalAccounts());
	    assertEquals(150.00, rep.getTotalCredits(), 0);
	    assertEquals(-50.00,rep.getTotalDebits(), 0);
	  }

	  @Test
	  public void totalCredits() {
		rep.addTransaction(new TransactionDetail(123456789, 50.0));
		rep.addTransaction(new TransactionDetail(987654321, 25.0));
		rep.addTransaction(new TransactionDetail(999999999, 50.0));

	    assertEquals(3, rep.getTotalAccounts());
	    assertEquals(125.00, rep.getTotalCredits(), 0);
	    assertEquals(0, rep.getTotalDebits(), 0);
	  }

	  @Test
	  public void totalDebits() {
		rep.addTransaction(new TransactionDetail(54321, -200.0));
		rep.addTransaction(new TransactionDetail(87542, -150.0));

	    assertEquals(2, rep.getTotalAccounts());
	    assertEquals(0, rep.getTotalCredits(), 0);
	    assertEquals(-350.00, rep.getTotalDebits(), 0);
	  }

}
