package dbosoft.testApp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbosoft.testApp.Product;
import dbosoft.testApp.reader.Reader;

class ParserTest {

    private static final String List = null;
    private Parser parser;
    private Reader reader;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @BeforeEach
    void setUp() throws Exception {
	reader = new Reader();
	parser = new Parser(reader);
    }

    @Test
    void testParser() {
	var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
	var goods = Arrays.asList(new Product.ProductBuild()
		.setDateOfSale(LocalDate.parse("03.03.2017", formatter))
		.setName("Яблоко \"Голден\"")
		.setPrice(2300)
		.build(),
		new Product.ProductBuild()
			.setDateOfSale(LocalDate.parse("04.03.2017", formatter))
			.setName("Хлеб ржаной")
			.setPrice(1800)
			.build(),
		new Product.ProductBuild()
			.setDateOfSale(LocalDate.parse("02.03.2017", formatter))
			.setName("Утюг \"Scarlett\"")
			.setPrice(2200)
			.build());

	assertEquals(goods, parser.parse("input.txt"));
    }
}
