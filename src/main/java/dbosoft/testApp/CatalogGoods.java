package dbosoft.testApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CatalogGoods {

    public Map<String, List<Product>> getGroupedProduct(List<Product> goods) {
	if (null == goods || goods.isEmpty()) {
	    throw new IllegalArgumentException("goods is null or empty");
	}

	var goodsGroups = new HashMap<String, List<Product>>();
	var productNames = getProductNames(goods);

	for (var name : productNames) {
	    var productsGroup = new ArrayList<Product>();

	    goods.stream()
		    .sorted(Comparator.comparing(Product::getName))
		    .filter(product -> product.getName().equals(name))
		    .forEach(product -> productsGroup.add(product));
	    goodsGroups.put(name, productsGroup);
	}

	return goodsGroups;
    }

    public HashSet<String> getProductNames(List<Product> goods) {
	var productNames = new HashSet<String>();
	goods.forEach(product -> productNames.add(product.getName()));

	return productNames;
    }
}
