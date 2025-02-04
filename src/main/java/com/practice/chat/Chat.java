package com.practice.chat;

import java.util.*;
public class Chat {
    public static int userCount = 0;
    public static  int getNewNumber(){
        return userCount++;
    }
    public static HashMap<String, User> members = new HashMap<>();

    public static ArrayList<Message> history = new ArrayList<>();

    public static void addMessage(Message message){
        history.add(message);
    }

    public static void addMember(User user){
        members.put(user.id, user);
    }

    public static class Message {
        private static int totalMessages = 0;

        public String id;

        public String senderID;

        public String text;

        public Message (){
            this.id = "" + ++totalMessages;
        }
        public String senderName(){
            return members.get(this.senderID).name;
        }
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getSenderID() {
            return senderID;
        }
        public void setSenderID(String senderID) {
            this.senderID = senderID;
        }
    }

}
