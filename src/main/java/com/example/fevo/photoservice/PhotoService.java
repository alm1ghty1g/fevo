package com.example.fevo.photoservice;


import com.example.fevo.model.Photo;

import java.util.List;

public interface PhotoService {

    boolean savePhoto(Photo photo);

    List<Photo> fetchAllPhotos();

    Photo fetchPhotoById(int id);

    void saveAll(List<Photo> photoList);

}
