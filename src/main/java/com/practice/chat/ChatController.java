package com.practice.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String showChat(String userId,  Model model){
        User user = Chat.members.get(userId);
        if (user == null){
            return "redirect:/login";
        }
        Chat.Message msg = new Chat.Message();
        msg.senderID = user.id;
        model.addAttribute("history", Chat.history);
        model.addAttribute("message", msg);
        model.addAttribute("user", user);
        return "chat";
    }

    @PostMapping("/chat")
    public String sendMessage(@ModelAttribute("msg") Chat.Message msg, Model model){
        User user = Chat.members.get(msg.senderID);
        if (user == null){
            return "redirect:/login";
        }
        Chat.addMessage(msg);
        return "redirect:/chat?userId=" + user.id;
    }

    @GetMapping("/")
    public String goToLogin(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String addMember(Model model){
        model.addAttribute("user", new User("joe" + Chat.getNewNumber()));
        return "login";
    }

    @PostMapping("/login")
    public String addMember(@ModelAttribute User user, Model model){
        Chat.addMember(user);
        model.addAttribute("user", user);
        return "redirect:/chat?userId=" + user.id;
    }

}
