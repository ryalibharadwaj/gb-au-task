/**********************************Customer Account CLASS***********************************************
 * Customer Account is generic class designed to perform credit and debit actions based on 
 * the Amount Value. 
 * @author Bharadwaj Ryali
 *******************************************************************************************************/
package io.com.gb.transactions.account;

import io.com.gb.transactions.model.TransactionDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerAccount {
	private final long acctNum;
	private double balance = 0.0;
	
	public void apply(TransactionDetail transaction){
		double transactionAmount = transaction.getTransactionAmt();
		
		if(transactionAmount < 0) {
			balance += Math.abs(transactionAmount); //Negative transaction amount is considered as debit
		} else {
			balance -= Math.abs(transactionAmount); //Positive transaction amount is considered as credit
		}
	}
}
