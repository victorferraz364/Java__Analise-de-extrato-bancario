package domain;

@FunctionalInterface
public interface BankTransactionFilter {

	boolean test(BankTransaction bankTransaction);
	
}
