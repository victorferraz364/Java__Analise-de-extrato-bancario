package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import domain.BankTransaction;
import services.BankStatementCSVParser;

public class Program {

	private static final String RESOURCES = "src\\resources\\";

	public static void main(String... args) throws IOException {
		
		BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();
		
		String fileName = "extrato.csv";
		
		Path path = Paths.get(RESOURCES, fileName);
		List<String> lines = Files.readAllLines(path);
		
		List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);

		System.out.println("total of all transaction: " + calculateTotalAmount(bankTransactions));
		System.out.println("total of all transaction: " + calculateTotalAmount(selectMonth(bankTransactions, Month.JANUARY)));
				
	}
	
	public static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
		double total = 0d;
		for (BankTransaction bankTransaction: bankTransactions) {
			total += bankTransaction.getAmount();
		}
		return total;
	}
	
	public static List<BankTransaction> selectMonth(List<BankTransaction> bankTransactions, Month month) {
		
		List<BankTransaction> bankTransactionMonth = new ArrayList<>();
		for (BankTransaction bankTransaction: bankTransactions) {
			if(bankTransaction.getDate().getMonth() == month) {
				bankTransactionMonth.add(bankTransaction);
			}
		}
		return bankTransactionMonth;
	}
	
}