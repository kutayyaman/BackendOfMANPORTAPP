package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "JobInterface Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class JobInterfaceDTO {
    @ApiModelProperty(required = true,value = "id")
    private Long id;
    @ApiModelProperty(required = true,value = "name")
    private String name;

    public JobInterfaceDTO(Long id, String name){ // bunu jobInterfaceRepository'de bir query ihtiyac duyuyor bu kurucuyu elleme
        this.id = id;
        this.name=name;
    }
}
