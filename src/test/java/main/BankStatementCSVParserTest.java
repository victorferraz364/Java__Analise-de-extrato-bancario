package main;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;

import domain.BankTransaction;
import services.BankStatementCSVParser;
import services.BankStatementParser;

public class BankStatementCSVParserTest {
		
	private BankStatementParser statementParser = new BankStatementCSVParser();
	
	@Test
	public void shouldParserOneCorrectLine() throws Exception {

		String line = "30-01-2017,-50,Tesco";
		BankTransaction result = statementParser.parserFrom(line);
		BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), 
				-50, "Tesco");
		double tolerance = 0.0d;
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}
	
}
