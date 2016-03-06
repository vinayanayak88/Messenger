package org.vinaya.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vinaya.javabrains.messenger.database.DatabaseClass;
import org.vinaya.javabrains.messenger.exceptions.DataNotFoundException;
import org.vinaya.javabrains.messenger.model.Message;

public class MessageService {
    
    private Map<Long,Message> messages = DatabaseClass.getMessages();
    
    public MessageService(){
        messages.put(1L, new Message(1, "Hello World!!", "Vinaya"));
        messages.put(2L, new Message(2, "Hello Jersey!!", "Vinaya"));
    }
    
    public List<Message> getAllMessages(){
        return new ArrayList<Message>(messages.values());
    }
    
    public Message getMessgae(Long id){
        Message message=  messages.get(id);
        if(message == null){
            throw new DataNotFoundException("Message with message id " + id +  " is not found");
        }
        else{
            return message;
        }
    }
    
    public Message addMessage(Message message){
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }
    
    public Message removeMesage(Long id){
       return messages.remove(id);
    }
    
    public Message updateMessage(Message message){
        messages.put(message.getId(), message);
        return message;
    }

}
