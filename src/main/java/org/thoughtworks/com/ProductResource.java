package org.thoughtworks.com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


@Path("/products")
public class ProductResource {
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getProduct() {
        return "s";
    }
}
