package com.springapp.mvc.service;

import com.springapp.mvc.model.ImageBean;

import java.util.List;


public interface ImageService {
    public void addImage(ImageBean image);
    public void updateImage(ImageBean image);
    public List<ImageBean> findImage(String searchTerm);
    public List<ImageBean> listImages();
    public ImageBean getImageById (int id);
    public ImageBean getImageByImgId (String imgID);

}
