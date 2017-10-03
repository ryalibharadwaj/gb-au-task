/**********************************SERVICE CLASS********************************************************
 * CustomerAccountService is service class designed to support following features
 * 1. Maintenance of Customer Accounts in Map.
 * 2. Handling Single and Multiple Transactions w.r.t accounts.
 * 3. Get the Balance for a specific account.
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.com.gb.transactions.model.TransactionDetail;
import io.com.gb.transactions.model.TransactionReport;
import lombok.Getter;

@Service
public class CustomerAccountService {
	@Getter
	private Map<Long, CustomerAccount> accounts = new HashMap<>();
	
	public void applyTransactions(TransactionReport report) {
		for (TransactionDetail transactionDetail : report.getTransactions()) {
			applyTransaction(transactionDetail);
		}
	}
	
	public void applyTransaction(TransactionDetail transactionDetail) {
		CustomerAccount account = accounts.get(transactionDetail.getCustomerAcctNum());
		
		if(account == null) {
			account = new CustomerAccount(transactionDetail.getCustomerAcctNum());
		}
		
		account.apply(transactionDetail);
		accounts.put(account.getAcctNum(), account);
	}
	
	public double getAccountBalance(Long custAcctNum) {
		CustomerAccount account = accounts.get(custAcctNum);
		
		if(account == null) {
			throw new RuntimeException("Customer Account " + custAcctNum + " does not exist.");
		}
		
		return account.getBalance();
	}
}
