import SpringBootExample.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        productRepository.deleteAll(); // Clear DB before each test
        productRepository.saveAll(List.of(
                new Product(null, "Laptop", "electronics", 1000.0),
                new Product(null, "Chair", "furniture", 150.0)
        ));
    }

    @Test
    void testGetAllProducts() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/products")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "price,asc"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        logger.info("Response JSON: {}", responseBody);
    }

    @Test
    void testCreateProduct() throws Exception {
        Product newProduct = new Product(null, "Keyboard", "electronics", 45.0);

        MvcResult result = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name", is("Keyboard")))
                .andReturn();

        logger.info("Response JSON (createProduct): {}", result.getResponse().getContentAsString());
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product original = new Product(null, "Tablet", "electronics", 300.0);   Product saved = productRepository.save(original);   //product to update
        Product updatedProduct = new Product(null, "Tablet Pro", "electronics", 500.0);     // new data

        MvcResult result = mockMvc.perform(put("/api/products/" + saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Tablet Pro")))
                .andExpect(jsonPath("$.price", is(500.0)))
                .andReturn();

        logger.info("Update Response: {}", result.getResponse().getContentAsString());
    }


    @Test
    void testGetProductById() throws Exception {
        Product saved = productRepository.save(new Product(null, "Mouse", "electronics", 25.0));

        MvcResult result= mockMvc.perform(get("/api/products/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Mouse")))
                .andReturn();

        logger.info("Response JSON (getProductById): {}", result.getResponse().getContentAsString());
    }

    @Test
    void testGetProductById_NotFound() throws Exception {
        try {
            MvcResult result= mockMvc.perform(get("/api/products/9999"))
                    .andExpect(status().isNotFound())
                    .andReturn();
        }
        catch (Exception e){    logger.info("Message: "+ e); }
    }

    @Test
    void testListProducts_FilterSortPagination() throws Exception {
        productRepository.saveAll(List.of(
                new Product(null, "Keyboard", "electronics", 45.0),
                new Product(null, "Laptop", "electronics", 1000.0),
                new Product(null, "Chair", "furniture", 150.0),
                new Product(null, "Mouse", "electronics", 25.0)
        ));

        // Act + Assert: Filter name=Keyboard, category=electronics, sort by price desc
        MvcResult result = mockMvc.perform(get("/api/products")
                        .param("name", "Keyboard")
                        .param("category", "electronics")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "price", "desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].name", is("Keyboard")))
                .andExpect(jsonPath("$[0].price", is(45.0)))
                .andReturn();

        System.out.println("Filtered Response: " + result.getResponse().getContentAsString());
    }


    @Test
    void testCallExternalAPi() throws Exception {

        // Expected response from https://jsonplaceholder.typicode.com/posts/1
        String expectedResponse = "{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n}";


        MvcResult result = mockMvc.perform(get("/api/products/call-external-api"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println("Response from external API: " + response);

        //Assert the content contains expected keys
        Assertions.assertTrue(response.contains("userId"));
        Assertions.assertTrue(response.contains("title"));
        Assertions.assertEquals(expectedResponse, response);    // (expected, actual)
    }
}

