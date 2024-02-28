package org.example.proiect_2.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.RoleDto;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.RegistrationRequest;
import org.example.proiect_2.models.User;
import org.example.proiect_2.service.RoleService;
import org.example.proiect_2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/users/read")
    public String getUsers(Model model){
        List<UserDto> users = userService.getAllUsers();
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Users");
        model.addAttribute("users", users);
        model.addAttribute("user", userDto);
        return "admin";
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @GetMapping("/createUser")
    public String renderCreateUserForm(@RequestParam(value="registrationSuccess", required = false) String success, Model model){
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Create User");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());
        model.addAttribute("userr", userDto);
        return "admin/create";
    }
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes){
        UserDto userDto = userService.registerUser(registrationRequest);

        redirectAttributes.addAttribute("registrationSuccess", "Success");
        return "redirect:/admin/users/read";
    }

    @GetMapping("/deleteUser")
    public String renderDeleteUserForm(Model model){
        List<UserDto> users = userService.getAllUsers();
        UserDto userDto = userService.getLoginUser();
        model.addAttribute("title", "Delete Users");
        model.addAttribute("users", users);
        model.addAttribute("user", userDto);
        return "admin/delete";
    }

    @PostMapping("/deleteUser")
    public String deleteUsers(@RequestParam int[] selectedUsers) {
        if (selectedUsers != null && selectedUsers.length > 0) {
            for (int userId : selectedUsers) {
                userService.deleteUser(userId);
            }
        }
        return "redirect:/admin/users/read";
    }

    @GetMapping("/updateUser/{id}")
    public String renderUpdateUserForm(@PathVariable("id") Integer id, Model model) {
        RoleDto roleDto = roleService.getRoleById(id);
        List<UserDto> users = userService.getAllUsers();
        List<UserDto> nonAdminUsers = new ArrayList<>();
        for(UserDto u : users){
            if (!u.roles().contains(roleDto)) {
                nonAdminUsers.add(u);
            }
        }
        model.addAttribute("users", nonAdminUsers);
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("title", "Update User");
        model.addAttribute("user", userDto);
        model.addAttribute("id", id);

        return "admin/update";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @RequestParam(value = "selectedUsers", required = false) int[] selectedUsers,
                             @ModelAttribute("user") RegistrationRequest registrationRequest) {
        if (selectedUsers != null && selectedUsers.length > 0) {
            for (int userId : selectedUsers) {
                userService.updateUser(userId, registrationRequest);
            }
        } else {
            userService.updateUser(id, registrationRequest);
        }

        return "redirect:/admin/users/read";
    }


}
