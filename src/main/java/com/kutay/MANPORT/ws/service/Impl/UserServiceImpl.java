package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.shared.GenericResponse;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> save(UserDTO userDTO) {
        setCreatedDateOfUserDTO(userDTO);
        encodeThePasswordOfUserDTO(userDTO);
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return ResponseEntity.ok(new GenericResponse("user created"));
    }

    private void encodeThePasswordOfUserDTO(UserDTO user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }

    private void setCreatedDateOfUserDTO(UserDTO user) {
        String currentDateAsAString = CurrentDateCreator.currentDateAsString();
        user.setCreatedDate(currentDateAsAString);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
