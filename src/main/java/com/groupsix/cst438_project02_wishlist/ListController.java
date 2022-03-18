package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.entities.Wishlist;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import com.groupsix.cst438_project02_wishlist.repositories.WishlistRepository;
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

@Controller
public class ListController {

    public static String BASE_URI = "http://localhost:8080/api/";

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/create_wishlist")
    String wishlist_create(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession(false);

        //Wishlist wishlist = wishlistRepository.findWishlistById(((User) request.getSession().getAttribute("User_Session")).getUserId());

        return "create_wishlist";
    }

    @RequestMapping(value = "/create_wishlist", method = RequestMethod.POST)
    String wishlist(HttpServletRequest request,
                    HttpServletResponse response,
                    Model model,
                    @RequestParam String listName)throws IOException {
        User user = ((User) request.getSession().getAttribute("User_Session"));
        Wishlist wishlist = new Wishlist();

        if(!listName.isEmpty()){
            wishlist.setUserId(user.getUserId());
            wishlist.setListName(listName);
            wishlistRepository.save(wishlist);
            response.sendRedirect("/");
        }
        else{
            model.addAttribute("Error_Msg", "Have to Name the Wishlist");
        }
        return "create_wishlist";
    }

}
