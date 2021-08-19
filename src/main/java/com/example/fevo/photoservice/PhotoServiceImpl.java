package com.example.fevo.photoservice;

import com.example.fevo.model.Photo;
import com.example.fevo.photorepository.PhotoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao photoDao;

    @Override
    public boolean savePhoto(Photo photo) {
        return photoDao.savePhoto(photo);
    }

    @Override
    public Photo fetchPhotoById(int id) {
        return photoDao.fetchPhotoById(id);
    }

    @Override
    public List<Photo> fetchAllPhotos() {
        return photoDao.fetchAllPhotos();
    }

    @Override
    public void saveAll(List<Photo> photoList) {
         photoDao.saveAll(photoList);
    }
}
