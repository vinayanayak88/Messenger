package org.vinaya.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.vinaya.javabrains.messenger.model.Comment;
import org.vinaya.javabrains.messenger.model.Message;
import org.vinaya.javabrains.messenger.model.Profile;

public class DatabaseClass {
    
    private static Map<Long,Message> messages = new HashMap<Long,Message>();
    private static Map<String,Profile> profiles = new HashMap<String,Profile>();
    private static Map<Long,Comment> comments = new HashMap<Long,Comment>();
    
    
    public static Map<Long, Message> getMessages() {
        return messages;
    }
    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
    public static Map<Long, Comment> getComments() {
        return comments;
    }
    

}
