package com.kutay.MANPORT.ws.dto;

import com.kutay.MANPORT.ws.domain.RowStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@ApiModel(value = "User Data Transfer Object")
public class UserDTO {
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
