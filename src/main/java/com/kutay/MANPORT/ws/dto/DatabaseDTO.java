package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.domain.Database;
import com.kutay.MANPORT.ws.domain.Frontend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Database Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseDTO {
    @ApiModelProperty(required = true, value = "id")
    private long id;
    @ApiModelProperty(required = true, value = "name")
    private String name;

    public DatabaseDTO(Database database) {
        setId(database.getId());
        setName(database.getName());
    }
}
