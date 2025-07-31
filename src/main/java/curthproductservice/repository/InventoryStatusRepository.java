package curthproductservice.repository;

import curthproductservice.model.InventoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryStatusRepository extends JpaRepository<InventoryStatus, Integer> {
}
