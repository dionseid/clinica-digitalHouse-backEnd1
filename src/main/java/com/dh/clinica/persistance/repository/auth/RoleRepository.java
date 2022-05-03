package com.dh.clinica.persistance.repository.auth;

import com.dh.clinica.persistance.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role r where r.name =:name")
    Optional<Role> getRoleByName(@Param("name") String name);
}
