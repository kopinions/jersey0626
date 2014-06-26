package org.thoughtworks.com;

import org.thoughtworks.com.json.response.PriceJson;
import org.thoughtworks.com.provider.ProductRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class PriceResource {
    private final ProductRepository productRepository;

    public PriceResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GET
    @Path("{id}")
    public PriceJson getPrice(@PathParam("id") int id) {
        productRepository.getPriceById(id);
        return new PriceJson();
    }
}
