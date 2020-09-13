package com.task.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.task.entity.Room;
import com.task.service.IpLocationService;
import com.task.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
public class NonRESTController {
    @Autowired
    private Service service;
    @Autowired
    private Map<Integer, Boolean> bulbsStatusMap;
    @Autowired
    private IpLocationService ipLocationService;

    @GetMapping("/lobby")
    public String lobby(){
        return "lobby";
    }

    @GetMapping("/room")
    public String enterRoom(
            HttpServletRequest httpServletRequest,
            @RequestParam(required = true) int id,
            Model model
    ) throws IOException, GeoIp2Exception {
        String address = httpServletRequest.getRemoteAddr();
        String country = "";
        try {
            country = ipLocationService.getLocationCountry(address);
        }catch (GeoIp2Exception e){
            return "countryError";
        }
        Room room = service.getRoomById(id);

        if(!room.getCountry().toLowerCase().equals(country.toLowerCase())){
            return "countryError";
        }

        model.addAttribute("roomName", room.getName());
        model.addAttribute("bulbStatus", bulbsStatusMap.get(id));
        model.addAttribute("roomId", id);

        return "room";
    }
}
