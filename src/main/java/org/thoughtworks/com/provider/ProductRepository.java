package org.thoughtworks.com.provider;

import org.thoughtworks.com.domain.Price;
import org.thoughtworks.com.domain.Product;

public interface ProductRepository {
    public Product getProductById(int eq);

    public int createProduct(Product capture);

    public Price getPriceById(int i);

    public int createProductPrice(Product product, Price price);
}
