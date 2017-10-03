package io.com.gb.transactions.account;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.com.gb.transactions.model.TransactionDetail;

@RunWith(JUnit4.class)
public class CustomerAccountServiceTest {

	private CustomerAccountService accountService = new CustomerAccountService();

	  
	@Test
	  public void debitToIncreaseBalance() {
	    accountService.applyTransaction(new TransactionDetail(1001, -50.0));
	    assertEquals(50.0, accountService.getAccountBalance(Long.valueOf(1001)), 0);
	  }

	  @Test
	  public void creditToDecreasesBalance() {
	    accountService.applyTransaction(new TransactionDetail(1002, 200.0));
	    assertEquals(-200.0, accountService.getAccountBalance(Long.valueOf(1002)), 0);
	  }

	  @Test(expected = RuntimeException.class)
	  public void invalidAccountThrowsException() {
	    accountService.getAccountBalance(Long.valueOf(-1));
	  }

}
