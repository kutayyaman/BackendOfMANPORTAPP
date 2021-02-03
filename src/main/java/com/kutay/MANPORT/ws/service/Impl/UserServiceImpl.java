package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.models.User;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void save(User user) {
        setRegistrationDateAndCreatedDateOfUser(user);
        encodeThePasswordOfUser(user);
        userRepository.save(user);
    }

    private void encodeThePasswordOfUser(User user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }

    private void setRegistrationDateAndCreatedDateOfUser(User user) {
        String currentDateAsAString = CurrentDateCreator.currentDateAsString();

        user.setRegistrationDate(currentDateAsAString);
        user.setCreatedDate(currentDateAsAString);
    }
}
