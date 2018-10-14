package com.wuckow.microserv.micro.domain;

import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {


    @Id
    @GeneratedValue
    private Long id;

    public User(@NotNull @Size(min = 2, message = "Enter at least 2 Characters.") String name, List<Post> posts, Date birthDate) {
        this.name = name;
        this.posts = posts;
        this.birthDate = birthDate;
    }

    @NotNull
    @Size(min = 2, message = "Enter at least 2 Characters.")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    private Date birthDate;

    public User() {

    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(Long id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
    }
}
