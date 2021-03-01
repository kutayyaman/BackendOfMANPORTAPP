package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Backend;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.BackendDTO;
import com.kutay.MANPORT.ws.repository.BackendRepository;
import com.kutay.MANPORT.ws.service.IBackendService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackendServiceImpl implements IBackendService {
    private final BackendRepository backendRepository;

    public BackendServiceImpl(BackendRepository backendRepository) {
        this.backendRepository = backendRepository;
    }

    @Override
    public List<BackendDTO> findAll() {
        List<BackendDTO> result = new ArrayList<>();
        List<Backend> backends = backendRepository.findAllByRowStatus(RowStatus.ACTIVE);

        for(Backend backend : backends){
            BackendDTO backendDTO = new BackendDTO(backend);
            result.add(backendDTO);
        }
        return result;
    }

    @Override
    public Backend findById(Long backendId) {
        return backendRepository.findFirstByIdAndRowStatus(backendId,RowStatus.ACTIVE);
    }
}
