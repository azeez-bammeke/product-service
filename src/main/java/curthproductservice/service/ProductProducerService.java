package curthproductservice.service;

import curthproductservice.mapper.AvroObjectMapper;
import curthproductservice.model.InventoryStatus;
import curthproductservice.model.InventoryType;
import curthproductservice.model.Product;
import curthproductservice.repository.InventoryStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductProducerService {

    private final InventoryStatusRepository inventoryStatusRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${app.kafka.topic.inventory-events}")
    private String inventoryTopic;

    //private final StreamBridge streamBridge;

    @Autowired
    public ProductProducerService(
            KafkaTemplate<String, Object> kafkaTemplate,
            InventoryStatusRepository inventoryStatusRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryStatusRepository = inventoryStatusRepository;
    }


    public void productStatus(String message) {

        //streamBridge.send("productStatus-events-out-0", message);
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
        curthproductservice.avro.InventoryStatus inv = AvroObjectMapper.inventoryStatusToAvro(saved);
        kafkaTemplate.send(inventoryTopic, inv);
    }
}
