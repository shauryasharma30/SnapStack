package com.shaurya.SnapStack.controller;

import com.shaurya.SnapStack.model.Image;
import com.shaurya.SnapStack.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/")
    public String Home(Model model)
    {
        List<Image> images = imageService.getAllImages();
        model.addAttribute("allImages", images);
        return "index";
    }

    @RequestMapping(value = "/upload_update/update")
    public String tempFnx(HttpSession session) {
        if(session.getAttribute("LoggedUser") == null) {
            return "redirect:/login_signup/login";
        } else {
            return "redirect:/User_Images";
        }
    }
}