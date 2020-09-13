package com.task.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.task.entity.Room;
import com.task.response.Response;
import com.task.service.IpLocationService;
import com.task.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class RESTController {
    @Autowired
    private Service service;
    @Autowired
    private IpLocationService ipLocationService;
    @Autowired
    private Map<Integer, Boolean> bulbsStatusMap;

    @GetMapping("/rooms")
    public
    Iterable<Room> getAllRooms() throws IOException, GeoIp2Exception {
        return service.getAllRooms();
    }

    @PostMapping("/rooms")
    Response createRoom(
            @RequestParam String name,
            @RequestParam String country
    ) {
        Room room = service.createRoom(name, country);
        bulbsStatusMap.put(room.getId(), room.isBulbOn());
        return new Response(room);
    }

    @GetMapping("/room_check")
    public Response getBulbStatus(
            @RequestParam(required = true) int id
    ) {

        return new Response(bulbsStatusMap.get(id));
    }

    @PostMapping("/room_check")
    public Response switchBulb(
            @RequestParam(required = true) int id
    ) {
        bulbsStatusMap.replace(id, !bulbsStatusMap.get(id));

        return new Response(bulbsStatusMap.get(id));
    }
}
