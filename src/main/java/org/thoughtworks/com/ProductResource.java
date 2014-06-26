package org.thoughtworks.com;

import org.thoughtworks.com.provider.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


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

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createProduct() {
        return Response.status(201).build();
    }
}
