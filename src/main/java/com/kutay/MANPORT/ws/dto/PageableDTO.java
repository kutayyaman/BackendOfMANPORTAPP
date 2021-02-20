package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(value = "Pageable Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDTO<T> {
    @ApiModelProperty(required = true,value = "content")
    List<T> content;
    @ApiModelProperty(required = true,value = "isFirst")
    boolean isFirst;
    @ApiModelProperty(required = true,value = "isLast")
    boolean isLast;
    @ApiModelProperty(required = true,value = "number")
    int number;
}
