package org.thoughtworks.com;

import org.thoughtworks.com.domain.Product;
import org.thoughtworks.com.provider.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


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
    public Response createProduct(Form form, @Context UriInfo uriInfo) {
        MultivaluedMap<String, String> createProductRequest = form.asMap();
        Product product = new Product(createProductRequest.getFirst("name"));
        int productId = productRepository.createProduct(product);
        return Response.status(201).location(uriInfo.getAbsolutePathBuilder().path(String.valueOf(productId)).build()).build();
    }


    @Path("{id}/prices")
    public PriceResource getPrice() {
        return new PriceResource(productRepository);
    }
}
