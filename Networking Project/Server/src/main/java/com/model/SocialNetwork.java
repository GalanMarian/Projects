package com.model;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    static List<Person> socialNetwork = new ArrayList<>();

    public static void addPerson(Person person)
    {
        socialNetwork.add(person);
    }

    public static boolean verifyPerson(String name)
    {
        for(Person person:socialNetwork)
        {
            if(person.getName().equals(name))
                return true;
        }
        return false;
    }

    public static Person getPersonByName(String name)
    {
        for(Person person:socialNetwork)
        {
            if(person.getName().equals(name))
                return person;
        }
        return null;
    }

    public static String getNameBySocket(Socket socket)
    {

        for(Person person:socialNetwork)
        {

            if(person.getSocket()!=null && person.getSocket().getPort()==socket.getPort())
                return person.getName();
        }
        return null;
    }

    public static List<Person> getSocialNetwork() {
        return socialNetwork;
    }
}
