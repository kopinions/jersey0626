package org.thoughtworks.com;

import org.thoughtworks.com.domain.Price;
import org.thoughtworks.com.domain.Product;
import org.thoughtworks.com.json.response.PriceJson;
import org.thoughtworks.com.json.response.PriceRefJson;
import org.thoughtworks.com.provider.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PriceResource {
    private final ProductRepository productRepository;
    private Product product;

    public PriceResource(ProductRepository productRepository, Product product) {
        this.productRepository = productRepository;
        this.product = product;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PriceRefJson> getAllPrices(@Context UriInfo uriInfo) {
        List<Price> productPrices = productRepository.getProductPrices(product);
        return productPrices.stream().map(p -> new PriceRefJson(p)).collect(toList());
    }

    @GET
    @Path("{id}")
    public PriceJson getPrice(@PathParam("id") int id) {
        productRepository.getPriceById(id);
        return new PriceJson();
    }


    @POST
    public Response createPrice(Form form, @Context UriInfo uriInfo) {
        MultivaluedMap<String, String> createPriceRequest = form.asMap();
        double price = Double.valueOf(createPriceRequest.getFirst("price"));
        int priceId = productRepository.createProductPrice(product, new Price(1, price));
        return Response.status(201).location(uriInfo.getAbsolutePathBuilder().path(String.valueOf(priceId)).build()).build();
    }
}
