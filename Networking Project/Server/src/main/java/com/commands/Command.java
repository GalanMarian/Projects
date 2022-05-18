package com.commands;

import com.model.Person;
import com.model.SocialNetwork;

import java.net.Socket;
import java.util.List;

public class Command {


    public static void register(String name)
    {
        Person tempPerson= new Person(name);
        SocialNetwork.addPerson(tempPerson);
    }
    public static boolean login(String name, Socket socket)
    {
        if(SocialNetwork.verifyPerson(name))
        {
            Person person = SocialNetwork.getPersonByName(name);
            person.setSocket(socket);
            person.setLogged(true);
            return true;
        }
        return false;
    }

    public static boolean friend(String nameOfPerson, List<String> friends)
    {

        Person person= SocialNetwork.getPersonByName(nameOfPerson);
        if(person!=null && person.isLogged())
        {
            for(String friend:friends)
            {
                Person tempPerson = SocialNetwork.getPersonByName(friend);
                if(tempPerson!=null)
                {
                    person.addFriend(tempPerson);
                    tempPerson.addFriend(person);
                }
            }
            return true;
        }
        return false;
    }

    public static boolean sendMessage(String nameOfPerson, String message)
    {
        Person person= SocialNetwork.getPersonByName(nameOfPerson);
        if(person!=null && person.isLogged()) {
            for (Person friend : person.getFriends()) {
                friend.addMessage(message);
            }
            return true;
        }
        return false;
    }
    public static List<String> read(String name)
    {
        Person person= SocialNetwork.getPersonByName(name);
        if(person!=null && person.isLogged())
        return person.getMessages();
        return null;
    }

}
