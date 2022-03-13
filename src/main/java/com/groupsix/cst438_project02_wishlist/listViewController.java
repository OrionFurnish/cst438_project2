package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class listViewController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    ItemRepository itemRepository;
}
