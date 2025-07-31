package curthproductservice.controller;

import curthproductservice.model.Product;
import curthproductservice.service.ProductProducerService;
import curthproductservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductProducerService productProducerService;
    private final ProductService productService;

    public ProductController(ProductProducerService productProducerService, ProductService productService) {
        this.productProducerService = productProducerService;
        this.productService = productService;
    }

    @PostMapping("/product")
    public String sendMessage(@RequestParam String message) {
        productProducerService.productStatus(message);
        log.info("Message sent: {}", message);
        return "Message sent to Kafka: " + message;
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

}
