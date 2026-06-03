package in.mesauravjha.creatorstore.repositories;

import in.mesauravjha.creatorstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
