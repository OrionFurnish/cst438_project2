package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.Item;
import com.groupsix.cst438_project02_wishlist.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class ItemController {
    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/item_view")
    String item_view(Model model, HttpSession session, @RequestParam Integer userId){
        if(userId == null){
            model.addAttribute("error_msg", "User not found");
            return "item_view";
        }

        String uri = BASE_URI + "item_view" + "?userId=" + userId;
        RestTemplate restTemplate = new RestTemplate();

        Item item = restTemplate.getForObject(uri, Item.class);
        model.addAttribute("items", item);

        session.setAttribute("Item_session", item);

        return "item_view";
    }

}
