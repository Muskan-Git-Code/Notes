package SpringBootExample;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
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
        return new ResponseEntity<>(products, null, HttpStatus.OK);
    }

    // POST /api/products
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();    headers.set("Authorization", "Bearer abc123");
        Product savedProduct = service.save(product);

        return new ResponseEntity<>(savedProduct, headers, HttpStatus.CREATED);
    }

    // GET Product by id: GET /api/products/{id}
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProduct(@PathVariable String name) {
        Optional<Product> product = service.getByName(name);

        if(product.isPresent()){    return new ResponseEntity<>(product.get(), HttpStatus.OK); }
        return ResponseEntity.notFound().build();
    }

    // PUT /api/products/{id}   // Complete product is given in input
    @PutMapping("/{name}")
    public ResponseEntity<Product> updateProduct(@PathVariable String name, @RequestBody Product product) {
        Optional<Product> updated = service.updateProduct(name, product);

        if(updated.isPresent()){    return new ResponseEntity<>(updated.get(), HttpStatus.OK); }
        return ResponseEntity.notFound().build();
    }

//    @PatchMapping("/{name}")  // Partial product is given in input
//    public ResponseEntity<Product> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {}


    // DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    // calling expernal api
    @GetMapping("/call-external-api")
    public ResponseEntity<String> callExternalApi() {
        return new ResponseEntity<>(service.callExternalApi(), null, HttpStatus.CREATED);
    }
}
