package com.example.fevo.photorepository;

import com.example.fevo.model.Photo;

import java.util.List;

public interface PhotoDao {

    boolean savePhoto(Photo photo);

    Photo fetchPhotoById(int id);

    List<Photo> fetchAllPhotos();

    void saveAll(List<Photo> photoList);

}
