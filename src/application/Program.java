package application;

import java.io.IOException;

import domain.BankStatementAnalyzer;
import services.BankStatementCSVParser;
import services.BankStatementParser;

public class Program {

	public static void main(String... args) throws IOException {

		BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
		
		BankStatementParser bankStatementParser = new BankStatementCSVParser();
		
		bankStatementAnalyzer.analyzer("extrato.csv", bankStatementParser);

	}
}