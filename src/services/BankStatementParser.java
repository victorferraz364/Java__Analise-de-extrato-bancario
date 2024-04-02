package services;

import java.util.List;

import domain.BankTransaction;

public interface BankStatementParser {

	BankTransaction parserFrom(String line);
	
	List<BankTransaction> parseLinesFrom(List<String> lines);
	
}
