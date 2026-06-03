package in.mesauravjha.creatorstore.repositories;

import in.mesauravjha.creatorstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
