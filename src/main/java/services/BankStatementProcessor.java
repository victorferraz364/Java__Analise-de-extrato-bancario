package services;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import domain.BankTransaction;
import domain.BankTransactionFilter;
import domain.BankTransactionSummarizer;

public class BankStatementProcessor {

	private List<BankTransaction> bankTransactions;

	public BankStatementProcessor(List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public double summarizeTransactions(BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		for (BankTransaction bankTransaction: bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);

		}
		return result;
	}	

	public double calculateTotalInMonth(Month month) {

		return summarizeTransactions((acc, bankTransaction) -> 
		bankTransaction.getDate().getMonth() 
		== month ? acc + bankTransaction.getAmount() : acc);

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
