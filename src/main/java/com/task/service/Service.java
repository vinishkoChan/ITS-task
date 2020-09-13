package com.task.service;

import com.task.entity.Room;
import com.task.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    @Autowired
    private Repository repository;

    public Iterable<Room> getAllRooms() {

        return repository.findAll();

    }

    public Room getRoomById(int id){
        return repository.findRoomById(id);
    }

    public Room createRoom(String name, String country) {
        Room room = new Room();
        room.setName(name);
        room.setCountry(country);
        room.setBulb(false);
        return repository.save(room);
    }

    public Room updateRoom(Room room){
        return repository.save(room);
    }

}
