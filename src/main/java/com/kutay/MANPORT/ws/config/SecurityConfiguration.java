package com.kutay.MANPORT.ws.config;

import com.kutay.MANPORT.ws.util.ApiPaths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // bunu ekledik artik herhangi bir controllerin methodunda @PreAuthorize("#username == #loggedInUser.username") gibi ifadeler yazabiliriz mesela bu yazdigimizin anlami username loggedInUser'in username'ine esitse gir buraya degilse geriye error doner veya @PreAuthorize("#username == principal.username") principal ile giris yapan kullaniciya ulasabiliriz bu yazim tarzlarina Spring Expression Language(SpEL) denir.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserAuthService userAuthService;

    //TODO: bu @Lazy'yi circular dependency sorununu cozmek icin koydum ama o soruna tekrar bak
    public SecurityConfiguration(@Lazy UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic().authenticationEntryPoint(new AuthEntryPoint());

        http.authorizeRequests().antMatchers(HttpMethod.POST, ApiPaths.AuthCtrl.CTRL).authenticated()
                .and()
                .authorizeRequests().anyRequest().permitAll();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() { //bundan bir tane nesne olusmasi yeterli bizim icin yani her yerde tek tek olusturmayalim diye bean haline getirdik.
        return new BCryptPasswordEncoder();
    }
}
