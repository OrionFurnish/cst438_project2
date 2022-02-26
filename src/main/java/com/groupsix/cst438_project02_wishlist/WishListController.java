package com.groupsix.cst438_project02_wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WishListController {

    @GetMapping("/allLists")
    List<String> all() {
        List<String> temp = new ArrayList<>();
        temp.add("random");
        temp.add("stuff");
        return temp;
    }

    public static void main(String[] args) {
        SpringApplication.run(Cst438Project02WishlistApplication.class, args);
    }
}
