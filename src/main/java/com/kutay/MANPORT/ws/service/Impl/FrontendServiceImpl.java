package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Frontend;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.FrontendDTO;
import com.kutay.MANPORT.ws.repository.FrontendRepository;
import com.kutay.MANPORT.ws.service.IFrontendService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FrontendServiceImpl implements IFrontendService {
    private final FrontendRepository frontendRepository;

    public FrontendServiceImpl(FrontendRepository frontendRepository) {
        this.frontendRepository = frontendRepository;
    }

    @Override
    public List<FrontendDTO> findAll() {
        List<FrontendDTO> result = new ArrayList<>();

        List<Frontend> frontends = frontendRepository.findAllByRowStatus(RowStatus.ACTIVE);
        for(Frontend frontend:frontends){
            FrontendDTO frontendDTO = new FrontendDTO(frontend);
            result.add(frontendDTO);
        }

        return result;
    }

    @Override
    public Frontend findById(Long id) {
        return frontendRepository.findFirstByIdAndRowStatus(id,RowStatus.ACTIVE);
    }
}
