package com.example.fevo.roverservice;

import com.example.fevo.model.Rover;

public interface RoverService {

    boolean saveRover(Rover rover);

    Rover fetchRoverById(int id);
}
