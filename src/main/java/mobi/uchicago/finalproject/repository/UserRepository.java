package mobi.uchicago.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mobi.uchicago.finalproject.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
