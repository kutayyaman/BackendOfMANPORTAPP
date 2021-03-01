package com.kutay.MANPORT.ws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kutay.MANPORT.ws.MyAnnotations.UniqueEmail;
import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import com.kutay.MANPORT.ws.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel(value = "User Data Transfer Object")
@JsonInclude(JsonInclude.Include.NON_NULL) // yani cevap olarak UserDTO dondugumuz zaman diyelimki rowStatus null ozaman bunu geriye donme yani rowStatus'u cikart bunun icinden demis oluyoruz
@NoArgsConstructor
public class UserDTO {
    @ApiModelProperty(required = true,value = "name")
    @NotNull(message = "{manportapp.constraint.name.NotNull.message}")
    @Size(min = 4, max = 255)
    private String name;
    @ApiModelProperty(required = true,value = "surname")
    @NotNull
    @Size(min = 4, max = 255)
    private String surname;
    @ApiModelProperty(required = true,value = "email")
    //@Column(unique = true)
    @NotNull
    @Email
    @UniqueEmail // bu validation'i kendim yazdim yani hazir kutuphaden gelen bir validation degil.
    private String email;
    @ApiModelProperty(required = true,value = "password")
    @NotNull
    @Size(min = 8, max = 255)
    private String password;
    @ApiModelProperty(required = false,value = "lastLoginDate")
    private String lastLoginDate;
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
    private RowStatus rowStatus = RowStatus.ACTIVE;
    @ApiModelProperty(required = false,value = "teamId")
    private Long teamId;
    @ApiModelProperty(required = false,value = "teamName")
    private String teamName;

    public UserDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastLoginDate = user.getLastLoginDate();
        this.birthdayDate = user.getBirthdayDate();
        this.id = user.getId();
        this.createdDate = user.getCreatedDate();
        this.modifiedDate = user.getModifiedDate();
        this.createdBy = user.getCreatedBy();
        this.modifiedBy = user.getModifiedBy();
        this.rowStatus = user.getRowStatus();
        Team team = user.getTeam();
        this.teamId = team.getId();
        this.teamName = team.getName();
    }

    @Override
    public String toString() { //Normalde @Data ile bu toStringde oluşturuluyor ancak ben BaseEntity'dende gelen ozelliklerin ekrana yazdirilmasini istedigim icin kendim ezdim ve en sona super.toString() ekledim.
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", birthdayDate='" + birthdayDate + '\'' +
                '}' + " " + super.toString();
    }
}
