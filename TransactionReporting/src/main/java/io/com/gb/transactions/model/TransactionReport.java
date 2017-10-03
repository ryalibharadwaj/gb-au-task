/**********************************CLASS belongs to MODEL Layer*****************************************
 * TransactionReport class  is designed to perform all actions that could be 
 * performed on a daily transaction file. Following are the features
 * 1. addTransactions => This feature identifies a transaction is valid or invalid and acts accordingly
 * 2. getTotalAccounts => This feature would help in getting total accounts in transaction file
 * 3. getTotalCredits => This feature helps in getting total credits
 * 4. getTotalDebits => This feature helps in getting total debits
 * 5. generateTransactionReport => This features help to get the final required report
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransactionReport {
	private final Path path;
	private final List<TransactionDetail> transactions = new ArrayList<>();
	private int rejectedTransactions = 0;

	public void addTransaction(TransactionDetail transactionDetail) {
		if(transactionDetail == null) {
			rejectedTransactions++;
			return;
		}
		transactions.add(transactionDetail);
	}
	
	public long getTotalAccounts(){
		return transactions.stream().map(TransactionDetail::getCustomerAcctNum).distinct().count();
	}
	
	public double getTotalCredits() {
		return transactions.stream().filter(i -> i.getTransactionAmt() > 0).mapToDouble(TransactionDetail::getTransactionAmt).sum();
	}
	
	public double getTotalDebits() {
		return transactions.stream().filter(i -> i.getTransactionAmt() < 0).mapToDouble(TransactionDetail::getTransactionAmt).sum();
	}
	
	public String generateTransactionReport() {
		return "File Processed: " + getPath().getFileName().toString() +
				"\n Total Accounts: " + String.format("%,d", getTotalAccounts())+
				"\n Total Credits: " + String.format("%,.2f", getTotalCredits())+
				"\n Total Debits: " + String.format("%,.2f", getTotalDebits())+
				"\n Skipped Transactions: " + getRejectedTransactions();
	}
}
