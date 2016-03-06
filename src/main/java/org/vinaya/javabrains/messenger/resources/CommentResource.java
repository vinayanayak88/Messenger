package org.vinaya.javabrains.messenger.resources;


/**
 * Date of Creation: Date: Feb 01, 2016<br>
 * File Name : CommentResource.java<br>
 * 
 * @author <a href=mailto:vinaya.nayak@transerainc.com>Vinaya Nayak</a>
 * @version $Revision: 1.5 $
 */

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.vinaya.javabrains.messenger.model.Comment;
import org.vinaya.javabrains.messenger.service.CommentService;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
    
    CommentService commentService = new CommentService();
    
    /*@GET
    public String test(){
        return "New sub resource";
    }
    
    @GET
    @Path("/{commentId}")
    public String test2(@PathParam("messageId") String messageId, @PathParam("commentId") String commentId){
        return  "Method to return comment ID . Message ID is : " + messageId + " Comment Id is : " +commentId;
    }*/
    
    @GET
    public List<Comment> getAllComments(@PathParam("messageId")long messageId){
        return commentService.getAllComments(messageId);
    }
    
    @POST
    public Comment addComment(@PathParam("messageId")long messageId, Comment comment){
       return  commentService.addComment(messageId, comment);
    }
    
    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId")long messageId, @PathParam("commentId")long commentId, Comment comment){
        comment.setId(commentId);
        return commentService.updateComment(messageId, comment);
    }
    
    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId")long messageId, @PathParam("commentId")long commentId){
        return commentService.getComment(messageId, commentId);
    }
    
    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId")long messageId, @PathParam("commentId")long commentId){
        commentService.removeComment(messageId, commentId);
    }

}
