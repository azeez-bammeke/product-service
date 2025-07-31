package curthproductservice.service;

import curthproductservice.model.InventoryStatus;
import curthproductservice.model.InventoryType;
import curthproductservice.model.Product;
import curthproductservice.repository.InventoryStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductProducerService {

    private final InventoryStatusRepository inventoryStatusRepository;

    private final StreamBridge streamBridge;

    @Autowired
    public ProductProducerService(StreamBridge streamBridge,
                                  InventoryStatusRepository inventoryStatusRepository) {
        this.streamBridge = streamBridge;
        this.inventoryStatusRepository = inventoryStatusRepository;
    }


    public void productStatus(String message) {
        streamBridge.send("productStatus-events-out-0", message);
    }

    public void productInventory(Product product, InventoryType inventoryType) {
        InventoryStatus inventoryStatus = InventoryStatus.builder()
                .id(product.getId())
                .inventoryType(inventoryType)
                .quantity(product.getQuantity())
                .productType(product.getProductType())
                .productName(product.getName())
                .createdAt(product.getCreatedDate())
                .updatedAt(product.getUpdatedDate())
                .build();
        InventoryStatus saved = inventoryStatusRepository.save(inventoryStatus);
        log.info("tag: product producer | publish inventory status: {}", saved);
        streamBridge.send("productInventory-events-out-0", saved);
    }
}
