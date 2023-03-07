package com.elyo.userservice.service;

import com.elyo.userservice.dto.UserDto;
import com.elyo.userservice.jpa.UserEentity;
import com.elyo.userservice.repository.UserRepository;
import com.elyo.userservice.vo.ResponseOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService  {


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(new BCryptPasswordEncoder().encode(userDto.getPwd()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEentity userEntity = modelMapper.map(userDto, UserEentity.class);

        userRepository.save(userEntity);

        UserDto returnUserDto = modelMapper.map(userEntity, UserDto.class);
        return returnUserDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEentity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public Iterable<UserEentity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEentity byEmail = userRepository.findByEmail(email);

        if(byEmail == null) throw new UsernameNotFoundException("user not found");

        return new ModelMapper().map(byEmail, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserEentity> byUserId = Optional.ofNullable(userRepository.findByEmail(username));
        UserEentity userEentity = userRepository.findByEmail(username);

        if(userEentity == null) throw new UsernameNotFoundException("user not found");
        return new User(userEentity.getEmail(), userEentity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }
}
