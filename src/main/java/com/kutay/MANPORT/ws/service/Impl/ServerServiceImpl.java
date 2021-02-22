package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.dto.ServerDTO;
import com.kutay.MANPORT.ws.repository.ServerRepository;
import com.kutay.MANPORT.ws.service.IServerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerServiceImpl implements IServerService {
    private final ServerRepository serverRepository;

    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public List<Server> findAll() {
        return serverRepository.findAllByRowStatus(RowStatus.ACTIVE);
    }

    @Override
    public List<Server> findAllByCountry(Country country) {
        return serverRepository.findAllByCountryAndRowStatus(country,RowStatus.ACTIVE);
    }

    @Override
    public List<ServerDTO> findAllByCountryAsDTO(Country country) {
        List<ServerDTO> result = new ArrayList<>();
        List<Server> serverList = findAllByCountry(country);
        for (Server server : serverList) {
            ServerDTO serverDTO = new ServerDTO(server);
            result.add(serverDTO);
        }
        return result;
    }

    @Override
    public Server findFirstById(Long id) {
        return serverRepository.findFirstByIdAndRowStatus(id,RowStatus.ACTIVE);
    }
}
