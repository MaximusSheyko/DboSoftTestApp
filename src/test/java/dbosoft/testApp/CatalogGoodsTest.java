package dbosoft.testApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatalogGoodsTest {

    private List<Product> goods = Arrays.asList(new Product.ProductBuild()
	    .setName("Milk")
	    .setPrice(2500)
	    .build(),
	    new Product.ProductBuild()
		    .setName("Orange")
		    .setPrice(5000)
		    .build());
    private CatalogGoods catalog;

    @BeforeEach
    void setUp() throws Exception {
	catalog = new CatalogGoods();
    }

    @Test
    void testGetGroupedProduct() {
	Map<String, List<Product>> goodsGroups = goods.stream()
		.collect(Collectors.toMap(Product::getName, v -> Arrays.asList(v)));

	assertEquals(goodsGroups, catalog.getGroupedProduct(goods));
    }

    @Test
    void testGetGroupedProduct_whenInputIsNull() {
	assertThrows(IllegalArgumentException.class,
		() -> catalog.getGroupedProduct(null));
    }

    @Test
    void testGetProductNames() {
	var productNames = new HashSet<String>();
	goods.forEach(product -> productNames.add(product.getName()));

	assertEquals(productNames, catalog.getProductNames(goods));
    }

}
