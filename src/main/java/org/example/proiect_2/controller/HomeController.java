package org.example.proiect_2.controller;

import lombok.RequiredArgsConstructor;
import org.example.proiect_2.dto.UserDto;
import org.example.proiect_2.models.RegistrationRequest;
import org.example.proiect_2.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication authentication){
        if(authentication != null){
            UserDto userDto = userService.getLoginUser();
            model.addAttribute("user", userDto);
        }
        model.addAttribute("title", "Home");

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Login");

        return "registration/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("loginError", true);

        return "registration/login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value="registrationSuccess", required = false) String success, Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("registrationSuccess", success);
        model.addAttribute("user", new RegistrationRequest());

        return "registration/register";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegistrationRequest registrationRequest, RedirectAttributes redirectAttributes){

        UserDto userDto = userService.registerUser(registrationRequest);

        redirectAttributes.addAttribute("registrationSuccess", "Success");

        return "redirect:/registration/register";
    }
}
