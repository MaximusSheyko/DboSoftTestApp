package dbosoft.testApp;

import dbosoft.testApp.calculator.CalculatorRevenue;
import dbosoft.testApp.formatter.Formatter;
import dbosoft.testApp.parser.Parser;
import dbosoft.testApp.reader.Reader;

public class Report {
    Formatter formatter;
    CalculatorRevenue logic;
    Parser parser;

    public Report(Reader reader, Formatter formatter, CalculatorRevenue logic, Parser parser) {
	this.formatter = formatter;
	this.logic = logic;
	this.parser = parser;
    }

    public String getReport(String fileName) {
	if (null == fileName) {
	    throw new IllegalArgumentException("file name can not be null");
	}

	var targetProduct = logic.getProductWithMaxRevenue(parser.parse(fileName));

	return formatter.getForm(targetProduct);
    }
}
