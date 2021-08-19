package com.example.fevo.camerarepository;

import com.example.fevo.model.Camera;

public interface CameraDao {

    boolean saveCamera(Camera camera);

    Camera fetchCameraById(int id);
}
