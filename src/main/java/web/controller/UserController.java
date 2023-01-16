package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.sevice.UserService;


@Controller
public class UserController {

private final UserService userService;

@Autowired
public UserController(UserService userService) {
    this.userService = userService;
}

@GetMapping()
public String showAllUser(Model model) {
    model.addAttribute("users", userService.findAll());
    return "/showAllUsers";
}

@GetMapping("/{id}")
public String showOneUser(@PathVariable long id, Model model) {
    model.addAttribute("user", userService.findById(id));
    return "/showOneUser";
}


@GetMapping("/new")
public String newUser(@ModelAttribute("user") User user) {
    return "/newUser";
}

@PostMapping()
public String createUser(@ModelAttribute User user) {
    userService.saveUser(user);
    return "redirect:/";
}

@GetMapping("/{id}/edit")
public String edit(Model model, @PathVariable("id") long id) {
    model.addAttribute("user", userService.findById(id));
    return "/editUser";
}

@PatchMapping("/{id}")
public String updateUser(@ModelAttribute("user") User user, @PathVariable long id) {
    userService.update(id, user);
    return "redirect:/";
}

@DeleteMapping("/{id}")
public String deleteUser(@PathVariable("id") long id) {
    userService.deleteUser(id);
    return "redirect:/";
}
}