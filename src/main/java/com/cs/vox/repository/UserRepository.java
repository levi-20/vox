package com.cs.vox.repository;

import com.cs.vox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findByUsernameContainingIgnoreCase(String searchTerm);

    List<User> findByNameContainingIgnoreCase(String name);

    Optional<User> findByUsername(String username);
}
