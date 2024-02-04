package com.myblog.myblog11.repository;

import com.myblog.myblog11.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
