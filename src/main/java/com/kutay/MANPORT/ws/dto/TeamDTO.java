package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Team;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Country Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {
    @ApiModelProperty(required = true,value = "id")
    private Long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;

    public TeamDTO(Team team) {
        setId(team.getId());
        setName(team.getName());
    }
}
