package edu.school21.springboot42.repositories;

import edu.school21.springboot42.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallsRepository extends JpaRepository<Hall, Integer> {
}
