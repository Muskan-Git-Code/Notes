package SpringBootExample;

import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product newProduct) {

        Optional<Product> val= repo.findById(id);
        if(val.isPresent()){
            // update the existing product and return
            Product existing= val.get();    existing.setName(newProduct.getName());     existing.setCategory(newProduct.getCategory());     existing.setPrice(newProduct.getPrice());
            return Optional.of(repo.save(existing));
        }
        return Optional.empty();
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    // fetch products, filtering, sorting, pagination
    @Override
    public List<Product> getProducts(String name, String category, int page, int size, String sort[]) {
        try{
            List<Product> allProducts = repo.findAll();

            // Filtering
            List<Product> filtered = new ArrayList<>();
            for(int i=0; i<allProducts.size(); i++) {
                Product p= allProducts.get(i);
                if( (name==null || p.getName().equalsIgnoreCase(name)) && (category==null || p.getCategory().equalsIgnoreCase(category)) ){     filtered.add(p); }
            }

            // Sorting
            if (sort != null && sort.length == 2) {
                String sortBy = sort[0];
                String direction = sort[1].toLowerCase();

                Field field= Product.class.getDeclaredField(sortBy);
                filtered.sort( (p1, p2) -> {
                    try {
                        Comparable val1= (Comparable)field.get(p1);
                        Comparable val2= (Comparable)field.get(p2);

                        if(direction.equals("asc")){    return val1.compareTo(val2); }
                        return val2.compareTo(val1);
                    } catch (Exception e) {}
                    return 0;
                });
            }

            // Pagination
            int start= page*size;   int end= Math.min(start+size, filtered.size());
            if(start >= filtered.size()){   return new ArrayList<>(); } // outOfRange
            return filtered.subList(start, end);
        }
        catch (Exception e){}
        return null;
    }
}

