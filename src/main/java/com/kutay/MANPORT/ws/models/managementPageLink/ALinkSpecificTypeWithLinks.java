package com.kutay.MANPORT.ws.models.managementPageLink;

import com.kutay.MANPORT.ws.dto.LinkDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ALinkSpecificTypeWithLinks {
    private String linkSpecificType;
    private List<LinkDTO> links = new ArrayList<>();
}
