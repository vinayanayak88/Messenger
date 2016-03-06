package org.vinaya.javabrains.messenger.resources;


/**
 * Date of Creation: Date: Feb 01, 2016<br>
 * File Name : MessageResource.java<br>
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

import org.vinaya.javabrains.messenger.model.Message;
import org.vinaya.javabrains.messenger.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
//@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
    
    MessageService messageService = new MessageService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(){
        return messageService.getAllMessages();
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(){
        return messageService.getAllMessages();
    }
    
    @GET 
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId")long messageId,@Context UriInfo uriInfo){
         Message message = messageService.getMessgae(messageId);
         message.addLinks( getUriToSelf(uriInfo, message), "self");
         message.addLinks( getUriToProfile(uriInfo, message), "profile");
         message.addLinks( getUriToComments(uriInfo, message), "comment");
         return message;
    }
    
    @POST
    public Response addMessage(Message message,@Context UriInfo uriInfo) throws URISyntaxException{
        Message newMessage = messageService.addMessage(message);
       //return Response.status(Status.CREATED).entity(newMessage).build();
        //return Response.created(new URI("/messenger/webapi/messages/" + message.getId())).entity(newMessage).build();
        //return messageService.addMessage(message)
        String newId = String.valueOf(newMessage.getId());
        URI newUri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(newUri).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId")long messageId,Message message){
        message.setId(messageId);
        return messageService.updateMessage(message);
    }
    
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId")long messageId){
        messageService.removeMesage(messageId);
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }
    
    private String getUriToSelf(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).
                path(Long.toString(message.getId())).toString();
        return uri;
    }
    
    private String getUriToProfile(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).
                path(message.getAuthor()).toString();
        return uri;
    }
    
    private String getUriToComments(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).
                path(MessageResource.class,"getCommentResource").
                path(CommentResource.class).resolveTemplate("messageId", message.getId())
                .toString();
        return uri;
    }
}
