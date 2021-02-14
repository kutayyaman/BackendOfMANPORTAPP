package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant,Long> {
}
