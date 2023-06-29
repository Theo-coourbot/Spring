package com.tpAnnonce.annonce.service;

import com.tpAnnonce.annonce.Repository.AnnouncementRepository;
import com.tpAnnonce.annonce.entity.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnnoucementService {
    @Autowired
    private AnnouncementRepository _annoucementRepository;


    private CategoryService categoryService;
    private PictureService pictureService;


    public boolean createAnnoucement(Announcement announcement){




        return false;
    }


}
