package dbosoft.testApp.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dbosoft.testApp.Product;
import dbosoft.testApp.reader.Reader;

public class Parser implements dbosoft.testApp.intarfaces.Parser<Product> {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String SHARP = "#";
    private Reader reader;

    public Parser(Reader reader) {
	this.reader = reader;
    }

    public List<Product> parse(String nameFile) {
	var goods = new ArrayList<Product>();
	var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
	String name;
	LocalDate dataOfSales;
	String dateForParsing;
	Integer price;

	for (var product : reader.read(nameFile)) {
	    name = product.substring(0, product.indexOf(SHARP));
	    dateForParsing = product.substring(product.indexOf(SHARP) + 1,
		    product.lastIndexOf(SHARP));
	    dataOfSales = LocalDate.parse(dateForParsing, formatter);
	    price = Integer.parseInt(product.substring(product.lastIndexOf(SHARP) + 1)
		    .trim());

	    goods.add(new Product.ProductBuild()
		    .setName(name)
		    .setDateOfSale(dataOfSales)
		    .setPrice(price)
		    .build());
	}

	return goods;
    }
}
