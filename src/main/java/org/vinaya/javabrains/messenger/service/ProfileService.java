package org.vinaya.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.vinaya.javabrains.messenger.database.DatabaseClass;
import org.vinaya.javabrains.messenger.exceptions.DataNotFoundException;
import org.vinaya.javabrains.messenger.model.Profile;

public class ProfileService {
    
    private Map<String,Profile> profiles = DatabaseClass.getProfiles();
    
    public ProfileService(){
        profiles.put("Vinaya", new Profile(1, "Vinaya", "Vinaya","Nayak"));
        profiles.put("Vidya", new Profile(2, "Vidya", "Vidya","Nayak"));
    }
    
    
    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profiles.values());
    }
    
    public Profile getProfile(String profileName){
        Profile profile =  profiles.get(profileName);
        if(profile == null){
            throw new DataNotFoundException("Profile with profile name " + profileName +  " is not found");
        }
        else{
            return profile;
        }
    }
    
    public Profile addMessage(Profile profile){
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }
    
    public Profile removeMesage(String profileName){
       return profiles.remove(profileName);
    }
    
    public Profile updateMessage(Profile profile){
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

}
