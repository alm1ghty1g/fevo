package com.example.fevo.controller;

import com.example.fevo.implementation.PhotoServiceApiImplementation;
import com.example.fevo.model.Photo;
import com.example.fevo.photoservice.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {


    @Autowired
    private PhotoServiceApiImplementation photoServiceApiImplementation;

    @Autowired
    private PhotoService photoService;


    @GetMapping("/get")
    public void getPhoto() {
        photoServiceApiImplementation.apiRunner();
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<Photo>> fetchPhotos() {
        List<Photo> photoList;
        photoList = photoService.fetchAllPhotos();
        return ResponseEntity.ok(photoList);
    }

    @PostMapping("/save")
    public ResponseEntity<Photo> savePhoto(@RequestBody Photo photo) {
        boolean result = photoService.savePhoto(photo);
        if(result)
            return ResponseEntity.ok(photo);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Photo> fetchById(@PathVariable("id") int id){
        Photo photo;
        photo = photoService.fetchPhotoById(id);
        return ResponseEntity.ok(photo);
    }



}
