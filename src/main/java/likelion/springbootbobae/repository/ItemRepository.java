package likelion.springbootbobae.repository;

import likelion.springbootbobae.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
