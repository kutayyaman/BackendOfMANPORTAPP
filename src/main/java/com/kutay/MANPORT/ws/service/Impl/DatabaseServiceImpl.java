package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Backend;
import com.kutay.MANPORT.ws.domain.Database;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.dto.BackendDTO;
import com.kutay.MANPORT.ws.dto.DatabaseDTO;
import com.kutay.MANPORT.ws.repository.DatabaseRepository;
import com.kutay.MANPORT.ws.service.IDatabaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseServiceImpl implements IDatabaseService {
    private final DatabaseRepository databaseRepository;

    public DatabaseServiceImpl(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    @Override
    public List<DatabaseDTO> findAll() {
        List<DatabaseDTO> result = new ArrayList<>();
        List<Database> databases = databaseRepository.findAllByRowStatus(RowStatus.ACTIVE);

        for (Database database : databases) {
            DatabaseDTO databaseDTO = new DatabaseDTO(database);
            result.add(databaseDTO);
        }
        return result;
    }

    @Override
    public Database findById(Long databaseId) {
        return databaseRepository.findFirstByIdAndRowStatus(databaseId,RowStatus.ACTIVE);
    }
}
