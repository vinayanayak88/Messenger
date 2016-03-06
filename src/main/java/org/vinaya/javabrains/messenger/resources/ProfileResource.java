package org.vinaya.javabrains.messenger.resources;


/**
 * Date of Creation: Date: Feb 01, 2016<br>
 * File Name : ProfileResource.java<br>
 * 
 * @author <a href=mailto:vinaya.nayak@transerainc.com>Vinaya Nayak</a>
 * @version $Revision: 1.5 $
 */

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.vinaya.javabrains.messenger.model.Profile;
import org.vinaya.javabrains.messenger.service.ProfileService;


@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    
    ProfileService profileService = new ProfileService();
    
    @GET
    public List<Profile> getprofiles(){
        return profileService.getAllProfiles();
    }
    
    @GET 
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName")String profileName){
        return profileService.getProfile(profileName);
    }
    
    @POST
    public Response addProfile(Profile profile,@Context UriInfo uriInfo) throws URISyntaxException{
        Profile newProfile = profileService.addMessage(profile);
       //return Response.status(Status.CREATED).entity(newMessage).build();
        //return Response.created(new URI("/messenger/webapi/messages/" + message.getId())).entity(newMessage).build();
        //return messageService.addMessage(message)
        String newName = String.valueOf(newProfile.getProfileName());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newName).build();
        return Response.created(newUri).entity(newProfile).build();
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateMessage(@PathParam("profileName")String profileName,Profile profile){
        profile.setProfileName(profileName);;
        return profileService.updateMessage(profile);
    }
    
    @DELETE
    @Path("/{profileName}")
    public void deleteMessage(@PathParam("profileName")String profileName){
        profileService.removeMesage(profileName);
    }
    
}
