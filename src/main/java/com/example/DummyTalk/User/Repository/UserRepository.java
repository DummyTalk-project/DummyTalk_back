package com.example.DummyTalk.User.Repository;

import com.example.DummyTalk.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserEmail(String email);

    boolean existsByUserEmail(String email);
}
