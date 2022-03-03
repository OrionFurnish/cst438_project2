package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class AccountController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @RequestMapping(value = "/account_settings")
    String account_settings(Model model, @RequestParam Integer userId){
        if(userId == null) {
            model.addAttribute("error_msg", "User not found");
            return "account_settings";
        }

        String uri = BASE_URI + "account_settings" + "?userId=" + userId; //Add params to BASE URI to know which user is logged in
        RestTemplate restTemplate = new RestTemplate(); // Convert response from API into JSON Object compatible with User

        User user = restTemplate.getForObject(uri, User.class); // Converts JSON Object into User Object
        model.addAttribute("user", user); // attribute to later access from html template
        return "account_settings"; // pointing to account settings html template
    }
}
