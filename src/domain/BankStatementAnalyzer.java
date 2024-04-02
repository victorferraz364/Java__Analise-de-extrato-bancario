package domain;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import services.BankStatementCSVParser;
import services.BankStatementParser;
import services.BankStatementProcessor;

public class BankStatementAnalyzer {
	private static final String RESOURCES = "src\\resources\\";

	public static void analyzer(String fileName, BankStatementParser bankStatementParser) throws IOException {


		Path path = Paths.get(RESOURCES, fileName);
		List<String> lines = Files.readAllLines(path);

		List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

		BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

		collectSumary(bankStatementProcessor);
	}

	private static void collectSumary(BankStatementProcessor bankStatementProcessor) {
		System.out.println("The total for all transactions is " 
				+ bankStatementProcessor.calculateTotalAmount());

		System.out.println("The total for transactions in January is " 
				+ bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));

		System.out.println("The total for transactions in February is "
				+ bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

		System.out.println("The total salary received is "
				+ bankStatementProcessor.calculateTotalForCategory("Salary"));
	}

}

