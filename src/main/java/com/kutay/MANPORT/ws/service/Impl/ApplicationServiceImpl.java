package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.Application;
import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.ServerRoom;
import com.kutay.MANPORT.ws.dto.ApplicationNameWithOtherDataListDTO;
import com.kutay.MANPORT.ws.dto.ApplicationWithIssuesData2DTO;
import com.kutay.MANPORT.ws.dto.CountryNameWithOtherDataListDTO;
import com.kutay.MANPORT.ws.dto.ServerRoomDataDTO;
import com.kutay.MANPORT.ws.repository.ApplicationRepository;
import com.kutay.MANPORT.ws.service.IApplicationService;
import com.kutay.MANPORT.ws.service.IServerRoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements IApplicationService {
    private final ApplicationRepository applicationRepository;
    private final IServerRoomService serverRoomService;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, IServerRoomService serverRoomService) {
        this.applicationRepository = applicationRepository;
        this.serverRoomService = serverRoomService;
    }


    @Override
    public List<ApplicationNameWithOtherDataListDTO> getApplicationsWithIssues() {
        List<Application> applicationList = applicationRepository.findAll();
        List<ApplicationNameWithOtherDataListDTO> applicationNameWithOtherDataListDTOList = new ArrayList<>();

        for (Application application : applicationList) {
            List<Country> countryList = (List<Country>) application.getCountries();

            ApplicationNameWithOtherDataListDTO applicationNameWithOtherDataListDTO = new ApplicationNameWithOtherDataListDTO();

            applicationNameWithOtherDataListDTO.setApplicationShortName(application.getShortName());
            List<CountryNameWithOtherDataListDTO> countryNameWithOtherDataListDTOList = new ArrayList<>();

            for (Country country : countryList) {
                List<ServerRoom> allServerRoomOfApp = (List<ServerRoom>) application.getServerRooms();
                CountryNameWithOtherDataListDTO countryNameWithOtherDataListDTO = new CountryNameWithOtherDataListDTO();
                countryNameWithOtherDataListDTO.setCountryName(country.getName());
                List<ServerRoomDataDTO> serverRoomDataDTOList = new ArrayList<>();
                for (ServerRoom serverRoom : allServerRoomOfApp) {
                    if (serverRoom.getCountry().getId() == country.getId()) {
                        ServerRoomDataDTO serverRoomDataDTO = new ServerRoomDataDTO();
                        serverRoomDataDTO.setServerRoomName(serverRoom.getName());
                        serverRoomDataDTOList.add(serverRoomDataDTO);
                    }
                }
                countryNameWithOtherDataListDTO.setServerRoomDataDTOList(serverRoomDataDTOList);
                countryNameWithOtherDataListDTOList.add(countryNameWithOtherDataListDTO);
            }
            applicationNameWithOtherDataListDTO.setCountryNameWithOtherDataListDTOList(countryNameWithOtherDataListDTOList);
            applicationNameWithOtherDataListDTOList.add(applicationNameWithOtherDataListDTO);
        }
        return applicationNameWithOtherDataListDTOList;
    }
}
