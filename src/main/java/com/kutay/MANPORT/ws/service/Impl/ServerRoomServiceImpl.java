package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.ServerRoom;
import com.kutay.MANPORT.ws.repository.ServerRoomRepository;
import com.kutay.MANPORT.ws.service.IServerRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerRoomServiceImpl implements IServerRoomService {
    private final ServerRoomRepository serverRoomRepository;

    public ServerRoomServiceImpl(ServerRoomRepository serverRoomRepository) {
        this.serverRoomRepository = serverRoomRepository;
    }

}
