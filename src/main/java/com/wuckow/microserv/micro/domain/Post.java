package com.wuckow.microserv.micro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


    @Entity
    public class Post {

        @Id
        @GeneratedValue
        private Long id;

        private String description;

        @ManyToOne(fetch = FetchType.LAZY)
        @JsonIgnore
        private User user;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        @Override
        public String toString() {
            return String.format("Post [id=%s, description=%s]", id, description);
        }
    }


