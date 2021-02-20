package com.kutay.MANPORT.ws.domain;

import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String createdDate = CurrentDateCreator.currentDateAsString();
    private String modifiedDate;
    private String createdBy;
    private String modifiedBy;
    @Enumerated(EnumType.STRING)
    private RowStatus rowStatus = RowStatus.ACTIVE;
}
