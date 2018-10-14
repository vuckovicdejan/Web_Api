package com.wuckow.microserv.micro.repo;

import com.wuckow.microserv.micro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
