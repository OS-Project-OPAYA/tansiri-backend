package com.capstone.mapapi.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping
    public Destination createDestination(@RequestBody Destination destination) {
        return destinationService.saveDestination(destination);
    }

}
