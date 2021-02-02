package com.kutay.ECommercialApp.ws.repository;

import com.kutay.ECommercialApp.ws.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
