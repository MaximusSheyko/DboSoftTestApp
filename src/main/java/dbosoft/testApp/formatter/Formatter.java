package dbosoft.testApp.formatter;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import dbosoft.testApp.Product;

public class Formatter implements dbosoft.testApp.intarfaces.Formatter<String, Product> {

    private static final String SPACE = " ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public String getForm(Product product) {
	if (null == product) {
	    throw new IllegalArgumentException("Product is null");
	}

	var form = new StringBuilder();
	var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);

	form.append(product.getName())
		.append(SPACE)
		.append(formatter.format(product.getDateOfSale()))
		.append(SPACE)
		.append(product.getPrice());

	return form.toString();
    }
}
