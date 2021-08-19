package com.example.fevo.photorepository;

import com.example.fevo.model.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl implements PhotoDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "PHOTO";

    @Override
    public Photo fetchPhotoById(int id) {
        Photo photo;
        photo = (Photo) redisTemplate.opsForHash().get(KEY, id);
        return photo;
    }

    @Override
    public boolean savePhoto(Photo photo) {
        try {
            redisTemplate.opsForHash().put(KEY, photo.getId(), photo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Photo> fetchAllPhotos() {
        List<Photo> photoList = redisTemplate.opsForHash().values(KEY);
        return photoList;
    }

    @Override
    public void saveAll(List<Photo> photoList) {
        try {
            for (int i = 0; i < photoList.size(); i++) {
                redisTemplate.opsForHash().put(KEY, photoList.get(i).getId(), photoList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
