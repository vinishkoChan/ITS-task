package com.task.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

@Component
public class IpLocationService {
    private DatabaseReader dbReader;

    public IpLocationService() throws IOException {
        InputStream database = new ClassPathResource("GeoLite2-Country.mmdb").getInputStream();
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public String getLocationCountry(String address) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(address);
        return dbReader.country(ipAddress).getCountry().getName();
    }
}
