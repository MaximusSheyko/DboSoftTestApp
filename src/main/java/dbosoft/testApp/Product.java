package dbosoft.testApp;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private String name;
    private LocalDate dateOfSale;
    private Integer price;

    public Product(String name, LocalDate dateOfSale, Integer price) {
	this.name = name;
	this.dateOfSale = dateOfSale;
	this.price = price;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public LocalDate getDateOfSale() {
	return dateOfSale;
    }

    public void setDateOfSale(LocalDate dateOfSale) {
	this.dateOfSale = dateOfSale;
    }

    public Integer getPrice() {
	return price;
    }

    public void setPrice(Integer price) {
	this.price = price;
    }

    public static class ProductBuild {
	private String name;
	private LocalDate dateOfSale;
	private Integer price;

	public ProductBuild setName(String name) {
	    this.name = name;

	    return this;
	}

	public ProductBuild setDateOfSale(LocalDate dateOfSale) {
	    this.dateOfSale = dateOfSale;

	    return this;
	}

	public ProductBuild setPrice(int price) {
	    this.price = price;

	    return this;
	}

	public Product build() {
	    return new Product(name, dateOfSale, price);
	}

    }

    @Override
    public int hashCode() {
	return Objects.hash(dateOfSale, name, price);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Product other = (Product) obj;
	return Objects.equals(dateOfSale, other.dateOfSale) && Objects.equals(name, other.name)
		&& Objects.equals(price, other.price);
    }

    @Override
    public String toString() {
	return "Product [name=" + name + ", dateOfSale=" + dateOfSale + ", price=" + price + "]";
    }

}
