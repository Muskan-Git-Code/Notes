import SpringBootExample.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest(classes = ApiApplication.class)
public class ProductControllerTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        productService.save(new Product(null, "Gaming Laptop", "electronics", 1500.0));
        productService.save(new Product(null, "Office Laptop", "electronics", 1200.0));
        productService.save(new Product(null, "Laptop Sleeve", "electronics", 50.0));
        productService.save(new Product(null, "Jeans", "clothes", 1200.0));
        productService.save(new Product(null, "Shirt", "clothes", 500.0));
    }

    @Test
    void testListProducts() throws Exception {
        // Get products with Filter by name Laptop; Pagination by page 1 size 2; Sorting by price desc
        List<Product> result = productService.getProducts("Laptop", null, 0, 2, new String[]{"price", "asc"});

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Laptop Sleeve", result.get(0).getName());
    }

    @Test
    void testUpdateProduct(){
        Product product = productService.getByName("Gaming Laptop").get();
        product.setPrice(1600.0);
        productService.save(product);

        Product updated = productService.getByName("Gaming Laptop").orElseThrow();
        Assertions.assertEquals(1600.0, updated.getPrice());
        Assertions.assertEquals("electronics", updated.getCategory());
    }

    @Test
    void testGetByName(){
        Product product = productService.getByName("Office Laptop").orElseThrow();
        Assertions.assertEquals("electronics", product.getCategory());
        Assertions.assertEquals(1200, product.getPrice());
    }

    @Test
    void testDeleteById(){
        Product product = productService.getByName("Office Laptop").orElseThrow();
        productService.deleteProduct(product.getId());
        Assertions.assertEquals(4, productRepository.count());
    }


    @Test
    void testCallExternalAPi() throws Exception {
        // Expected response from https://jsonplaceholder.typicode.com/posts/1
        String expectedResponse = "{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n}";

        String result= productService.callExternalApi();
        Assertions.assertEquals(expectedResponse, result);
    }
}

