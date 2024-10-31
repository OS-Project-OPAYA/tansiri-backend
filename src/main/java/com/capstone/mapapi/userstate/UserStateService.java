package com.capstone.mapapi.userstate;

import com.capstone.mapapi.start.Start;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStateService {

    @Autowired
    private UserStateRepository userStateRepository;

    public UserState saveUserState(UserState userState) {
        return userStateRepository.save(userState); // Start 객체를 그대로 저장
    }
}
