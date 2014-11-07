package com.springapp.mvc.controller;

import com.springapp.mvc.model.ImageBean;
import com.springapp.mvc.service.ImageService;
import com.springapp.mvc.utils.ImgurUpload;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ImageController {

    private ImageService imageService;

    @Autowired(required=true)
    @Qualifier(value="imageService")
    public void setPersonService(ImageService ps) {
        this.imageService = ps;
    }


    @RequestMapping(value={"/","/index.htm", "/index"})
	public String homePage(ModelMap model) {
        List<ImageBean> images = imageService.listImages();
        model.addAttribute("images", images);
        return "index";
	}

    @RequestMapping(value="/details/{imgID}")
    public ModelAndView getImageDetails(@PathVariable("imgID") String imgID,
                                        ModelMap model) {
        ImageBean imageBean= imageService.getImageByImgId(imgID);
        model.addAttribute("title", imageBean.getTitle());
        model.addAttribute("dimensions", imageBean.getDimensions());
        model.addAttribute("format", imageBean.getFormat());
        model.addAttribute("size", imageBean.getImgsize());
        model.addAttribute("date", imageBean.getStringDate());
        model.addAttribute("imgID", imageBean.getImgID());

        return new ModelAndView("details");
    }

    @RequestMapping(value="search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam String q,
                               ModelMap model) {
        List<ImageBean> images = imageService.findImage(q);
        model.addAttribute("query", q);
        model.addAttribute("images", images);
        return new ModelAndView("results");
    }

    @RequestMapping(value={"/upload","/upload.htm"}, method = RequestMethod.POST)
    public ModelAndView uploadImage(@RequestParam MultipartFile image,
                              @RequestParam String title,
                              @RequestParam(required=false) boolean isPrivate,
                              HttpServletRequest request,
                              ModelMap model) {

        if (!image.isEmpty()) {

            BufferedImage bufImage = null;
            int width = 0;
            int height = 0;
            ImageBean imageBean = new ImageBean();



            try {
                bufImage = ImageIO.read(image.getInputStream());
                width = bufImage.getWidth();
                height = bufImage.getHeight();
                //upload à l'aide de l'api imgur afin d'obtenir une url
                //afin de ne pas manipuler des url locales...
                JSONObject json = ImgurUpload.getImgurResponse(bufImage);
                //récupération de l'id généré
                System.out.println(json);
                if(json.has("id"))
                    imageBean.setImgID(json.optString("id"));


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sizeInKB = (int) (image.getSize() / 1024);
            imageBean.setTitle(title);
            imageBean.setDimensions(width + "x" + height);
            imageBean.setImgsize(sizeInKB);

            String extension = image.getContentType();
            extension = extension.substring(extension.lastIndexOf("/")+1);
            imageBean.setFormat(extension);

            if (isPrivate)
                imageBean.setPrivate(isPrivate);

            imageService.addImage(imageBean);



            //création du lien
            String path = request.getSession().getServletContext().getRealPath("/")
                    + "uploads/"
                    + imageBean.getImgID()
                    + "." + extension;
            File imageDest = new File(path);


            if (!imageDest.exists())
                imageDest.mkdirs();


            model.addAttribute("title", title);
            model.addAttribute("dimensions", imageBean.getDimensions());
            model.addAttribute("format", extension);
            model.addAttribute("size", sizeInKB);


            model.addAttribute("date", imageBean.getStringDate());

            model.addAttribute("imgID", imageBean.getImgID());

            try {
                //upload de l'image en local
                image.transferTo(imageDest);

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return new ModelAndView("details");

    }
}