package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
