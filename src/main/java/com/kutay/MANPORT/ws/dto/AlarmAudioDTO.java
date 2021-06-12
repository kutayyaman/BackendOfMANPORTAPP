package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "AlarmAudio Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmAudioDTO {
    @ApiModelProperty(required = true, value = "isLineStopRisk")
    private boolean isLineStopRisk = true;
    @ApiModelProperty(required = true, value = "impact")
    private String impact;
}
