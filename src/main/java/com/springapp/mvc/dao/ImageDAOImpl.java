package com.springapp.mvc.dao;

import com.springapp.mvc.model.ImageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("imageDAO")
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addImage(ImageBean image) {
        sessionFactory.getCurrentSession().persist(image);
    }

    public void updateImage(ImageBean image) {
        sessionFactory.getCurrentSession().update(image);
    }



    @Override
    public List<ImageBean> findImage(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ImageBean WHERE isPrivate = false AND lower(title) like :title ORDER BY uploadDate DESC ");
        query.setParameter("title", '%' + searchTerm + '%');
        List<ImageBean> imagesList = query.list();

        return imagesList;
    }


    @Override
    public List<ImageBean> listImages() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ImageBean WHERE isPrivate = false ORDER BY uploadDate DESC ");
        //seuls les 16 premiers résultats sont pris, pour afficher les dernières images ajoutées à la page d'accueil
        List<ImageBean> imagesList = query.setMaxResults(16).list();

        return imagesList;
    }

    @Override
    public ImageBean getImageById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (ImageBean) session.get(ImageBean.class,id);
    }

    @Override
    public ImageBean getImageByImgId (String imgID) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM ImageBean WHERE imgID = :imgID");
        query.setParameter("imgID", imgID);
        List<ImageBean> imagesList = query.setMaxResults(1).list();
        return (ImageBean) imagesList.get(0);
    }

}
