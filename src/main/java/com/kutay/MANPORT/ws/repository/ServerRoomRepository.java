package com.kutay.MANPORT.ws.repository;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.ServerRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerRoomRepository extends JpaRepository<ServerRoom,Long> {

}
