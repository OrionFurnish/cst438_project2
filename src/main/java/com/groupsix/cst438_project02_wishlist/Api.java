package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class Api {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/findUserByUsername")
    public @ResponseBody User getUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @GetMapping(path = "/findUserById")
    public @ResponseBody User getUserById(@RequestParam Integer userId) {
        return userRepository.findUserById(userId);
    }

    @GetMapping(path = "/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping(path = "/users")
    public ResponseEntity<User> adminCreateUser(@RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String bio,
                                                @RequestParam String dob,
                                                @RequestParam String userImgUrl,
                                                @RequestParam (required = false) boolean isAdmin) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserBio(bio);
        user.setUserDob(dob);
        user.setUserImgUrl(userImgUrl);
        user.setAdmin(isAdmin);
        userRepository.save(user);

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(path = "/users")
    public @ResponseBody String adminDeleteUser (@RequestParam String username,
                                                 @RequestParam String password,
                                                 @RequestParam String confirmPassword) {
        User user = userRepository.findUserByUsername(username);

        if(user == null) {
            return "User doesn't exist";
        } else if (!password.equals(confirmPassword)) {
            return "Password incorrect";
        } else if (user.getUsername().equals(username)) {
            userRepository.delete(user);
            return "User deleted";
        }
        return "User not deleted";
    }

    @PatchMapping(path = "/users")
    public @ResponseBody String adminUpdateUser (@RequestParam String username,
                                                 @RequestParam String password,
                                                 @RequestParam String bio,
                                                 @RequestParam String dob,
                                                 @RequestParam String userImgUrl,
                                                 @RequestParam (required = false) boolean isAdmin) {
        User user = userRepository.findUserByUsername(username);

        if(user == null) {
            return "User doesn't exist";
        } else {
            user.setPassword(password);
            user.setUserBio(bio);
            user.setUserDob(dob);
            user.setUserImgUrl(userImgUrl);
            user.setAdmin(isAdmin);
            userRepository.save(user);
            return "User: " + username + " was updated";
        }
    }

    @PostMapping(path = "/newuser")
    public @ResponseBody String newUser (@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam String bio,
                                         @RequestParam String dob,
                                         @RequestParam String userImgUrl) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserBio(bio);
        user.setUserDob(dob);
        user.setUserImgUrl(userImgUrl);
        user.setAdmin(false);
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

    @PostMapping(path = "/account_settings")
    public @ResponseBody String postEditAccount (@RequestParam Integer userId,
                                                 @RequestParam String username,
                                                 @RequestParam String password,
                                                 @RequestParam String confirmPassword,
                                                 @RequestParam(required = false) boolean deleteAccount) {
        User user = userRepository.findUserById(userId);
        if(user == null) {
            return "User not found";
        } else if(!password.equals(user.getPassword()) || !password.equals(confirmPassword)) {
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
