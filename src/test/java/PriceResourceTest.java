import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.thoughtworks.com.domain.Product;
import org.thoughtworks.com.exception.PriceNotFoundException;
import org.thoughtworks.com.provider.ProductRepository;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceResourceTest extends JerseyTest {


    @Mock
    ProductRepository productRepository;

    @Captor
    ArgumentCaptor<Product> productCaptor;
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
