package curthproductservice.service;

import curthproductservice.model.InventoryType;
import curthproductservice.model.Product;
import curthproductservice.repository.ProductRepository;
import curthproductservice.utils.BarcodeUtils;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductProducerService productProducerService;

    public ProductService(ProductRepository productRepository,
                          ProductProducerService productProducerService) {
        this.productRepository = productRepository;
        this.productProducerService = productProducerService;
    }


    @Transactional
    public Integer addProduct(Product product) {
        try {
            Product newProduct = product.toBuilder()
                    .barcode(BarcodeUtils.generateBarcode())
                    .build();

            Product saved = productRepository.save(newProduct);
            log.info("tag: product-service | add product | New product saved | product Id: {}", saved.getId());
            productProducerService.productInventory(saved, InventoryType.ADD);
            return saved.getId();
        } catch (DataIntegrityViolationException e) {
            log.error("tag: product-service | add product | Constraint violation: {}", e.getMessage());
            throw new IllegalArgumentException("Product name or barcode already exists");
        } catch (Exception exception) {
            log.error("tag: product-service | add product | Unexpected error", exception);
            throw new RuntimeException("Failed to save product", exception);
        }
    }

}
