package com.practice.chat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ApiController {
    @GetMapping("/history")
    public ArrayList<Chat.Message> getHistory(){
        return Chat.history;
    }

    @GetMapping("/name")
    public String returnName(@RequestParam String userId) {
        return Chat.members.get(userId).name;
    }

}
