package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}