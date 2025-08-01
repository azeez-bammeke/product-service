package curthproductservice.mapper;

import curthproductservice.avro.InventoryStatus;


public class AvroObjectMapper {

    public static InventoryStatus inventoryStatusToAvro(curthproductservice.model.InventoryStatus entity) {
        return InventoryStatus.newBuilder()
                .setId(entity.getId().toString())
                .setInventoryType(entity.getInventoryType().toString())
                .setQuantity(entity.getQuantity())
                .setProductName(entity.getProductName())
                .setProductType(entity.getProductType().toString())
                .setCreatedAt(entity.getCreatedAt().toString())
                .setUpdatedAt(entity.getUpdatedAt().toString())
                .build();
    }
}
