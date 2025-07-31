package curthproductservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class InventoryStatus {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String productName;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    private Instant createdAt;

    private Instant updatedAt;
}
