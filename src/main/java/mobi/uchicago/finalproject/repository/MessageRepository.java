package mobi.uchicago.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mobi.uchicago.finalproject.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
