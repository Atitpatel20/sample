package com.myblog.myblog11.repository;

import com.myblog.myblog11.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
