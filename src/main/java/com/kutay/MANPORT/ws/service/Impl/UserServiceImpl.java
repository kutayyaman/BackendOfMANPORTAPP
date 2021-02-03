package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
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
