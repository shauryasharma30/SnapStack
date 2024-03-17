package com.shaurya.SnapStack.service;

import com.shaurya.SnapStack.model.Image;
import com.shaurya.SnapStack.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AmazonS3Service amazonS3Service;

    public void uploadImage(Image newImage, MultipartFile file) {
        try {
            log.info("In service -> Uploading file : {}", newImage.getTitle());
            String s3Link = amazonS3Service.uploadFile(file);
            newImage.setImageFile(s3Link);
            imageRepository.createImage(newImage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String convertImgToBase64(MultipartFile file) throws IOException {
        return Base64.getEncoder().encodeToString(file.getBytes());
    }

    public List<Image> getAllImages() {
        return imageRepository.getAllImages();
    }

    public List<Image> getUserImages(Integer userId) {
        return imageRepository.getUserImages(userId);
    }

    public Image getImgData(Integer imageId) {
        return imageRepository.getImgData(imageId);
    }

    public void updateImgData(Image updatedImgData, MultipartFile ufile) {
        try {
            log.info("In service -> Updating & Uploading New Image file : {}", updatedImgData.getTitle());
            String s3Link = amazonS3Service.uploadFile(ufile);
            updatedImgData.setImageFile(s3Link);
            imageRepository.updateImgData(updatedImgData);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void deleteImage(Integer imageId) {
        log.info("Deleting Image with ID : {}", imageId);
        imageRepository.deleteImage(imageId);
    }
}