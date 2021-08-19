package com.example.fevo.roverrepository;

import com.example.fevo.model.Rover;

public interface RoverDao {

    boolean saveRover(Rover rover);

    Rover fetchRoverById(int id);
}
