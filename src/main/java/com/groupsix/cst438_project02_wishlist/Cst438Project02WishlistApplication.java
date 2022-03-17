package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@SpringBootApplication
public class Cst438Project02WishlistApplication {

    @RequestMapping("/")
     String home(HttpSession session, HttpServletResponse response, Model model) throws IOException {
        User user = (User)session.getAttribute("User_Session");
        if(user == null) {
            response.sendRedirect("/landing");
        }
        model.addAttribute("user", user);
        return "home";
    }

    @RequestMapping(value = "/name")
    @ResponseBody
    String name() {
        return "name";
    }

    @RequestMapping(value = "/landing")
    String landing_page() {
        return "landing_page";
    }


    public static void main(String[] args) {
        SpringApplication.run(Cst438Project02WishlistApplication.class, args);
    }

}
