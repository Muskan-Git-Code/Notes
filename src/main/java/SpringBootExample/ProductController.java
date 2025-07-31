package SpringBootExample;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductServiceImpl service;

    public ProductController(ProductServiceImpl service) {  this.service = service; }

    // GET: Filter + Pagination + Sorting, with optional header input
    // Example: Get products filtered by name: GET /api/products?name=Chair,Table&page=0&size=5&sort=price,desc
    @GetMapping
    public ResponseEntity<List<Product>> listProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        List<Product> products= service.getProducts(name, category, page, size, sort);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();    headers.set("Authorization", "Bearer abc123");
        Product savedProduct = service.save(product);

        return new ResponseEntity<>(savedProduct, headers, HttpStatus.CREATED);
    }

    // GET Product by id: GET /api/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = service.getById(id);

        if(product.isPresent()){    return new ResponseEntity<>(product.get(), HttpStatus.OK); }
        return ResponseEntity.notFound().build();
    }

    // PUT /api/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> updated = service.updateProduct(id, product);

        if(updated.isPresent()){    return new ResponseEntity<>(updated.get(), HttpStatus.OK); }
        return ResponseEntity.notFound().build();
    }


    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    // calling expernal api
    @GetMapping("/call-external-api")
    public ResponseEntity<String> callExternalApi() {

        String url = "https://jsonplaceholder.typicode.com/posts/1";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();    headers.set("Accept", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);   // (header, body) if given

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);      // (url, httpMethod, request, class)

        return new ResponseEntity<>(response.getBody(), headers, HttpStatus.CREATED);
    }
}
