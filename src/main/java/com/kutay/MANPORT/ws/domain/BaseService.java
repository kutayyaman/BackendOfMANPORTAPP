package com.kutay.MANPORT.ws.domain;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseService extends BaseEntity {
    private String name;
}
