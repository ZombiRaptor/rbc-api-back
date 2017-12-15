package com.example.rbcapi.repository;

import com.example.rbcapi.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotRepository extends JpaRepository<Robot, Long> {
}