package com.springapp.mvc.service;

import com.springapp.mvc.dao.ImageDAO;
import com.springapp.mvc.model.ImageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDAO imageDAO;


    @Override
    @Transactional
    public void addImage(ImageBean image) {
        imageDAO.addImage(image);
    }

    @Override
    @Transactional
    public void updateImage(ImageBean image) { imageDAO.updateImage(image); }

    @Override
    @Transactional
    public List<ImageBean> findImage(String searchTerm) {
        return imageDAO.findImage(searchTerm);
    }

    @Override
    @Transactional
    public List<ImageBean> listImages() {
        return imageDAO.listImages();
    }

    @Override
    @Transactional
    public ImageBean getImageById(int id) {
        return imageDAO.getImageById(id);
    }

    @Override
    @Transactional
    public ImageBean getImageByImgId(String imgID) {
        return imageDAO.getImageByImgId(imgID);
    }
}
