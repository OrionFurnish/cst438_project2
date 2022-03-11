package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    private boolean isValidUrl(String imageUrl) throws IOException {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int rc = connection.getResponseCode();
            connection.disconnect();

            if(rc == HttpURLConnection.HTTP_NOT_FOUND) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/profile_edit")
    String edit_account(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.isNew()) {
            response.sendRedirect("/login");
            return "login_page";
        } else {
            User user = (User) session.getAttribute("User_Session");

            if(isValidUrl(user.getUserImgUrl())) {
                model.addAttribute("ImageValid", true);
            } else {
                model.addAttribute("ImageValid", false);
            }
            model.addAttribute("user", user);
            return "profile_edit";
        }
    }

    @PostMapping(value = "/profile_edit")
    String post_edit_account(HttpServletRequest request,
                             HttpServletResponse response,
                             Model model,
                             @RequestParam String username,
                             @RequestParam String bio,
                             @RequestParam String imageUrl) throws IOException {
        User user = (User) request.getSession().getAttribute("User_Session");

        if(username.isEmpty() || bio.isEmpty() || imageUrl.isEmpty()) {
            model.addAttribute("Error_Msg", "Cannot have empty fields.");
        } else {
            user.setUsername(username);
            user.setUserBio(bio);
            user.setUserImgUrl(imageUrl);
            userRepository.save(user);
            request.getSession().setAttribute("User_Session", user);
            response.sendRedirect("profile_edit?update_success=Profile+updated+successfully.");
        }

        model.addAttribute("user", user);

        return "profile_edit";
    }
}
