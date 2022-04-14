package edu.school21.springboot42.repositories;

import edu.school21.springboot42.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionsRepository extends JpaRepository<Session, Integer> {

    @Query("SELECT s FROM Session s JOIN FETCH s.film")
    List<Session> findAllSessions();

}
