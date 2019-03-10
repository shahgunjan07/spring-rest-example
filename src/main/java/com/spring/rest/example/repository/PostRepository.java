package com.spring.rest.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.example.domain.Post;

public interface PostRepository  extends JpaRepository<Post, Integer>{

}
