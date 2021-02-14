package com.kutay.MANPORT.ws.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryNameWithOtherDataListDTO {
    private String countryName;
    List<ServerRoomDataDTO> serverRoomDataDTOList;
}
