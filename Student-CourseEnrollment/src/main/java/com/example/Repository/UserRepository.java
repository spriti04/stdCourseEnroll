package com.example.Repository;

import com.example.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //Users Name Only
    List<User> findByCoursesId(int id);

    Optional<User> findByEmail(String email);
}
