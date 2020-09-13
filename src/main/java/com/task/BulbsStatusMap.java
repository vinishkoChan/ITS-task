package com.task;

import com.task.entity.Room;
import com.task.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Iterator;

@Component
public class BulbsStatusMap extends HashMap<Integer, Boolean> {
    @Autowired
    private Service service;

    @PostConstruct
    public void postConstruct(){
        Iterator<Room> iterator = service.getAllRooms().iterator();
        while(iterator.hasNext()){
            Room room = iterator.next();
            this.put(room.getId(), room.isBulbOn());
        }
    }

    @PreDestroy
    public void shutdown(){
        this.forEach((k,v)->{
            Room room = service.getRoomById(k);
            room.setBulb(v);
            service.updateRoom(room);
        });
    }

}
