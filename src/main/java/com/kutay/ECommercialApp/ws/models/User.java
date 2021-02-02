package com.kutay.ECommercialApp.ws.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@ApiModel(value = "User Data Transfer Object")
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseEntity {
    @ApiModelProperty(required = true,value = "name")
    private String name;
    @ApiModelProperty(required = true,value = "surname")
    private String surname;
    @ApiModelProperty(required = true,value = "email")
    @Column(unique = true)
    private String email;
    @ApiModelProperty(required = true,value = "password")
    private String password;
    @ApiModelProperty(required = false,value = "defaultAddressId")
    private Long defaultAddressId;
    @ApiModelProperty(required = false,value = "lastLoginDate")
    private String lastLoginDate;
    @ApiModelProperty(required = false,value = "registrationDate")
    private String registrationDate;
    @ApiModelProperty(required = false,value = "birthdayDate")
    private String birthdayDate;

    @Override
    public String toString() { //Normalde @Data ile bu toStringde oluşturuluyor ancak ben BaseEntity'dende gelen ozelliklerin ekrana yazdirilmasini istedigim icin kendim ezdim ve en sona super.toString() ekledim.
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", defaultAddressId=" + defaultAddressId +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                '}' + " " + super.toString();
    }
}
