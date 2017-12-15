package com.example.rbcapi.service;

import com.example.rbcapi.dto.RobotDTO;
import com.example.rbcapi.model.Robot;
import com.example.rbcapi.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotService {

    @Autowired
    protected RobotRepository robotRepository;

    /**
     * Service called the robotRepository to find all robots and returning its mapped robot list
     *
     * @return List<Robot>
     */
    public List<Robot> getAllRobots() {
        return robotRepository.findAll();
    }

    /**
     * Service called the robotRepository to find the robot and return its robot
     *
     * @param robotId id of the robot to find
     * @return Robot
     */
    public Robot getRobot(Long robotId) {
        return robotRepository.findOne(robotId);
    }

    /**
     * Service create a robot by mapping robot to robot and calling robotService create method
     *
     * @param robot robot to create
     * @return Robot
     */
    public Robot createRobot(Robot robot) {
        return robotRepository.save(robot);
    }

    /**
     * Service called the repository to delete the robot by its id
     *
     * @param robotId id of the robot to delete
     */
    public void deleteRobot(Long robotId) {
        robotRepository.delete(robotId);
    }
}
