package org.thoughtworks.com.json;

import org.thoughtworks.com.domain.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class ProductRefJson {
    private final Product product;
    private final UriInfo uriInfo;

    public ProductRefJson(Product product, UriInfo uriInfo) {

        this.product = product;
        this.uriInfo = uriInfo;
    }

    public String getName() {
        return product.getName();
    }

    public URI getUri() {
        return uriInfo.getAbsolutePathBuilder().path(String.valueOf(product.getId())).build();
    }

}
