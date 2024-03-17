package com.shaurya.SnapStack.controller;

import com.shaurya.SnapStack.model.User;
import com.shaurya.SnapStack.model.UserProfile;
import com.shaurya.SnapStack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/login_signup/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login_signup/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login_signup/login")
    public String loginUser(User user, HttpSession session) {
        User existingUser = userService.login(user);
        if(existingUser == null) {
            System.out.println("User Doesn't Exist!!!");
            return "login_signup/login";
        }
        else {
            session.setAttribute("LoggedUser", existingUser);
            session.setAttribute("usr", existingUser.getUsername());
            System.out.println("User Found!!!");
            return "redirect:/User_Images";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login_signup/signup")
    public String signup(Model model) {
        User user = new User();
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        model.addAttribute("user", user);
        return "login_signup/signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login_signup/signup")
    public String signupUser(User user) {
        userService.signupUser(user);
        return "redirect:/login_signup/login";
    }

    @RequestMapping(value = "logout")
    public String userLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
