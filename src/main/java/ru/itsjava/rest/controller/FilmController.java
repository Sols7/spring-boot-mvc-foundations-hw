package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilmController {
    private final UserService userService;

    @GetMapping("/users")
    public String usersPage(Model model) {
        List<User> allUsers = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for(User user: allUsers){
            userDtos.add(UserDto.toDto(user));
        }

        model.addAttribute("users", userDtos);
        return "users-page";
    }

}
