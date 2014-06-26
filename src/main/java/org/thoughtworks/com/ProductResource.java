package org.thoughtworks.com;

import org.thoughtworks.com.provider.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/products")
public class ProductResource {

    @Inject
    ProductRepository productRepository;

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getProduct(@PathParam("id") int id) {
        productRepository.getProductById(id);
        return "s";
    }
}
