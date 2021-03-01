package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Backend;
import com.kutay.MANPORT.ws.dto.BackendDTO;

import java.util.List;

public interface IBackendService {
    List<BackendDTO> findAll();

    Backend findById(Long backendId);
}
