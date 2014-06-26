import org.junit.Test;
import org.thoughtworks.com.domain.Product;
import org.thoughtworks.com.provider.MyBatis;
import org.thoughtworks.com.provider.ProductRepository;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProductRepositoryTest {
    @Test
    public void should_get_product_by_id() throws IOException {
        MyBatis myBatis = new MyBatis();
        ProductRepository productRepository = myBatis.getSqlSessionFactory().openSession().getMapper(ProductRepository.class);
        Product product = productRepository.getProductById(1);
        assertThat(product.getId(), is(1));
    }
}
