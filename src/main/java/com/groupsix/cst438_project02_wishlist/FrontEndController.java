package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.models.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *  A class for handling all web requests
 */
@Controller
public class FrontEndController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/landing")
    String landing_page() {
        return "landing_page";
    }


    @RequestMapping(value = "/account_settings")
    String account_settings(Model model, HttpSession session, @RequestParam Integer userId){
        if(userId == null) {
            model.addAttribute("error_msg", "User not found");
            return "account_settings";
        }

        String uri = BASE_URI + "account_settings" + "?userId=" + userId; //Add params to BASE URI to know which user is logged in
        RestTemplate restTemplate = new RestTemplate(); // Convert response from API into JSON Object compatible with User

        User user = restTemplate.getForObject(uri, User.class); // Converts JSON Object into User Object
        model.addAttribute("user", user); // attribute to later access from html template

        // Add user to a session
        session.setAttribute("User_Session", user);

        return "account_settings"; // pointing to account settings html template
    }

    @RequestMapping(value = "/account_settings", method = RequestMethod.POST)
    String account_settings(HttpServletRequest request,
                            @RequestParam Integer userId,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String newPassword,
                            @RequestParam(required = false) boolean deleteAccount) {

        User user = userRepository.findUserById(userId);

        user.setUsername(username);
        user.setPassword(newPassword);
        userRepository.save(user);
        request.getSession().setAttribute("User_Session", user);

        return "account_settings";
    }
}
