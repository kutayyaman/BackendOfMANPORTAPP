package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Server;
import com.kutay.MANPORT.ws.dto.PageableDTO;
import com.kutay.MANPORT.ws.dto.ServerDTO;
import com.kutay.MANPORT.ws.repository.ServerRepository;
import com.kutay.MANPORT.ws.service.IServerService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ServerServiceImpl implements IServerService {
    private final ServerRepository serverRepository;
    private final MessageSource messageSource;

    public ServerServiceImpl(ServerRepository serverRepository, MessageSource messageSource) {
        this.serverRepository = serverRepository;
        this.messageSource = messageSource;
    }

    @Override
    public List<Server> findAll() {
        return serverRepository.findAllByRowStatus(RowStatus.ACTIVE);
    }

    @Override
    public List<Server> findAllByCountry(Country country) {
        return serverRepository.findAllByCountryAndRowStatus(country, RowStatus.ACTIVE);
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
        return serverRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
    }

    @Override
    public PageableDTO<ServerDTO> findAllWithApplicationServersAndCountry(Pageable pageable) {
        PageableDTO<ServerDTO> result = new PageableDTO();
        List<ServerDTO> resultContent = new ArrayList<>();

        Page<Server> page = serverRepository.findAllByRowStatus(RowStatus.ACTIVE, pageable);

        for (Server server : page.getContent()) {
            ServerDTO serverDTO = new ServerDTO(server);
            serverDTO.setLiveAppCount(server.getApplicationServers().size());
            resultContent.add(serverDTO);
        }
        result.setContent(resultContent);
        result.setFirst(page.isFirst());
        result.setLast(page.isLast());
        result.setNumber(page.getNumber());

        return result;
    }

    @Override
    public ResponseEntity<?> deleteServerById(Long id) {
        Server server = serverRepository.findFirstByIdAndRowStatus(id, RowStatus.ACTIVE);
        server.setRowStatus(RowStatus.DELETED);
        serverRepository.save(server);

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.info.server.Deleted", null, locale);
        return ResponseEntity.ok().body(message);
    }
}
