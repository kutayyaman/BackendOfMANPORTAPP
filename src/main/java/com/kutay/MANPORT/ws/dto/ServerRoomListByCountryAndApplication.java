package com.kutay.MANPORT.ws.dto;

import com.kutay.MANPORT.ws.domain.Country;
import com.kutay.MANPORT.ws.domain.ServerRoom;
import lombok.Data;

import java.util.List;

@Data
public class ServerRoomListByCountryAndApplication {
    private Country country;
    private List<ServerRoom> serverRoomList;
}
