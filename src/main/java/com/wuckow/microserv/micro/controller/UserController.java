package com.wuckow.microserv.micro.controller;

import com.wuckow.microserv.micro.domain.Post;
import com.wuckow.microserv.micro.domain.User;
import com.wuckow.microserv.micro.repo.PostRepo;
import com.wuckow.microserv.micro.repo.UserRepo;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepo userRepo;

    private PostRepo postRepo;

    public UserController(UserRepo userRepo, PostRepo postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }





    @GetMapping("/users")
    public List<User> retriveAllUsers(){

        return userRepo.findAll();

    }

    @GetMapping("/users/{id}")
    public Resource<User> returnOneuser(@PathVariable Long id)  {

        Optional<User>user = userRepo.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("User with id " + id + " does not exist");

        Resource<User> userResource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retriveAllUsers());

        userResource.add(linkTo.withRel("all-users"));

        return userResource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        User savedUser = userRepo.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {

       userRepo.deleteById(id);


    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retriveAllUsersPosts(@PathVariable long id){

        Optional<User> userOptional = userRepo.findById(id);

        if (!userOptional.isPresent())
        {throw new UserNotFoundException("id -" + id);}

        return userOptional.get().getPosts();

    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> saveUser(@PathVariable long id,@RequestBody Post post){

        Optional<User> userOptional = userRepo.findById(id);

        if (!userOptional.isPresent())
        {throw new UserNotFoundException("id -" + id);}

        User user = userOptional.get();

        post.setUser(user);

        postRepo.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }



}
