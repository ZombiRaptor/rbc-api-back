package com.example.rbcapi;

import com.example.rbcapi.model.User;
import com.example.rbcapi.repository.UserRepository;
import com.example.rbcapi.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUser() throws Exception {
        when(userRepositoryMock.findOne(any(Long.class))).thenReturn(new User("firstname", "lastname", "username", "password"));

        User u = this.userService.getUser(1L);

        Assert.assertEquals(u.getFirstName(), "firstname");
        Assert.assertEquals(u.getLastName(), "lastname");
        Assert.assertEquals(u.getUserName(), "username");
        Assert.assertEquals(u.getPassword(), "password");
    }

}
