package com.ibm.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository < User, Integer > {
//    UserEntity findByEmail(String email);
}
