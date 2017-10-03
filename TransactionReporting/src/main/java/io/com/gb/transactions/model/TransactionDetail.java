/*******************CLASS belongs to MODEL Layer**************************
 * Simple POJO to store Transaction details
 * @author Bharadwaj Ryali
 **************************************************************************/
package io.com.gb.transactions.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TransactionDetail {
	private final long customerAcctNum;
	private final double transactionAmt;
}
