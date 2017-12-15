package com.example.rbcapi;

import com.example.rbcapi.model.Robot;
import com.example.rbcapi.model.User;
import com.example.rbcapi.repository.RobotRepository;
import com.example.rbcapi.service.RobotService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RobotServiceTests {

    @Mock
    RobotRepository robotRepository;

    @InjectMocks
    RobotService robotService;

    @Test
    public void getRobotSuccessfully() {
        User user = this.getUser();
        user.setId(1L);

        when(robotRepository.findOne(any(Long.class))).thenReturn(new Robot("title", "description", 19.99, user));

        Robot r = this.robotService.getRobot(1L);

        Assert.assertEquals(r.getTitle(), "title");
        Assert.assertEquals(r.getDescription(), "description");
        Assert.assertEquals(r.getPrice(), 19.99, 0.001);
        Assert.assertEquals(r.getUser().getId(), user.getId());
    }

    private User getUser() {
        return new User();
    }
}
