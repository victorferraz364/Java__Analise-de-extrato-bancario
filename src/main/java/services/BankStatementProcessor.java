package services;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import domain.BankTransaction;

public class BankStatementProcessor {

	private List<BankTransaction> bankTransactions;
	
	public BankStatementProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}
	
	public double calculateTotalAmount() {
		
			double total = 0d;
			for (BankTransaction bankTransaction: bankTransactions) {
				total += bankTransaction.getAmount();
			}
			return total;
		}
	
	public List<BankTransaction> calculateTotalInMonth(Month month) {
		
		List<BankTransaction> bankTransactionMonth = new ArrayList<>();
		
		for (BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				bankTransactionMonth.add(bankTransaction);
			}
		}
		return bankTransactionMonth;
	}
	
	public double calculateTotalForCategory(final String category) {
		
		double total = 0;
		
		for(final BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDescription().equals(category)) { 
				total += bankTransaction.getAmount();
			}
		}
		return total;
	}

	public List<BankTransaction> findTransactionsGreaterThanEqual( int amount) {
	        return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
	    }
	
	public List<BankTransaction> findTransactions(BankTransactionFilter bankTransactionFilter) {
		List<BankTransaction> result = new ArrayList<>();
		for(BankTransaction bankTransaction: bankTransactions) {
			if(bankTransactionFilter.test(bankTransaction)) { 
				result.add(bankTransaction);
			}
		}
		return result;
	}
}
