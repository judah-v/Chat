package com.practice.chat;

import java.util.UUID;
public class User {
    public String id;
    public String name;

    public User(String name){
        this.id = Double.toString(Math.random());
        this.name = name;
//        System.out.println("\n\n\nNEW USER CREATED\n\n\n");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

}
