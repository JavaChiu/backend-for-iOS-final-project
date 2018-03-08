package mobi.uchicago.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mobi.uchicago.finalproject.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
