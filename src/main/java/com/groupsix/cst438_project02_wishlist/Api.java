package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/findUserByUsername")
    public @ResponseBody User getUserByName(String username) {
        return userRepository.findUserByName(username);
    }

    @GetMapping(path = "/findUserById")
    public @ResponseBody User getUserById(@RequestParam Integer userId) {
        return userRepository.findUserById(userId);
    }

    @PostMapping(path = "/createUser")
    public @ResponseBody String createUser (@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String bio,
                                            @RequestParam String dob,
                                            @RequestParam String userImgUrl) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserBio(bio);
        user.setUserDob(dob);
        user.setAdmin(false);
        user.setUserImgUrl(userImgUrl);
        userRepository.save(user);

        return "Account create successful";
    }

    @PostMapping(path = "/updateUser")
    public @ResponseBody String updateUser (@RequestParam Integer userId,
                                            @RequestParam String bio,
                                            @RequestParam String dob,
                                            @RequestParam String userImgUrl) {
        User user = userRepository.findUserById(userId);
        user.setUserBio(bio);
        user.setUserDob(dob);
        user.setUserImgUrl(userImgUrl);
        userRepository.save(user);

        return "Profile update successful";
    }

    @GetMapping(path = "/account_settings")
    public @ResponseBody User getEditAccount (@RequestParam Integer userId) {
        return userRepository.findUserById(userId);
    }

    // Not using this mapping for now. Can ignore.
    @PostMapping(path = "/account_settings")
    public @ResponseBody String postEditAccount (@RequestParam Integer userId,
                                                 @RequestParam String username,
                                                 @RequestParam String password,
                                                 @RequestParam String confirmPassword,
                                                 @RequestParam(required = false) boolean deleteAccount) {
        User user = userRepository.findUserById(userId);
        if(user == null) {
            return "User not found";
        }

        if(!Objects.equals(user.getPassword(), password) || !Objects.equals(password, confirmPassword)) {
            return "Incorrect password!";
        } else if (deleteAccount) {
            userRepository.delete(user);
            return "Account successfully deleted";
        } else {
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
            return "Account successfully Updated";
        }
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void emptyParameters(MissingServletRequestParameterException e) {
        String name = e.getParameterName();
        System.out.println(name + " is empty parameter!");
    }
}
