package com.kutay.MANPORT.ws.service;

import com.kutay.MANPORT.ws.domain.Database;
import com.kutay.MANPORT.ws.dto.DatabaseDTO;

import java.util.List;

public interface IDatabaseService {
    List<DatabaseDTO> findAll();

    Database findById(Long databaseId);
}
