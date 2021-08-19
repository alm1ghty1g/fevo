package com.example.fevo.camerarepository;

import com.example.fevo.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CameraDaoImpl implements CameraDao {


    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "CAM";

    @Override
    public boolean saveCamera(Camera camera) {
        try {
            redisTemplate.opsForHash().put(KEY, camera.getId(), camera);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Camera fetchCameraById(int id) {
        Camera camera;
        camera = (Camera) redisTemplate.opsForHash().get(KEY, id);
        return camera;
    }
}
