package com.example.fevo.roverservice;

import com.example.fevo.model.Rover;
import com.example.fevo.roverrepository.RoverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImpl implements RoverService{

    @Autowired
    private RoverDao roverDao;

    @Override
    public boolean saveRover(Rover rover) {
        return false;
    }

    @Override
    public Rover fetchRoverById(int id) {
        return null;
    }
}
