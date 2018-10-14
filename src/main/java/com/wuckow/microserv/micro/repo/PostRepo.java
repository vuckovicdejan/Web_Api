package com.wuckow.microserv.micro.repo;

import com.wuckow.microserv.micro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
