package com.example.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.study.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
