package com.springapp.mvc.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name = "Image")
public class ImageBean {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private Date uploadDate;

    private String dimensions;

    private Integer imgsize;

    private String format;

    private boolean isPrivate;

    private String imgID;


    public ImageBean() {
        uploadDate = new Date();
        isPrivate = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getStringDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy',' hh:mm a");
        return sdf.format(uploadDate);
    }

    public Integer getImgsize() {
        return imgsize;
    }

    public void setImgsize(int imgSize) {
        this.imgsize = imgSize;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uploadDate=" + uploadDate +
                ", dimensions='" + dimensions + '\'' +
                ", imgSize=" + imgsize +
                ", format='" + format + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
