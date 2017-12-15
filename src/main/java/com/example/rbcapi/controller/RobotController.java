package com.example.rbcapi.controller;

import com.example.rbcapi.dto.RobotDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RobotController {

    List<RobotDTO> getAllRobots();

    ResponseEntity<RobotDTO> getRobot(Long robotId);

    RobotDTO createRobot(RobotDTO robotDTO);

    ResponseEntity<RobotDTO> deleteRobot(Long robotId);
}
