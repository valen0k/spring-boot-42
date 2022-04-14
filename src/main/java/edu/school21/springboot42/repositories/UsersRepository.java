package edu.school21.springboot42.repositories;

import edu.school21.springboot42.models.Confirmation;
import edu.school21.springboot42.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.picName = :pic_name WHERE u.email = :email")
    void setUserPic(@Param("email") String email, @Param("pic_name") String picName);

    @Query(nativeQuery = true,
            value = "SELECT EXISTS(SELECT email FROM users_cinema WHERE email = :email)")
    boolean findEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE User u SET u.confirmation = :confirmation WHERE u.email = :email")
    void setUserConfirmation(@Param("email") String email, @Param("confirmation") Confirmation confirmation);

}
