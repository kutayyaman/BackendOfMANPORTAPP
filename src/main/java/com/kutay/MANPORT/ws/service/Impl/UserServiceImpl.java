package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.error.ApiError;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.shared.GenericResponse;
import com.kutay.MANPORT.ws.util.ApiPaths;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> save(UserDTO userDTO) {
        ApiError apiError = validateUserDTO(userDTO);
        if (apiError != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
        }
        setCreatedDateOfUserDTO(userDTO);
        encodeThePasswordOfUserDTO(userDTO);
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return ResponseEntity.ok(new GenericResponse("user created"));
    }

    private ApiError validateUserDTO(UserDTO userDTO) {
        ApiError error = new ApiError(400,"Validation error", ApiPaths.UserCtrl.CTRL);
        Map<String, String> validationErrors = new HashMap<>();

        String name = userDTO.getName();
        String surname = userDTO.getSurname();
        String mail = userDTO.getEmail();
        String password = userDTO.getPassword();

        if (name == null || name.isEmpty()) {
            validationErrors.put("name", "name cannot be null");
        }
        if (surname == null || surname.isEmpty()) {
            validationErrors.put("surname", "surname cannot be null");
        }

        if(!validationErrors.isEmpty()){
            error.setValidationErrors(validationErrors);
            return error;
        }
        return null;
    }

    private void encodeThePasswordOfUserDTO(UserDTO user) {
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
    }

    private void setCreatedDateOfUserDTO(UserDTO user) {
        String currentDateAsAString = CurrentDateCreator.currentDateAsString();
        user.setCreatedDate(currentDateAsAString);
    }
}
