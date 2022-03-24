package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.Item;
import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.entities.Wishlist;
import com.groupsix.cst438_project02_wishlist.repositories.ItemRepository;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import com.groupsix.cst438_project02_wishlist.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class listViewController {

    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/edit_wishlist")
    String view_item_list(@RequestParam Integer wishlistId, Model model) throws IOException {
        model.addAttribute("WishlistID", wishlistId);
        return "edit_wishlist";
    }

    @RequestMapping(value = "/add_item")
    String addItem(@RequestParam Integer wishlistId, Model model) {
        model.addAttribute("WishlistID", wishlistId);
        return "add_item";
    }

    @RequestMapping(value = "/add_item", method = RequestMethod.POST)
    void addItem (HttpServletResponse response,
                    @RequestParam Integer wishlistId,
                    @RequestParam String itemUrl,
                    @RequestParam String itemImgUrl,
                    @RequestParam String itemName,
                    @RequestParam String itemDetails) throws Exception {
        Item item = new Item();

        item.setItemUrl(itemUrl);
        item.setItemImgUrl(itemImgUrl);
        item.setItemName(itemName);
        item.setItemDetails(itemDetails);
        item.setWishlistId(wishlistId);

        itemRepository.save(item);

        response.sendRedirect("edit_wishlist?wishlistId="+wishlistId);
    }
}
