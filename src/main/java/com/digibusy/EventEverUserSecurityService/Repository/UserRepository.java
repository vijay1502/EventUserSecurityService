package com.digibusy.EventEverUserSecurityService.Repository;

import com.digibusy.EventEverUserSecurityService.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,String> {
    @Query("select a from Users a where a.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);
}
