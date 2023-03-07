package com.elyo.userservice.repository;

import com.elyo.userservice.jpa.UserEentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEentity,Long> {


    UserEentity findByUserId(String id);
    UserEentity findByEmail(String email);

}
