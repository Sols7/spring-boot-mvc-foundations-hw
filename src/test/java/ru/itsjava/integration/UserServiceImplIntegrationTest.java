package ru.itsjava.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.User;
import ru.itsjava.repository.UserRepository;
import ru.itsjava.service.UserService;
import ru.itsjava.service.UserServiceImpl;

import java.util.NoSuchElementException;

@DataJpaTest
@Import(UserServiceImpl.class)
public class UserServiceImplIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void shouldHaveCorrectGetAllUsers() {
        var expectedUsers = userRepository.findAll();
        var actualUsers = userService.getAllUsers();

        Assertions.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void shouldHaveCorrectCreateUser() {
        var createUser = new User(3L, "createUser", 25, userService.getUserById(1L).getPet());
        userService.createUser(createUser);
        var actualUser = userService.getUserById(3L);

        Assertions.assertEquals(createUser, actualUser);
    }

    @Test
    public void shouldHaveCorrectGetUserById() {
        var expectedUser = userRepository.findById(1L).get();
        var actualUser = userService.getUserById(1L);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldHaveCorrectUpdateUser() {
        var updateUser = userService.getUserById(1L);
        updateUser.setName("updateUser");
        userService.updateUser(updateUser);
        var actualUser = userService.getUserById(1L);

        Assertions.assertEquals("updateUser", actualUser.getName());
    }

    @Test
    public void shouldHaveCorrectDeleteUser() {
        NoSuchElementException elementException = Assertions.assertThrows(NoSuchElementException.class, () -> {
            userService.deleteUser(userService.getUserById(3L));
            var deletedUser = userService.getUserById(3L);

            Assertions.assertNull(deletedUser);
        });

        Assertions.assertEquals("No value present", elementException.getMessage());
    }
}