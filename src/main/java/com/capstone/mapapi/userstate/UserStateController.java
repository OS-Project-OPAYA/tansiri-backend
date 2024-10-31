package com.capstone.mapapi.userstate;

import com.capstone.mapapi.start.Start;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userstate")
public class UserStateController {

    @Autowired
    private UserStateService userStateService;

    @PostMapping
    public UserState saveUserState(@RequestBody UserState userState) {
        return userStateService.saveUserState(userState);
    }
}
