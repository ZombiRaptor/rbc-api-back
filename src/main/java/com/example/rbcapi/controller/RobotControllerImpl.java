package com.example.rbcapi.controller;

import com.example.rbcapi.dto.RobotDTO;
import com.example.rbcapi.model.Robot;
import com.example.rbcapi.service.RobotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RobotControllerImpl implements RobotController {

    @Autowired
    RobotService robotService;

    /**
     * Controller called the robotService to find all robots and returning its mapped robotDTO list
     * @return List<RobotDTO>
     */
    @Override
    @GetMapping("/robots")
    public List<RobotDTO> getAllRobots() {
        ModelMapper modelMapper = new ModelMapper();
        List<Robot> robots = robotService.getAllRobots();
        return robots.stream().map(robot -> modelMapper.map(robot, RobotDTO.class)).collect(Collectors.toList());
    }

    /**
     * Controller called the userService to find
     * @param robotId
     * @return
     */
    // Get robot by id
    @Override
    @GetMapping("/robots/{id}")
    public ResponseEntity<RobotDTO> getRobot(@PathVariable(value = "id") Long robotId) {
        Robot robot = robotService.getRobot(robotId);
        if (robot == null) {
            return ResponseEntity.notFound().build();
        }
        ModelMapper modelMapper = new ModelMapper();

        return ResponseEntity.ok().body(modelMapper.map(robot, RobotDTO.class));
    }

    // Create robot
    @Override
    @PostMapping("/robots")
    public RobotDTO createRobot(@Valid @RequestBody RobotDTO robotDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Robot robot = robotService.createRobot(modelMapper.map(robotDTO, Robot.class));

        return modelMapper.map(robot, RobotDTO.class);
    }

    // Delete robot
    @Override
    @DeleteMapping("/robots/{id}")
    public ResponseEntity<RobotDTO> deleteRobot(@PathVariable(value = "id") Long robotId) {
        Robot robot = robotService.getRobot(robotId);

        if (robot == null) {
            return ResponseEntity.notFound().build();
        }

        robotService.deleteRobot(robotId);
        return ResponseEntity.ok().build();
    }
}
