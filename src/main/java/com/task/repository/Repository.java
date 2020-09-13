package com.task.repository;

import com.task.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Room, Integer> {

    Room findRoomById(int id);

}
