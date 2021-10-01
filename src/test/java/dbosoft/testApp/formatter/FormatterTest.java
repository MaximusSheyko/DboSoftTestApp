package dbosoft.testApp.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dbosoft.testApp.Product;

class FormatterTest {

    private Formatter formatter;
    private static final String EXPECTED_FORMAT = "Хлеб ржаной 04.03.2017 4500";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @BeforeEach
    void setUp() throws Exception {
	formatter = new Formatter();
    }

    @Test
    void testGetForm() {
	var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
	var product = new Product.ProductBuild()
		.setDateOfSale(LocalDate.parse("04.03.2017", formatter))
		.setName("Хлеб ржаной")
		.setPrice(4500)
		.build();

	assertEquals(EXPECTED_FORMAT, this.formatter.getForm(product));
    }

    @Test
    void testGetForm_whenInputIsNull() {
	assertThrows(IllegalArgumentException.class,
		() -> formatter.getForm(null));
    }

}
