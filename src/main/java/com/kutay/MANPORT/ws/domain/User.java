package com.kutay.MANPORT.ws.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseEntity implements UserDetails {
    private String name;
    private String surname;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_User");
    }

    @Override
    public String getUsername() { //burada mail donuyorum cunku Basic Auth'da username ve password gerekiyor ancak ben username yerine mail kullaniyorum.
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
