package dbosoft.testApp.calculator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import dbosoft.testApp.CatalogGoods;
import dbosoft.testApp.Product;

public class CalculatorRevenue {
    CatalogGoods catalogGoods;

    public CalculatorRevenue(CatalogGoods catalogGoods) {
	this.catalogGoods = catalogGoods;
    }

    public Product getProductWithMaxRevenue(List<Product> goods) {
	if (null == goods || goods.isEmpty()) {
	    throw new IllegalArgumentException("goods is null ");
	}

	var productGroups = catalogGoods.getGroupedProduct(goods);
	var groupWithMaxRevenue = getGroupWithMaxRevenue(productGroups, goods);
	var totalRevenue = 0;
	var nameProduct = groupWithMaxRevenue.get(0).getName();

	for (var product : groupWithMaxRevenue) {
	    totalRevenue += product.getPrice();
	}

	return new Product(nameProduct, getActualDateRevenue(groupWithMaxRevenue), totalRevenue);
    }

    private LocalDate getActualDateRevenue(List<Product> goods) {
	return goods.stream()
		.sorted(Comparator.comparing(Product::getDateOfSale))
		.toList().get(goods.size() - 1)
		.getDateOfSale();
    }

    private List<Product> getGroupWithMaxRevenue(Map<String, List<Product>> goodsGroups,
	    List<Product> goods) {
	var productNames = catalogGoods.getProductNames(goods);
	var revenue = 0;
	var maxRevenue = 0;
	String targetName = null;

	for (var name : productNames) {
	    revenue = countTotalRevenue(goodsGroups.get(name));

	    if (revenue > maxRevenue) {
		targetName = name;
		maxRevenue = revenue;
	    }
	}

	return goodsGroups.get(targetName);
    }

    private int countTotalRevenue(List<Product> goodsGroup) {
	var revenue = new AtomicInteger();
	goodsGroup.forEach(product -> revenue.getAndAdd(product.getPrice()));

	return revenue.get();
    }
}
