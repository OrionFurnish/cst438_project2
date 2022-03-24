package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.Item;
import com.groupsix.cst438_project02_wishlist.repositories.ItemRepository;
import com.groupsix.cst438_project02_wishlist.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewWishlistController {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/view_wishlist")
    String view_item_list(@RequestParam Integer wishlistId, Model model) {
        List<Item> items = itemRepository.findItemByWishlistId(wishlistId);
        model.addAttribute("items", items);
        return "view_wishlist";
    }
}
