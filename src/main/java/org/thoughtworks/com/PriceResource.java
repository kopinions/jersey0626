package org.thoughtworks.com;

import org.thoughtworks.com.json.response.PriceJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class PriceResource {


    @GET
    @Path("{id}")
    public PriceJson getPrice() {
        return new PriceJson();
    }
}
