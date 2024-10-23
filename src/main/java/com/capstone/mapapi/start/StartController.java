package com.capstone.mapapi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StartController {

    @Autowired
    private StartService startService;

    // 안드로이드에서 데이터를 받을 POST 엔드포인트
    @PostMapping("/start")
    public Start createStart(@RequestBody Start start) {
        return startService.saveStart(start);
    }
}
