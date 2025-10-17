package SpringBootExample;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable(cacheNames = "ProductsCache", key = "#name")
    public Optional<Product> getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    @CachePut(cacheNames = "ProductsCache", key = "#name")
    public Optional<Product> updateProduct(String name, Product newProduct) {

        Optional<Product> val= repo.findByName(name);
        if(val.isPresent()){
            // update the existing product and return
            Product existing= val.get();    existing.setName(newProduct.getName());     existing.setCategory(newProduct.getCategory());     existing.setPrice(newProduct.getPrice());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();
    }

    @Override
    @CacheEvict(cacheNames = "ProductsCache", key = "#id")
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    // fetch products via filtering, sorting, pagination
    @Override
    public List<Product> getProducts(String name, String category, int page, int size, String sort[]) {
        String sortBy = sort[0] != null ? sort[0] : "id"; // default column
        String dir = sort[1] != null ? sort[1].toUpperCase() : "ASC"; // default direction

        Sort.Direction direction;
        try {
            direction = Sort.Direction.valueOf(dir);
        } catch (IllegalArgumentException e) {
            direction = Sort.Direction.ASC; // fallback if wrong value
        }

        Pageable pageable = PageRequest.of(page, size, direction, sortBy);  // Sorting, Pagination
        return repo.findByFilters(name, category, pageable).getContent();   // filtration directly by query
    }

    @Override
    public String callExternalApi() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();    headers.set("Accept", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);   // (header, body) if given
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);      // (url, httpMethod, request, class)

        return response.getBody();
    }
}

