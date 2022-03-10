package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login")
    String login() {
        return "login_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String login(HttpServletResponse response,
                 HttpSession session,
                 Model model,
                 @RequestParam String username,
                 @RequestParam String password) throws IOException {
        User user = userRepository.findUserByUsername(username);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                session.setAttribute("User_Session", user);
                response.sendRedirect("/");
            }
        }

        model.addAttribute("Error_Message", "Invalid login, please try again.");
        model.addAttribute("Username", username);
        model.addAttribute("Password", password);
        return "login_page";
    }

    @RequestMapping(value = "/create_account")
    String create_account(Model model) {
        return "create_account";
    }

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    String create_account(HttpServletResponse response,
                          Model model,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String confirmPassword) throws IOException {
        User user = userRepository.findUserByUsername(username);
        if(user == null) {
            if(!username.equals("")) {
                if(password.equals(confirmPassword)) {
                    userRepository.save(new User(username, password));
                    response.sendRedirect("/login?success_message=Account+successfully+created.");
                } else {
                    model.addAttribute("Error_Message", "Passwords do not match.");
                }
            } else {
                model.addAttribute("Error_Message", "Username cannot be empty.");
            }
        } else {
            model.addAttribute("Error_Message", "Username taken.");
        }

        model.addAttribute("Username", username);
        model.addAttribute("Password", password);
        model.addAttribute("Confirm_Password", confirmPassword);
        return "create_account";
    }

}
