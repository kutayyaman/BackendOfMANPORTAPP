package com.kutay.MANPORT.ws.service.Impl;

import com.kutay.MANPORT.ws.domain.RowStatus;
import com.kutay.MANPORT.ws.domain.Team;
import com.kutay.MANPORT.ws.domain.User;
import com.kutay.MANPORT.ws.dto.TeamDTO;
import com.kutay.MANPORT.ws.dto.UserDTO;
import com.kutay.MANPORT.ws.repository.UserRepository;
import com.kutay.MANPORT.ws.service.ITeamService;
import com.kutay.MANPORT.ws.service.IUserService;
import com.kutay.MANPORT.ws.shared.GenericResponse;
import com.kutay.MANPORT.ws.util.CurrentDateCreator;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final ITeamService teamService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, MessageSource messageSource, ITeamService teamService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.messageSource = messageSource;
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<?> save(UserDTO userDTO) {
        setCreatedDateOfUserDTO(userDTO);
        encodeThePasswordOfUserDTO(userDTO);
        Team team = new Team();
        team.setId(userDTO.getTeamId());

        User user = modelMapper.map(userDTO, User.class);
        user.setTeam(team);

        userRepository.save(user);

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("manportapp.info.userCreated",null, locale);

        return ResponseEntity.ok(new GenericResponse(message));
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
        return userRepository.findByEmailAndRowStatus(email, RowStatus.ACTIVE);
    }

    @Override
    public List<UserDTO> getUsersByTeamId(Long id) {
        List<UserDTO> result = new ArrayList<>();
        Team team = new Team();
        team.setId(id);

        List<User> users = userRepository.findAllByRowStatusAndTeam(RowStatus.ACTIVE,team);
        for(User user : users){
            UserDTO userDTO = new UserDTO(user);
            result.add(userDTO);
        }

        return result;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findFirstByIdAndRowStatus(id,RowStatus.ACTIVE);
    }

    @Override
    public User findByIdAndTeamId(Long id, Long teamId) {
        TeamDTO teamDTO = teamService.getById(teamId);
        Team team = new Team();
        team.setId(teamDTO.getId());
        return userRepository.findFirstByIdAndTeamAndRowStatus(id,team,RowStatus.ACTIVE);
    }


}
