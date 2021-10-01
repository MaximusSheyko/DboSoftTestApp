package dbosoft.testApp;

import dbosoft.testApp.calculator.CalculatorRevenue;
import dbosoft.testApp.formatter.Formatter;
import dbosoft.testApp.parser.Parser;
import dbosoft.testApp.reader.Reader;

public class Demo {
    public static void main(String[] args) {
	Report report = new Report(new Reader(), new Formatter(), new CalculatorRevenue(new CatalogGoods()),
		new Parser(new Reader()));

	System.out.println(report.getReport("input.txt"));
    }
}
