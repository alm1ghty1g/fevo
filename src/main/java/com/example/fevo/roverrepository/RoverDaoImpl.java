package com.example.fevo.roverrepository;


import com.example.fevo.model.Rover;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoverDaoImpl implements RoverDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String KEY = "ROVER";


    @Override
    public boolean saveRover(Rover rover) {
        try {
            redisTemplate.opsForHash().put(KEY, rover.getId(), rover);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rover fetchRoverById(int id) {
        Rover rover;
        rover = (Rover) redisTemplate.opsForHash().get(KEY, id);
        return rover;
    }
}
