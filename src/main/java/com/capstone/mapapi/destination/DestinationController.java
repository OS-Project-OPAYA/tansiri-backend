package com.capstone.mapapi.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping("/destination")
    public Destination createDestination(@RequestBody Destination destination) {
        return destinationService.saveDestination(destination);
    }
}
