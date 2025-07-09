package com.example.test.Repository;

import com.example.test.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<Users,Integer> {
    Optional<Users> findUsersByUsername(String username);
}
