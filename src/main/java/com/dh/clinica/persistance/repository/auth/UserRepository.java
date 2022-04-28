package com.dh.clinica.persistance.repository.auth;

import com.dh.clinica.persistance.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    // This would produce a more robust outcome than the default next method
    Optional<User> findByUsernameOrMail(String username, String email);

    Optional<User> findByUsername(String username);

    /* JulianPariss implementation:
    @Query("from User u where u.name =:name")
    Optional<User> getUserByName(@Param("name") String name);
    It refers to username by 'name', hence it does the @Query */
}
