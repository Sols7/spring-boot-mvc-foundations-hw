package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String usersPage(Model model) {
        List<UserDto> allUsersDto = userService.getAllUsers()
                .stream().map(UserDto::toDto).collect(Collectors.toList());
        model.addAttribute("users", allUsersDto);
        return "users-page";
    }

    @GetMapping("user/add")
    public String addPage() {
        return "add-user-page";
    }

    @PostMapping("user/add")
    public String afterAddPage(UserDto userDto) {
        userService.createUser(UserDto.fromDto(userDto));
        return "redirect:/user";
    }

    @GetMapping("user/{id}/edit")
    public String editPage(@PathVariable("id") long id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("userDto", UserDto.toDto(userById));
        return "edit-user-page";
    }

    @PostMapping("user/{id}/edit")
    public String afterEditPage(UserDto userDto) {
        userService.updateUser(UserDto.fromDto(userDto));
        return "redirect:/user";
    }

    @GetMapping("user/{id}/delete")
    public String deletePage(@PathVariable("id") long id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("userDto", UserDto.toDto(userById));
        return "delete-user-page";
    }

    @PostMapping("user/{id}/delete")
    public String afterDeletePage(User user) {
        userService.deleteUser(user);
        return "redirect:/user";
    }

}
