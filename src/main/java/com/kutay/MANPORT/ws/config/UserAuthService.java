package com.kutay.MANPORT.ws.config;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.service.Impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {
    private final IUserService userService;

    public UserAuthService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("Mail does not exist");
        }
        return user;
    }
}
