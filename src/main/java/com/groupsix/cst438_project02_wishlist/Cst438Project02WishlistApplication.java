package com.groupsix.cst438_project02_wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Cst438Project02WishlistApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Group 06 Wishlist Application!";
    }

    @RequestMapping(value = "/name")
    @ResponseBody
    String name() {
        return "name";
    }


    public static void main(String[] args) {
        SpringApplication.run(Cst438Project02WishlistApplication.class, args);
    }

}
