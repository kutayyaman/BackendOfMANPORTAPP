package com.kutay.MANPORT.ws.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseEntity {
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private Long defaultAddressId;
    private String lastLoginDate;
    private String registrationDate;
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
