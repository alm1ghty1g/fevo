package com.example.fevo.cameraservice;

import com.example.fevo.camerarepository.CameraDao;
import com.example.fevo.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraDao cameraDao;

    @Override
    public boolean saveCamera(Camera camera) {
        return cameraDao.saveCamera(camera);
    }

    @Override
    public Camera fetchCameraById(int id) {
        return cameraDao.fetchCameraById(id);
    }
}
