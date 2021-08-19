package com.example.fevo.cameraservice;

import com.example.fevo.model.Camera;

public interface CameraService {

    boolean saveCamera(Camera camera);

    Camera fetchCameraById(int id);
}
