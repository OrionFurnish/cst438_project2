package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/findUserByName")
    public @ResponseBody User getUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @GetMapping(path = "/findUserById")
    public @ResponseBody User getUserById(@RequestParam int userId) {
        return userRepository.findUserById(userId);
    }

    @PostMapping(path = "/createUser")
    public @ResponseBody String createUser (@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String bio,
                                            @RequestParam String dob) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserBio(bio);
        user.setUserDob(dob);
        user.setAdmin(false);
        userRepository.save(user);

        return "Account create successful";
    }

    @PostMapping(path = "/updateUser")
    public @ResponseBody String updateUser (@RequestParam int userId,
                                            @RequestParam String userName,
                                            @RequestParam String userPassword,
                                            @RequestParam String userBio,
                                            @RequestParam String userDob) {
        User user = userRepository.findUserById(userId);
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setUserBio(userBio);
        user.setUserDob(userDob);
        userRepository.save(user);

        return "User update successful";
    }

}
