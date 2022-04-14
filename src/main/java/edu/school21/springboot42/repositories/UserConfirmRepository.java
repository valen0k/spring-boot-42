package edu.school21.springboot42.repositories;

import edu.school21.springboot42.models.UserConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserConfirmRepository extends JpaRepository<UserConfirm, Integer> {

    @Query("SELECT u FROM UserConfirm u WHERE u.userUuid = :uuid")
    Optional<UserConfirm> findByUserUuid(@Param("uuid") String uuid);

}
