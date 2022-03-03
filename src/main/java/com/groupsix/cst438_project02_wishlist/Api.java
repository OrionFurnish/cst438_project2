package com.groupsix.cst438_project02_wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/addUser")
    public @ResponseBody String addUser(@RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userRepository.save(user);

        return "saved";
    }

}
