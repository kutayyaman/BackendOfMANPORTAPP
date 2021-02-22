package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Server;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Server Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerDTO {
    @ApiModelProperty(required = true,value = "id")
    private long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;

    public ServerDTO(Server server){ //bunu ayni bu sekilde kullanan yerler var sakin elleme.
        this.id=server.getId();
        this.name=server.getName();
    }
}
