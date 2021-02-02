package com.kutay.ECommercialApp.ws.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(required = true,value = "id")
    private Long id;
    @ApiModelProperty(required = false,value = "createdDate")
    private String createdDate;
    @ApiModelProperty(required = false,value = "modifiedDate")
    private String modifiedDate;
    @ApiModelProperty(required = false,value = "createdBy")
    private String createdBy;
    @ApiModelProperty(required = false,value = "modifiedBy")
    private String modifiedBy;
    @ApiModelProperty(required = false,value = "rowStatus")
    @Enumerated(EnumType.STRING)
    private RowStatus rowStatus = RowStatus.ACTIVE;
}
