package com.shaurya.SnapStack.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.shaurya.SnapStack.model.Image;
import com.shaurya.SnapStack.model.User;
import com.shaurya.SnapStack.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    private String prevDate;

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy 'at' hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone("IST"));
        return formatter.format(date);
    }

    @RequestMapping("/User_Images")
    public String userImages(Model model, HttpSession session) {
        if(session.getAttribute("LoggedUser") == null) {
            return "redirect:/login_signup/login";
        } else {
            User user = (User) session.getAttribute("LoggedUser");
            List<Image> images = imageService.getUserImages(user.getId());
            model.addAttribute("userImages", images);
            return "User_Images";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "upload_update/upload")
    public String uploadImg(HttpSession session) {
        if(session.getAttribute("LoggedUser") == null) {
            return "redirect:/login_signup/login";
        } else {
            return "upload_update/upload";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "upload_update/upload")
    public String uploadNewImage(@RequestParam("file") MultipartFile file, Image newImage, HttpSession session) {
        User user = (User) session.getAttribute("LoggedUser");
        newImage.setUser(user);
        newImage.setDate(formatDate(new Date()));
        imageService.uploadImage(newImage, file);
        return "redirect:/User_Images";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateImage")
    public String getImageData(@RequestParam(name = "imageId") Integer imageId, Model model) {
        Image image = imageService.getImgData(imageId);
        prevDate = image.getDate();
        model.addAttribute("imgData", image);
        return "upload_update/update";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateImage")
    public String updateImage(@RequestParam(name = "imageId") Integer imageId, @RequestParam("ufile") MultipartFile ufile, Image updatedImgData, HttpSession session) {
        if(session.getAttribute("LoggedUser") == null) {
            return "redirect:/login_signup/login";
        } else {
            updatedImgData.setId(imageId);
            updatedImgData.setDate(prevDate);
            updatedImgData.setUpdatedDate(formatDate(new Date()));
            User user = (User) session.getAttribute("LoggedUser");
            updatedImgData.setUser(user);
            imageService.updateImgData(updatedImgData, ufile);
            return "redirect:/User_Images";
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteImage")
    public String deleteImage(@RequestParam(name = "imageId") Integer imageId) {
        imageService.deleteImage(imageId);
        return "redirect:/User_Images";
    }
}
