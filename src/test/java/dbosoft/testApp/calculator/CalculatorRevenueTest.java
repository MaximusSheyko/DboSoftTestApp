package dbosoft.testApp.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dbosoft.testApp.CatalogGoods;
import dbosoft.testApp.Product;

@RunWith(MockitoJUnitRunner.class)
class CalculatorRevenueTest {

    @Mock
    private CatalogGoods catalog;
    @InjectMocks
    private CalculatorRevenue calculatorRevenue;
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @BeforeEach
    void setUp() throws Exception {
	catalog = mock(CatalogGoods.class);
	calculatorRevenue = new CalculatorRevenue(catalog);
    }

    @Test
    void testGetProductWithMaxRevenue_whenInputVarIsNull() {
	assertThrows(IllegalArgumentException.class,
		() -> calculatorRevenue.getProductWithMaxRevenue(null));
    }

    @Test
    void testGetProductWithMaxRevenue_whenInputIsValid() {
	var formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
	var goods = Arrays.asList(new Product.ProductBuild()
		.setName("Milk")
		.setPrice(2500)
		.setDateOfSale(LocalDate.parse("03.03.2017", formatter))
		.build(),
		new Product.ProductBuild()
			.setName("Orange")
			.setPrice(5000)
			.setDateOfSale(LocalDate.parse("04.03.2016", formatter))
			.build());
	Map<String, List<Product>> goodsGroups = goods.stream()
		.collect(Collectors.toMap(Product::getName, v -> Arrays.asList(v)));
	var productNames = new HashSet<String>();
	var result = goods.get(1);

	goods.forEach(product -> productNames.add(product.getName()));

	when(catalog.getGroupedProduct(goods)).thenReturn(goodsGroups);
	when(catalog.getProductNames(goods)).thenReturn(productNames);
	assertEquals(result, calculatorRevenue.getProductWithMaxRevenue(goods));

    }
}
