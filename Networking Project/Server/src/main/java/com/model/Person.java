package com.model;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Person> friends = new ArrayList<>();
    private List<String> messages= new ArrayList<>();
    private Socket socket;
    private boolean isLogged;

    public Person(String name) {
        this.name = name;
    }

    public void addMessage(String message)
    {
        messages.add(message);
    }

    public void addFriend(Person friend)
    {
        friends.add(friend);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", socket=" + socket +
                '}';
    }
}
