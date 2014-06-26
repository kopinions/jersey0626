import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.thoughtworks.com.domain.Price;
import org.thoughtworks.com.domain.Product;
import org.thoughtworks.com.exception.PriceNotFoundException;
import org.thoughtworks.com.provider.ProductRepository;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceResourceTest extends JerseyTest {


    @Mock
    ProductRepository productRepository;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    ArgumentCaptor<Price> priceArgumentCaptor;
    @Test
    public void should_return_200_when_get_price() {
        Response response = target("/products/1/prices/1").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(response.getStatus(), 200);
    }


    @Test
    public void should_return_404_when_can_not_find_product() {
        when(productRepository.getPriceById(anyInt())).thenThrow(PriceNotFoundException.class);
        Response response = target("/products/2/prices/2").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        assertEquals(response.getStatus(), 404);
    }

    @Test
    public void should_create_product_price() {
        Form createPriceRequest = new Form();
        createPriceRequest.param("price", String.valueOf(1.1));
        createPriceRequest.param("productId", String.valueOf(1));

        when(productRepository.getProductById(2)).thenReturn(new Product(2));
        when(productRepository.createProductPrice(any(Product.class), any(Price.class))).thenReturn(2);

        Response response = target("/products/2/prices").request().post(Entity.form(createPriceRequest));
        assertEquals(response.getStatus(), 201);
        assertThat(response.getLocation().toString(), endsWith("/products/2/prices/2"));

        verify(productRepository).createProductPrice(productArgumentCaptor.capture(), priceArgumentCaptor.capture());
        assertThat(productArgumentCaptor.getValue().getId(), is(2));
        assertThat(priceArgumentCaptor.getValue().getPrice(), is(1.1));
    }

    @Test
    public void should_get_all_prices() {
        Response response = target("/products/2/prices").request().get();
        assertEquals(response.getStatus(), 200);

    }

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.packages("org.thoughtworks.com");
        resourceConfig.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(productRepository).to(ProductRepository.class);
            }
        });
        return resourceConfig;
    }
}
