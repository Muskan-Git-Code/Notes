package SpringBootExample;

import java.util.*;

public interface ProductService {

    List<Product> getProducts(String name, String category, int page, int size, String sort[]);
    Optional<Product> getByName(String name);
    Product save(Product product);
    Optional<Product> updateProduct(String name, Product product);
    void deleteProduct(Long id);
    String callExternalApi();
}
