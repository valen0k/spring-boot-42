package edu.school21.springboot42.repositories;

import edu.school21.springboot42.models.LogInfo;
import edu.school21.springboot42.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogInfoRepository extends JpaRepository<LogInfo, Integer> {

    @Query("SELECT l FROM LogInfo l WHERE l.user = :user")
    List<LogInfo> findByUser(@Param("user") User user);

}
