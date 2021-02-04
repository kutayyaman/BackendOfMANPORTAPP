package com.kutay.MANPORT.ws.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseEntity {
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String password;
    private String lastLoginDate;
    private String birthdayDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "applicationManager", fetch = FetchType.LAZY)
    private Set<Application> applications = new HashSet<>();


    @Override
    public String toString() { //Normalde @Data ile bu toStringde oluşturuluyor ancak ben BaseEntity'dende gelen ozelliklerin ekrana yazdirilmasini istedigim icin kendim ezdim ve en sona super.toString() ekledim.
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                '}' + " " + super.toString();
    }
}
