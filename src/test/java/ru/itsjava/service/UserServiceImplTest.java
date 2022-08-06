package ru.itsjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;
import ru.itsjava.repository.UserRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@Import(UserServiceImpl.class)
public class UserServiceImplTest {

    @Configuration
    static class MyConfiguration {
        Pet pet = new Pet(1L, "testPet1");
        User user1 = new User(1L, "tUser1", 18, pet);
        User user2 = new User(2L, "tUser2", 25, pet);
        List<User> users = List.of(user1, user2);

        @Bean
        public UserRepository userRepositoryMock() {
            UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
            when(mockUserRepository.findAll()).thenReturn(users);
            return mockUserRepository;
        }

        @Bean
        public PetService petServiceMock() {
            return Mockito.mock(PetService.class);
        }

        @Bean
        public UserService userService() {
            return new UserServiceImpl(userRepositoryMock(), petServiceMock());
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    UserRepository userRepositoryMock;

    @Test
    public void getAllUsersTest() {
        Pet pet = new Pet(1L, "testPet1");
        User user1 = new User(1L, "tUser1", 18, pet);
        User user2 = new User(2L, "tUser2", 25, pet);
        List<User> users = List.of(user1, user2);

        when(userRepositoryMock.findAll()).thenReturn(users);
        var actualUsers = userService.getAllUsers();

        Assertions.assertEquals(users, actualUsers);
    }
}
