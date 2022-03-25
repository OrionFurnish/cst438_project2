package com.groupsix.cst438_project02_wishlist;

import com.groupsix.cst438_project02_wishlist.entities.User;
import com.groupsix.cst438_project02_wishlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/admin_create_user")
    String admin_create_user(HttpServletResponse response, HttpSession session) throws IOException {
        check_if_admin(response, session);
        return "admin_create_user";
    }

    @RequestMapping(value = "admin_create_user", method = RequestMethod.POST)
    String admin_create_user(HttpServletResponse response, HttpSession session, Model model,
                             @RequestParam String username, @RequestParam String password, @RequestParam (required = false) boolean admin) throws IOException {
        check_if_admin(response, session);

        User user = new User(username, password);
        user.setAdmin(admin);
        userRepository.save(user);
        response.sendRedirect("admin_create_user?create_success=User+created+successfully.");

        return "admin_create_user";
    }

    @RequestMapping(value = "admin_delete_users")
    String admin_delete_users(HttpServletResponse response, HttpSession session, Model model) throws IOException {
        check_if_admin(response, session);
        model.addAttribute("users", get_user_except_current(session));
        return "admin_delete_users";
    }

    @RequestMapping(value = "admin_delete_users", method = RequestMethod.POST)
    String admin_delete_users_post(HttpServletResponse response, HttpSession session, Model model,
                                   @RequestParam(value = "usersToDelete", required = false) int[] usersToDelete) throws IOException {
        check_if_admin(response, session);
        if(usersToDelete != null) {
            for(int i = 0; i < usersToDelete.length; i++) {
                userRepository.deleteById(usersToDelete[i]);
            }
        }
        model.addAttribute("users", get_user_except_current(session));
        response.sendRedirect("admin_delete_users?delete_success=Users+deleted+successfully.");
        return "admin_delete_users";
    }

    @RequestMapping(value = "/admin_view_users")
    String admin_view_users(HttpServletResponse response, HttpSession session, Model model) throws IOException {
        check_if_admin(response, session);
        model.addAttribute("users", userRepository.findAll());
        return "admin_view_users";
    }

    @RequestMapping(value = "/admin_update_users")
    String admin_update_user(HttpServletResponse response, HttpSession session, Model model) throws IOException {
        check_if_admin(response, session);
        UserForm userForm = new UserForm();
        userForm.setUserList(new ArrayList<>((Collection<User>) userRepository.findAll()));
        model.addAttribute("userForm", userForm);
        return "admin_update_users";
    }

    @RequestMapping(value = "/admin_update_users", method = RequestMethod.POST)
    String admin_update_user(HttpServletResponse response,
                             HttpSession session,
                             Model model,
                             @ModelAttribute UserForm userForm) throws IOException {
        check_if_admin(response, session);
        userRepository.saveAll(userForm.getUserList());
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/admin_view_users";
    }

    ArrayList<User> get_user_except_current(HttpSession session) throws IOException {
        User user = (User)session.getAttribute("User_Session");
        return (ArrayList<User>) userRepository.findAllByUserIdIsNotLike(user.getUserId());
    }

    void check_if_admin(HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User)session.getAttribute("User_Session");
        if(user == null || !user.isAdmin()) {
            response.sendRedirect("/landing");
        }
    }
}
