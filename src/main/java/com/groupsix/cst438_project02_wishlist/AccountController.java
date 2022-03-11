package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *  A class for handling all web requests
 */
@Controller
public class AccountController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/account_settings")
    String account_settings(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession(false);

        // Redirect if user is not logged in
        if (session == null || session.isNew()) {
            response.sendRedirect("/login");
            return "login_page";
        } else {
            model.addAttribute("username", ((User) session.getAttribute("User_Session")).getUsername());
            return "account_settings";
        }
    }

    @RequestMapping(value = "/account_settings", method = RequestMethod.POST)
    String account_settings(HttpServletRequest request,
                            HttpServletResponse response,
                            Model model,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam(required = false) String newPassword,
                            @RequestParam(required = false) boolean deleteAccount) throws IOException {
        User user = (User) request.getSession().getAttribute("User_Session");

        if (deleteAccount) {
            if(user.getPassword().equals(password)) {
                userRepository.delete(user);
                response.sendRedirect("/landing");
                request.getSession().invalidate();
                return "landing_page";
            } else {
                model.addAttribute("Error_Msg", "Requires current password to delete");
            }

        } else {
            if(!username.isEmpty()) {
                if (user.getPassword().equals(password)) {
                    user.setUsername(username);

                    if(!newPassword.isEmpty()) {
                        user.setPassword(newPassword);
                    }

                    userRepository.save(user);
                    request.getSession().setAttribute("User_Session", user);
                    model.addAttribute("username", user.getUsername());
                    response.sendRedirect("/account_settings?update_success=Account+updated+successfully.");
                } else {
                    model.addAttribute("Error_Msg", "Incorrect current password");
                }
            } else {
                model.addAttribute("Error_Msg", "Can't have empty username");
            }
        }

        model.addAttribute("username", user.getUsername());
        return "account_settings";
    }
}
