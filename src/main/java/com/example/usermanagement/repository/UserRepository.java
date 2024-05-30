package com.example.usermanagement.repository;
import com.example.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUserId(Integer userId);
}

