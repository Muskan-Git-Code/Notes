package SpringBootExample;

import org.springframework.data.domain.Pageable;

import java.util.*;

public interface ProductService {

    List<Product> getProducts(String name, String category, int page, int size, String sort[]);
    Optional<Product> getById(Long id);
    Product save(Product product);
    Optional<Product> updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
