package com.elyo.userservice.service;

import com.elyo.userservice.dto.UserDto;
import com.elyo.userservice.jpa.UserEentity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEentity> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);

}
