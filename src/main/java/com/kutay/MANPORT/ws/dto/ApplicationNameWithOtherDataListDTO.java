package com.kutay.MANPORT.ws.dto;

import lombok.Data;
import java.util.List;

@Data
public class ApplicationNameWithOtherDataListDTO {
    private String applicationShortName;
    private List<CountryNameWithOtherDataListDTO> countryNameWithOtherDataListDTOList;
}
